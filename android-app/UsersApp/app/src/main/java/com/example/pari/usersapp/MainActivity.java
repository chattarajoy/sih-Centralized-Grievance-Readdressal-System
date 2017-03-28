package com.example.pari.usersapp;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.Arrays;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.facebook.FacebookSdk;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.LoginResult;
import com.facebook.login.LoginManager;
import com.facebook.FacebookCallback;
import java.util.Map;
import com.facebook.FacebookException;
import java.util.concurrent.Callable;
import java.io.OutputStream;
import android.util.Log;
import java.util.HashMap;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.net.HttpURLConnection;
import java.net.URL;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import android.view.View;
import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.TextView;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import org.json.JSONException;
import org.json.JSONObject;
import com.facebook.Profile;
import com.facebook.AccessToken;



public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener{
    CallbackManager mFacebookCallbackManager;
    String loginid,authtoken;
    GoogleApiClient mGoogleApiClient;
    Context context=this;
    TextView tv_email,tv_pass;
    String email,pass;
    String resp,resp2,secretKey,accessToken,status;
    private static final String URL_FOR_LOGIN = Constants.SERVER+"/auth/user_login";

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        mFacebookCallbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);
       // context = this;
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        tv_email = (EditText)findViewById(R.id.textView4);
        tv_pass = (EditText)findViewById(R.id.textView5);
        LoginButton mFacebookSignInButton = (LoginButton)findViewById(R.id.fb_button);
        mFacebookSignInButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday"));
        mFacebookSignInButton.registerCallback(mFacebookCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        final Bundle parameters = new Bundle();
                        AccessToken accessToken = loginResult.getAccessToken();
                        Profile profile = Profile.getCurrentProfile();
                        GraphRequest request = GraphRequest.newMeRequest(
                                accessToken,
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        Log.v("LoginActivity", response.toString());

                                        // Application code
                                        try {
                                            String email = object.getString("email");
                                            String name = object.getString("name");
                                            parameters.putString("name",name);
                                            parameters.putString("email",email);
                                        }catch (JSONException e) {e.printStackTrace();}
                                    }
                                });

                   //     parameters.putString("fields", "id,name,email,gender,birthday");
                     //   request.setParameters(parameters);
                      //  request.executeAsync();



                        Toast toast = Toast.makeText(context,"Login Successful :)", Toast.LENGTH_LONG);
                        toast.show();

                        Intent intent = new Intent(context,HomePage.class);
                        intent.putExtra("loginType","facebook");
                        intent.putExtras(parameters);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancel() {
                        Toast toast = Toast.makeText(context,"Login Failed :(", Toast.LENGTH_LONG);
                        toast.show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(MainActivity.class.getCanonicalName(), error.getMessage());
                        Toast toast = Toast.makeText(context,"Login Failed :(", Toast.LENGTH_LONG);
                        toast.show();
                    }

                }
        );
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        SignInButton signInButton = (SignInButton) findViewById(R.id.google_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(this);
        Button signup = (Button)findViewById(R.id.signup_button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,SignUp.class);
                startActivity(intent);
            }
        });
        Button login = (Button)findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String email = tv_email.getText().toString();
                 String pass = tv_pass.getText().toString();
                 loginUser(email,pass);
             //    String restURL = "192.168.117.60:8000/";
            //     new RestOperation.execute(restURL);
            }
        });
    }

    @Override
    public void onClick(View v) {
        signIn();
    }
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());

        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            String name =  acct.getDisplayName();
            String email = acct.getEmail();
            Bundle parameters = new Bundle();
            parameters.putString("name",name);
            parameters.putString("email",email);
            Toast toast = Toast.makeText(context,"Login Successful :)", Toast.LENGTH_LONG);
            toast.show();

            Intent intent = new Intent(context,HomePage.class);
            intent.putExtra("loginType","google");
            intent.putExtras(parameters);
            startActivity(intent);
        }
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    private void loginUser( final String email, final String password) {
        // Tag used to cancel the request
        String cancel_req_tag = "login";
        progressDialog.setMessage("Logging you in...");
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_FOR_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();
                JSONObject jObj = null;
                try {
                    jObj = new JSONObject(response);
                    status = jObj.getString("status");
                    if (status != null && status.equals("success")) {
                        accessToken = null;
                        accessToken = jObj.getString("access_token");
                        secretKey = jObj.getString("secret_key");
                        String user = jObj.getString("user_name");
                        boolean aadhaar_verified = jObj.getBoolean("aadhar_verified");
                        boolean phone_no_verified = jObj.getBoolean("phone_no_verified");
                        Intent intent = new Intent(
                                            MainActivity.this,
                                            HomePage.class);
                        Bundle b = new Bundle();
                        b.putString("name", user);
                        b.putString("email", email);
                        b.putString("accessToken",accessToken);
                        b.putString("secretKey",secretKey);
                        b.putString("password",password);
                        b.putBoolean("aadhar_verified",aadhaar_verified);
                        b.putBoolean("phone_no_verified",phone_no_verified);
                        intent.putExtras(b);
                        startActivity(intent);
                        finish();
                    }

                    else {

                        String errorMsg = null;
                        try {
                            errorMsg = jObj.getString("error_message");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq,cancel_req_tag);
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }
    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
