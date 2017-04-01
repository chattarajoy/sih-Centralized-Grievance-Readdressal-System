package com.example.pari.usersapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.squareup.picasso.Picasso;

/**
 * Created by pari on 26-03-2017.
 */

public class EditComplaint extends AppCompatActivity {
    Bundle b;
    String accessToken,secretKey,name,email;
    String URL_FOR_COMPLAINT_BY_ID = Constants.SERVER+"/complaint/show_complaint_by_id";
    ProgressDialog progressDialog;
    ImageView tv_image;
    Button save_button,alert;
  //  String id,subject,description,latitude,longitude,city,state,pincode,created_at,updated_at,userid,status,priority,image;
    TextView tv_address,tv_id,tv_subject,tv_description,tv_latitude,tv_longitude,tv_city,tv_state,tv_pincode,tv_created_at,tv_updated_at,tv_userid,tv_status,tv_priority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_complaint);
        Intent i = getIntent();
        b = i.getExtras();
        secretKey = b.getString("secretKey");
        accessToken = b.getString("accessToken");
        name = b.getString("name");
        email = b.getString("email");
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        tv_id = (TextView)findViewById(R.id.textView15);
        tv_subject = (TextView)findViewById(R.id.textView16);
        tv_description = (TextView)findViewById(R.id.textView17);
        tv_latitude = (TextView)findViewById(R.id.textView18);
        tv_longitude = (TextView)findViewById(R.id.textView19);
        tv_city = (TextView)findViewById(R.id.textView20);
        tv_state = (TextView)findViewById(R.id.textView21);
        tv_pincode = (TextView)findViewById(R.id.textView22);
        tv_address = (TextView)findViewById(R.id.textView28);
        tv_created_at = (TextView)findViewById(R.id.textView23);
        tv_updated_at = (TextView)findViewById(R.id.textView24);
        tv_userid = (TextView)findViewById(R.id.textView25);
        tv_status = (TextView)findViewById(R.id.textView26);
        tv_priority = (TextView)findViewById(R.id.textView27);
        tv_image = (ImageView)findViewById(R.id.imageView4);
        tv_id.setText("Complaint ID : "+b.getString("id"));
        tv_subject.setText("Subject : "+b.getString("subject"));
        tv_description.setText("Description : "+b.getString("description"));
        tv_latitude.setText("Latitude : "+b.getString("latitude"));
        tv_longitude.setText("Longitude: "+b.getString("longitude"));
        tv_city.setText("City : "+b.getString("city"));
        tv_state.setText("State : "+b.getString("state"));
        tv_pincode.setText("Pincode : "+b.getString("pincode"));
        tv_created_at.setText("Created At : "+b.getString("created_at"));
        tv_updated_at.setText("Updated At : "+b.getString("updated_at"));
        tv_userid.setText("User ID : "+b.getString("user_id"));
        tv_status.setText("Status : "+b.getString("status"));
        tv_priority.setText("Priority : "+b.getString("priority"));
        tv_address.setText("Address : "+b.get("address"));
        save_button = (Button)findViewById(R.id.save_button);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditComplaint.this,HomePage.class);
                Bundle b2 = new Bundle();
                b2.putString("accessToken",accessToken);
                b2.putString("secretKey",secretKey);
                b2.putString("name",name);
                b2.putString("email",email);
                i.putExtras(b);
                startActivity(i);
            }
        });
        alert = (Button)findViewById(R.id.alert);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send request to api
            }
        });
        String key = b.getString("image");
        AWSCredentials credentials = new BasicAWSCredentials(
                Constants.BUCKET_ACCESS_KEY_ID,
                Constants.BUCKET_SECRET_KEY_ID);

        // create a client connection based on credentials
        AmazonS3 s3client = new AmazonS3Client(credentials);
       // s3client.setEndpoint("asarcgrs.s3.ap-south-1.amazonaws.com");
        // create bucket - name must be unique for all S3 users
        ResponseHeaderOverrides override = new ResponseHeaderOverrides();
        override.setContentType( "image/jpeg" );
        GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest( Constants.BUCKET_NAME, key );
        urlRequest.setExpiration( new Date( System.currentTimeMillis() + 3600000 ) );  // Added an hour's worth of milliseconds to the current time.
        urlRequest.setResponseHeaders( override );
        URL url = s3client.generatePresignedUrl( urlRequest );
        try {
           // startActivity( new Intent( Intent.ACTION_VIEW, Uri.parse( url.toURI().toString() ) ) );
           /* Uri uri = Uri.parse( url.toURI().toString() );
            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
            tv_image.setImageBitmap(bitmap);*/
            String Url = url.toURI().toString();
            Picasso.with(getApplicationContext())
                    .load(Url).resize(200,200)
                    .into(tv_image);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    /*    AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(Constants.BUCKET_ACCESS_KEY_ID,
                Constants.BUCKET_SECRET_KEY_ID));
        TransferUtility transferUtility = new TransferUtility(s3, this);
        File filesDir = getApplicationContext().getFilesDir();
        File imageFile = new File(filesDir,key);
        TransferObserver observer = transferUtility.download(
                Constants.BUCKET_NAME,
                key,
                imageFile
        );

        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
     //   Drawable d = new BitmapDrawable(getResources(), bitmap);
     //  resize(d);
     //   tv_image.setImageDrawable(d);
        tv_image.setImageBitmap(bitmap);*/
    }
    private BitmapDrawable resize(Drawable image)
    {
        //(int) (bitmap.getHeight() * 0.125)
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap,
                120, 120, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }
}
