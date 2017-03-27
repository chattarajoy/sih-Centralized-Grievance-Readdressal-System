package com.example.pari.usersapp;

/**
 * Created by pari on 26-03-2017.
 */
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ComplaintObjectHolder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView subject,description;
    public ComplaintObjectHolder(View itemView) {
        super(itemView);
        description = (TextView)itemView.findViewById(R.id.textView11);
        subject = (TextView)itemView.findViewById(R.id.textView10);
        image = (ImageView)itemView.findViewById(R.id.imageView3);
    }

}
