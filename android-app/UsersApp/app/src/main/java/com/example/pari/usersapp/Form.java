package com.example.pari.usersapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.location.Address;
import android.support.v4.app.ActivityCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.provider.MediaStore;
import android.graphics.Bitmap;
import android.os.ResultReceiver;
import android.os.Handler;
import android.location.Geocoder;
import android.database.Cursor;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.mobileconnectors.s3.transfermanager.Upload;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferManager;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Locale;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.mobileconnectors.s3.transferutility.*;
import java.io.File;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import android.provider.MediaStore.Images;
/**
 * Created by pari on 23-03-2017.
 */

public class Form extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String mAddressOutput;
    int LOCATION_PERMISSION = 1;
    protected Location mLastLocation;
    private AddressResultReceiver mResultReceiver;
    ImageView imageView;
    TextView textLoc;
    Button imageCaptureButton;
    Button imageUploadButton;
    Spinner sp_type;
    ProgressDialog progressDialog;
    Context context = this;
    String subject,description,image,latitude,longitude,city,state,pincode,accessToken,secretKey;
    Bundle b;
    final private static String URL_FOR_COMPLAINT = Constants.SERVER+"/complaint/create";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        b = getIntent().getExtras();
        accessToken = b.getString("accessToken");
        secretKey = b.getString("secretKey");
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        textLoc = (TextView) findViewById(R.id.textView7);
        imageCaptureButton = (Button) findViewById(R.id.button3);
        imageUploadButton = (Button) findViewById(R.id.button2);
        sp_type = (Spinner) findViewById(R.id.spinner2);
        imageView = (ImageView)findViewById(R.id.imageView2);
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener ll = new myLocationListener();
        mResultReceiver = new AddressResultReceiver(new Handler());
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION);
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, ll);
        imageCaptureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        imageUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sp_type.setAdapter(adapter);
        sp_type.setOnItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        Button submit = (Button)findViewById(R.id.button5);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description = (((EditText)findViewById(R.id.textView6)).getText()).toString();
                registerComplaint();
            }
        });
    }
    private void registerComplaint() {
        // Tag used to cancel the request
        String cancel_req_tag = "register";

        progressDialog.setMessage("Filing your Complaint");
        showDialog();
       /* if(test == 0) {
            URL_FOR_REGISTRATION+="?name="+name+"&contact="+contact+"&email="+email+"&password="+password;
            test = 1;
        }*/
        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_FOR_COMPLAINT, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
               // Log.d("Form", "Register Response: " + response.toString());
                //Toast.makeText(getApplicationContext(),"Register Response: " + response.toString(), Toast.LENGTH_SHORT).show();
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    //   boolean error = jObj.getBoolean("error");
                    String status = jObj.getString("status");
                    if (status.equals("success")) {
                        Toast.makeText(getApplicationContext(),
                                "Complaint Successfully Filed!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, HomePage.class);
                        intent.putExtras(b);
                        startActivity(intent);
                        /*// Launch login activity
                        Intent intent = new Intent(
                                RegisterActivity.this,
                                LoginActivity.class);
                        startActivity(intent);
                        finish();*/
                    } else {

                        String errorMsg = jObj.getString("error_message");
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
                Log.e("Form", "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),"Error:"+
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("subject", subject);
                params.put("description",description);
                params.put("image", image);
                params.put("city", city);
                params.put("state", state);
                params.put("pincode", pincode);
                params.put("latitude", latitude);
                params.put("longitude", longitude);
                params.put("access_token",accessToken);
                params.put("secret_key",secretKey);
                return params;
            }
            @Override
            public Map<String, String> getHeaders() {
                Map <String,String> params  = new HashMap<String, String>();
                params.put("access_token",accessToken);
                params.put("secret_key",secretKey);
                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);

            final String OBJECT_KEY="https://s3-ap-southeast-1.amazonaws.com/asarcgrs/ "+imageBitmap.toString()+".jpeg";
            image = OBJECT_KEY;
            // this example to work
            AWSCredentials credentials = new BasicAWSCredentials(
                    Constants.BUCKET_ACCESS_KEY_ID,
                    Constants.BUCKET_SECRET_KEY_ID);

            // create a client connection based on credentials
            final AmazonS3 s3client = new AmazonS3Client(credentials);
           // s3client.setEndpoint("s3.ap-south-1.amazonaws.com");
            // create bucket - name must be unique for all S3 users
            final String bucketName = Constants.BUCKET_NAME;
           /* s3client.putObject(new PutObjectRequest(bucketName, fileName,
                    new File("C:\\Users\\user\\Desktop\\testvideo.mp4"))
                    .withCannedAcl(CannedAccessControlList.PublicRead));*/
            Uri tempUri = getImageUri(getApplicationContext(), imageBitmap);
            final String filePath = getRealPathFromURI(tempUri);
          /*  TransferManager manager = new TransferManager(credentials);
            Upload upload = manager.upload("bucket_name", "Android/" + OBJECT_KEY, file);*/
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    PutObjectRequest por = new PutObjectRequest( bucketName, OBJECT_KEY, new java.io.File( filePath) ).withCannedAcl(CannedAccessControlList.PublicRead);
                    s3client.putObject( por );
                }
            });

            /*File filesDir = context.getFilesDir();
            File imageFile = new File(filesDir,OBJECT_KEY);
            OutputStream os = null;
            try {
                os = new BufferedOutputStream(new FileOutputStream(imageFile));
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                os.close();
                AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(Constants.BUCKET_ACCESS_KEY_ID,
                        Constants.BUCKET_SECRET_KEY_ID));
                TransferUtility transferUtility = new TransferUtility(s3, context);
                TransferObserver observer = transferUtility.upload(
                        Constants.BUCKET_NAME,
                        OBJECT_KEY,
                        imageFile
                );
                image = OBJECT_KEY;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/



           /* s3.putObject(new PutObjectRequest("your-bucket", "some-path/some-key.jpg", new File("somePath/someKey.jpg")).withCannedAcl(CannedAccessControlList.PublicRead));
            s3.getUrl("your-bucket", "some-path/some-key.jpg");*/
        }
        else if(requestCode == 0 && resultCode == RESULT_OK)
        {
            Bitmap bitmap;
            super.onActivityResult(requestCode, resultCode, data);
            //  TextView textTargetUri;
            if (resultCode == RESULT_OK){
                Uri targetUri = data.getData();
                //   textTargetUri.setText(targetUri.toString());

                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                    imageView.setImageBitmap(bitmap);
                    final String OBJECT_KEY="https://s3-ap-southeast-1.amazonaws.com/asarcgrs/ "+bitmap.toString()+".jpeg";
                    image = OBJECT_KEY;
                    final String filePath = getRealPathFromURI(targetUri);
                    AWSCredentials credentials = new BasicAWSCredentials(
                            Constants.BUCKET_ACCESS_KEY_ID,
                            Constants.BUCKET_SECRET_KEY_ID);

                    // create a client connection based on credentials
                    final AmazonS3 s3client = new AmazonS3Client(credentials);
                  //  s3client.setEndpoint("s3.ap-south-1.amazonaws.com");
                    // create bucket - name must be unique for all S3 users
                    final String bucketName = Constants.BUCKET_NAME;
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            PutObjectRequest por = new PutObjectRequest( bucketName, OBJECT_KEY, new java.io.File( filePath) ).withCannedAcl(CannedAccessControlList.PublicRead);
                            s3client.putObject( por );
                        }
                    });
                    /*File filesDir = context.getFilesDir();
                    File imageFile = new File(filesDir,OBJECT_KEY);
                    OutputStream os = null;
                    try {
                        os = new BufferedOutputStream(new FileOutputStream(imageFile));
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                        os.close();
                        AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(Constants.BUCKET_ACCESS_KEY_ID,
                                Constants.BUCKET_SECRET_KEY_ID));
                        TransferUtility transferUtility = new TransferUtility(s3, context);

                        TransferObserver observer = transferUtility.upload(
                                Constants.BUCKET_NAME,
                                OBJECT_KEY,
                                imageFile
                        );
                        image = OBJECT_KEY;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
*/


                } catch (java.io.FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    protected void startIntentService() {
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra(Constants.RECEIVER, mResultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, mLastLocation);
        startService(intent);
    }

    void showToast(String s) {
        Toast t = Toast.makeText(this, s, Toast.LENGTH_LONG);
        t.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        subject = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        subject = "";
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Form Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private class myLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                longitude = Double.valueOf(location.getLongitude()).toString();
                latitude = Double.valueOf(location.getLatitude()).toString();
                mLastLocation = location;
                List<Address> addresses;
                try {
                    Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
                    addresses = gcd.getFromLocation(location.getLatitude(),
                            location.getLongitude(), 1);
                    city = addresses.get(0).getLocality();
                    pincode = addresses.get(0).getPostalCode();
                    state = addresses.get(0).getAdminArea();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!Geocoder.isPresent()) {
                    //  showToast(getString(R.string.no_geocoder_available));
                    textLoc.append(getString(R.string.no_geocoder_available));
                } else
                    startIntentService();
            }
        }


        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            // Display the address string
            // or an error message sent from the intent service.
            mAddressOutput = resultData.getString(Constants.RESULT_DATA_KEY);
            // displayAddressOutput();
            textLoc.setText(mAddressOutput);
            // Show a toast message if an address was found.
            if (resultCode == Constants.SUCCESS_RESULT) {
                //  showToast(getString(R.string.address_found));
            }

        }

    }

}

