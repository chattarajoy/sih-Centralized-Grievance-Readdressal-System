package com.example.pari.usersapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.design.widget.TextInputLayout;
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

public class UpdatePasswordFragment extends Fragment {
    Context context;
    ProgressDialog progressDialog;
    String status,accessToken,secretKey;
    String URL_FOR_UPDATE_PASSWORD = Constants.SERVER+"/user/update_password";
    public UpdatePasswordFragment() {
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
        View v = inflater.inflate(R.layout.activity_update_password, container, false);
        context = getContext();
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        final EditText current = ((TextInputLayout) v
                .findViewById(R.id.editText9)).getEditText();
        final EditText new_pass = ((TextInputLayout) v
                .findViewById(R.id.editText10)).getEditText();

        final EditText confirm_new = ((TextInputLayout) v
                .findViewById(R.id.editText11)).getEditText();
        final String password = getActivity().getIntent().getExtras().getString("password");
        accessToken = getActivity().getIntent().getExtras().getString("accessToken");
        secretKey = getActivity().getIntent().getExtras().getString("secretKey");
        Button b = (Button)v.findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    updatePassword(password, new_password);
                }
            }
        });


        return v;
    }
    void updatePassword(final String old_pass,final String new_pass)
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
                        Toast.makeText(context,
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
                params.put("old_password", old_pass);
                params.put("new_password", new_pass);
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
