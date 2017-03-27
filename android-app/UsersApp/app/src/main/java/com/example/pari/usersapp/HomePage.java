package com.example.pari.usersapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by pari on 23-03-2017.
 */

public class HomePage extends AppCompatActivity {
    Context context = this;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Intent intent = getIntent();
        String loginType = intent.getStringExtra("loginType");
        String name,email;
        TextView textView_name = (TextView)findViewById(R.id.textView_name);
        TextView textView_email = (TextView)findViewById(R.id.textView_email);
        b = intent.getExtras();
        name = b.getString("name");
        email = b.getString("email");
        String accessToken = b.getString("accessToken");
        String secretKey = b.getString("secretKey");
        textView_name.setText(name);
        textView_email.setText(email);
        Button button_complain = (Button)findViewById(R.id.button_complain);
        button_complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Form.class);
                intent.putExtras(b);
                startActivity(intent);
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
    }
}
