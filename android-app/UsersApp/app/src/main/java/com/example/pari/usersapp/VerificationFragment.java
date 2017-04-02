package com.example.pari.usersapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
 * Created by pari on 01-04-2017.
 */

public class VerificationFragment extends Fragment {
    String status,accessToken,secretKey;
    String otp;
    Bundle b;
    String URL_FOR_VERIFICATION_AADHAR = Constants.SERVER+"/aadhar_verification/verify_aadhar_data";
    String URL_FOR_VERIFICATION_OTP = Constants.SERVER+"/aadhar_verification/verify_otp";
    ProgressDialog progressDialog;
    String otpSent;
    TextInputLayout et_otp;
    Button verifyOtp;
    Context context;
    public VerificationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_verifiaction, container, false);
        context = getContext();
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        b = getActivity().getIntent().getExtras();
        accessToken = b.getString("accessToken");
        secretKey = b.getString("secretKey");
        final TextInputLayout aadhar = (TextInputLayout)v.findViewById(R.id.editText6);
        final TextInputLayout phone = (TextInputLayout)v.findViewById(R.id.editText7);
        et_otp = (TextInputLayout)v.findViewById(R.id.editText12);
        verifyOtp = (Button)v.findViewById(R.id.button8);
        Button verify = (Button)v.findViewById(R.id.button7);
        et_otp.setVisibility(View.INVISIBLE);
        verifyOtp.setVisibility(View.INVISIBLE);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aadhar_card_no = aadhar.getEditText().getText().toString();
                String phone_no = phone.getEditText().getText().toString();
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
        verifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x = et_otp.getEditText().getText().toString();
                verify_otp(x);
            }
        });
        return v;
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
                        et_otp.setVisibility(View.VISIBLE);
                        verifyOtp.setVisibility(View.VISIBLE);
                       /* String otpEntered = getInputFromPrompt();
                       /* boolean isVerified = verify_otp(otpSent,otpEntered);
                        if(isVerified)
                            test[0] = 1;*/
                        //   test[0] = 1;

                        //  finish();
                    }

                    else {

                        String errorMsg = null;
                        try {
                            errorMsg = jObj.getString("error_message");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(context,
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
                Toast.makeText(context,
                        "Please Check your Internet Connection!", Toast.LENGTH_LONG).show();
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
        AppSingleton.getInstance(context).addToRequestQueue(strReq,cancel_req_tag);
        /*if(test[0] == 1)
            return true;
        return false;*/
    }
    void verify_otp(final String otpEntered)
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
                        Toast.makeText(context,
                                "Successfully Verified!", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getActivity(), HomePage.class);
                        b.remove("aadhar_verified");
                        b.putBoolean("aadhar_verified",true);
                        intent.putExtras(b);
                        startActivity(intent);
                    }

                    else {

                        String errorMsg = null;
                        try {
                            errorMsg = jObj.getString("error_message");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(context,
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
                Toast.makeText(context,
                        "Please Check your Internet Connection!", Toast.LENGTH_LONG).show();
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
        AppSingleton.getInstance(context).addToRequestQueue(strReq,cancel_req_tag);
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
}
