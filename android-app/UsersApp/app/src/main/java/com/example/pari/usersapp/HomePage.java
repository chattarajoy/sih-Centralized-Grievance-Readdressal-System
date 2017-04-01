package com.example.pari.usersapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pari on 23-03-2017.
 */

public class HomePage extends AppCompatActivity {
    Context context = this;
    Bundle b;
    String URL_FOR_UPDATE_PASSWORD = Constants.SERVER+"/user/update_password";
    String accessToken,secretKey;
    String name,email,status;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Intent intent = getIntent();
        String loginType = intent.getStringExtra("loginType");
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        TextView textView_name = (TextView)findViewById(R.id.textView_name);
        TextView textView_email = (TextView)findViewById(R.id.textView_email);
        b = intent.getExtras();
        name = b.getString("name");
        email = b.getString("email");
        accessToken = b.getString("accessToken");
        secretKey = b.getString("secretKey");
        final boolean aadhar_verified = b.getBoolean("aadhar_verified");
        final boolean phone_no_verified = b.getBoolean("phone_no_verified");
        final String password = b.getString("password");
        textView_name.setText(name);
        textView_email.setText(email);
        Button button_complain = (Button)findViewById(R.id.button_complain);
        button_complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!aadhar_verified || !phone_no_verified)
                {
                    Intent intent = new Intent(HomePage.this,Verification.class);
                    intent.putExtras(b);
                    startActivity(intent);
                }
                else
                {

                            Intent intent = new Intent(HomePage.this,Form.class);
                            intent.putExtras(b);
                            startActivity(intent);



                }

            }
        });
        Button button_sign_out = (Button)findViewById(R.id.button4);
        button_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MainActivity.class);
                startActivity(intent);
            }
        });
        Button button_track = (Button) findViewById(R.id.button_track);
        button_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Track.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        Button button_change_pass = (Button) findViewById(R.id.button_change_pass);
        button_change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.change_pass_prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText current = (EditText) promptsView
                        .findViewById(R.id.editText9);
                final EditText new_pass = (EditText) promptsView
                        .findViewById(R.id.editText10);

                final EditText confirm_new = (EditText) promptsView
                        .findViewById(R.id.editText11);
                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {

                                      //  while(true) {
                                            String pass = current.getText().toString();
                                            String new_password = new_pass.getText().toString();
                                            String confirm_new_pass = confirm_new.getText().toString();
                                            if (!pass.equals(password)) {
                                                Toast toast = Toast.makeText(context, "Incorrect Password", Toast.LENGTH_LONG);
                                                toast.show();
                                            } else if (!new_password.equals(confirm_new_pass)) {
                                                Toast toast = Toast.makeText(context, "Passwords Don't Match", Toast.LENGTH_LONG);
                                                toast.show();
                                            } else {
                                                updatePassword(new_password);
                                              //  break;
                                            }
                                     //   }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
    }
    void updatePassword(final String new_pass)
    {
        String cancel_req_tag = "update_password";
        progressDialog.setMessage("Updating Password");
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_FOR_UPDATE_PASSWORD, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                hideDialog();
                JSONObject jObj = null;
                try {
                    jObj = new JSONObject(response);
                    status = jObj.getString("status");
                    if (status != null && status.equals("success")) {
                        Toast.makeText(getApplicationContext(),
                                "Password Updated!", Toast.LENGTH_LONG).show();
                      //  finish();
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
              //  Log.e(TAG, "Login Error: " + error.getMessage());
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
                params.put("password", new_pass);
                return params;
            }
            @Override
            public Map<String, String> getHeaders() {
                // Posting params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("access_token", accessToken);
                params.put("secret_key", secretKey);
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
