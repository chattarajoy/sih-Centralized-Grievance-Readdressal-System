package com.example.pari.usersapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.net.URL;
import java.net.URISyntaxException;
import java.util.Date;

/**
 * Created by pari on 26-03-2017.
 */

public class CardViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ComplaintObject> items = new ArrayList<ComplaintObject>();
    View view;
    Context context;
    String name,email;
    Boolean aadhar_verified,phone_no_verified;
    public CardViewAdapter(ArrayList<ComplaintObject> items,Context context,String name,String email,Boolean aadhar_verified,Boolean phone_no_verified)
    {
        this.items=items;
        this.context = context;
        this.name = name;
        this.email = email;
        this.aadhar_verified= aadhar_verified;
        this.phone_no_verified=phone_no_verified;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_complaints, parent, false);
        final ComplaintObjectHolder complaintObjectHolder = new ComplaintObjectHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,EditComplaint.class);
                int position = complaintObjectHolder.getPosition();
                ComplaintObject co = items.get(position);
                Bundle b = new Bundle();
                b.putString("id",co.getId());
                b.putString("subject",co.getSubject());
                b.putString("description",co.getDescription());
                b.putString("image",co.getImage());
                b.putString("latitude",co.getLatitude());
                b.putString("longitude",co.getLongitude());
                b.putString("city",co.getCity());
                b.putString("state",co.getState());
                b.putString("pincode",co.getPincode());
                b.putString("created_at",co.getCreated_at());
                b.putString("updated_at",co.getUpdated_at());
                b.putString("user_id",co.getUserid());
                b.putString("status",co.getStatus());
                b.putString("priority",co.getPriority());
                b.putString("accessToken",co.getAccessToken());
                b.putString("secretKey",co.getSecretKey());
                b.putString("name",name);
                b.putString("email",email);
                b.putBoolean("aadhar_verified",aadhar_verified);
                b.putBoolean("phone_no_verified",phone_no_verified);
                intent.putExtras(b);
                context.startActivity(intent);
            }
        });
        return complaintObjectHolder;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ComplaintObjectHolder vh1 = (ComplaintObjectHolder) holder;
       // vh1.id.setText("Complaint ID : "+items.get(position).getId());
        vh1.subject.setText(items.get(position).getId()+". Subject : "+items.get(position).getSubject());
        vh1.description.setText("Description : "+items.get(position).getDescription());
        vh1.status.setText("Status : "+items.get(position).getStatus());
      /*  vh1.latitude.setText("Latitude : "+items.get(position).getLatitude());
        vh1.longitude.setText("Longitude : "+items.get(position).getLongitude());
        vh1.city.setText("City : "+items.get(position).getCity());
        vh1.state.setText("State : "+items.get(position).getState());
        vh1.pincode.setText("Pincode : "+items.get(position).getPincode());
        vh1.created_at.setText("Created At : "+items.get(position).getCreated_at());
        vh1.updated_at.setText("Updated At : "+items.get(position).getUpdated_at());
        vh1.userid.setText("User ID : "+items.get(position).getUserid());
        vh1.priority.setText("Priority : "+items.get(position).getPriority());
        vh1.status.setText("Status : "+items.get(position).getState());*/

    /*    AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(Constants.BUCKET_ACCESS_KEY_ID,
                Constants.BUCKET_SECRET_KEY_ID));
        TransferUtility transferUtility = new TransferUtility(s3, context);
        File filesDir = context.getFilesDir();
        File imageFile = new File(filesDir,key);
        TransferObserver observer = transferUtility.download(
                Constants.BUCKET_NAME,
                key,
                imageFile
        );
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        imageView.setImageBitmap(bitmap);*/
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
