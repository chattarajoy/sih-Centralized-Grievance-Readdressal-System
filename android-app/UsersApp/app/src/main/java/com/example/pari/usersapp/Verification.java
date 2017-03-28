package com.example.pari.usersapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
 * Created by pari on 27-03-2017.
 */

public class Verification extends AppCompatActivity {
  //  String aadhar_card_no,phone_no;
    String status,accessToken,secretKey;
    String otp;
    Bundle b;
    String URL_FOR_VERIFICATION_AADHAR = Constants.SERVER+"/aadhar_verification/verify_aadhar_data";
    String URL_FOR_VERIFICATION_OTP = Constants.SERVER+"/aadhar_verification/verify_otp";
    ProgressDialog progressDialog;
    String otpSent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifiaction);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        b = getIntent().getExtras();
        accessToken = b.getString("accessToken");
        secretKey = b.getString("secretKey");
        final EditText aadhar = (EditText)findViewById(R.id.editText6);
        final EditText phone = (EditText)findViewById(R.id.editText7);
        Button verify = (Button)findViewById(R.id.button7);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aadhar_card_no = aadhar.getText().toString();
                String phone_no = phone.getText().toString();
                verify_aadhar(aadhar_card_no,phone_no);
               /* boolean isVerified = verify_aadhar(aadhar_card_no,phone_no);
                if(isVerified) {
                    getInputFromPrompt();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "Verification Failed :(", Toast.LENGTH_LONG).show();
                }*/
            }
        });
    }
    void verify_aadhar(final String aadhar_card_no, final String phone_no)
    {
        String cancel_req_tag = "verifying_aadhar";
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        progressDialog.setMessage("Sending OTP");
        showDialog();
     //   final int[] test = {0};
        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_FOR_VERIFICATION_AADHAR, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                hideDialog();
                JSONObject jObj = null;
                try {
                    jObj = new JSONObject(response);
                    status = jObj.getString("status");
                    if (status != null && status.equals("success")) {
                        otpSent = jObj.getString("message");
                       /* String otpEntered = getInputFromPrompt();
                       /* boolean isVerified = verify_otp(otpSent,otpEntered);
                        if(isVerified)
                            test[0] = 1;*/
                     //   test[0] = 1;
                        getInputFromPrompt();
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
                params.put("aadhar_number", aadhar_card_no);
                params.put("contact", phone_no);
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
       // if(test[0] == 0)
            AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq,cancel_req_tag);
        /*if(test[0] == 1)
            return true;
        return false;*/
    }
    void verify_otp(String otpSent, final String otpEntered)
    {
        String cancel_req_tag = "verifying_phone";
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        progressDialog.setMessage("Verifying OTP");
        showDialog();
     //   final int[] test = {0};
        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_FOR_VERIFICATION_OTP, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                hideDialog();
                JSONObject jObj = null;
                try {
                    jObj = new JSONObject(response);
                    status = jObj.getString("status");
                    if (status != null && status.equals("success")) {
                       // test[0] = 1;
                        Toast.makeText(getApplicationContext(),
                                "Successfully Verified!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Verification.this, Form.class);
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
                params.put("otp", otpEntered);
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
        /*if(test[0] == 1)
            return true;
        return false;*/
    }
    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }
    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
    void getInputFromPrompt()
    {
        otp="";
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.otp_prompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText et_otp = (EditText) promptsView
                .findViewById(R.id.editText8);
        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                otp = et_otp.getText().toString();
                                verify_otp(otpSent,otp);
                                /*boolean isVerified = verify_otp(otpSent,otp);
                                if(isVerified) {
                                    Intent intent = new Intent(Verification.this, Form.class);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),
                                            "Verification Failed :(", Toast.LENGTH_LONG).show();
                                }*/
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


       // return otp;
    }
}
