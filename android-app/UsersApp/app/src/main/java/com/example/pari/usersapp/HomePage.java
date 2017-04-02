package com.example.pari.usersapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

import android.support.design.widget.TabLayout;
import com.example.pari.usersapp.FileAComplaintFragment;
import com.example.pari.usersapp.Verification;
import com.example.pari.usersapp.TrackFragment;
import com.example.pari.usersapp.UpdatePasswordFragment;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import java.util.List;
import java.util.ArrayList;
/**
 * Created by pari on 23-03-2017.
 */

public class HomePage extends AppCompatActivity {
    Context context = this;
    Bundle b;
    String accessToken,secretKey;
    String name,email,status;
    ProgressDialog progressDialog;
    FloatingActionButton sign_out;
    int test = 0;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Boolean aadhar_verified;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Intent intent = getIntent();
        String loginType = intent.getStringExtra("loginType");
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        b = intent.getExtras();
        name = b.getString("name");
        email = b.getString("email");
        accessToken = b.getString("accessToken");
        secretKey = b.getString("secretKey");
        aadhar_verified = b.getBoolean("aadhar_verified");
        if(aadhar_verified)
            test = 1;
        final boolean phone_no_verified = b.getBoolean("phone_no_verified");
        final String password = b.getString("password");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        sign_out = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MainActivity.class);
                startActivity(intent);
            }
        });


    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        if(test == 1)
        {
            adapter.addFragment(new FileAComplaintFragment(), "REPORT COMPLAINT");
        }
        else
        {
            adapter.addFragment(new VerificationFragment(), "REPORT COMPLAINT");
        }
        adapter.addFragment(new TrackFragment(), "TRACK COMPLAINTS");

        adapter.addFragment(new UpdatePasswordFragment(), "SETTINGS");
        viewPager.setAdapter(adapter);
    }
    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    void setTest()
    {
        test = 1;
    }
}
