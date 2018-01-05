package com.dinesh.library;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class ProfileActivity extends Activity {
   public TextView name, batch, idnumber,  dob, phone, address, fine,dept;
    private SharedPreferences sp;
    String credits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent i = getIntent();
        name = (TextView) findViewById(R.id.tVusername);
        batch = (TextView) findViewById(R.id.tVbatch);
        idnumber = (TextView) findViewById(R.id.tVid);
        dept = (TextView) findViewById(R.id.tVdept);
        dob = (TextView) findViewById(R.id.tVdob);
        phone = (TextView) findViewById(R.id.tVpnumber);
        address = (TextView) findViewById(R.id.tVaddress);
        fine = (TextView) findViewById(R.id.tVfine);
        sp = getSharedPreferences("MyProfile", MODE_PRIVATE);
        dept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sem=new Intent(ProfileActivity.this,SemActivity.class);
                startActivity(sem);
            }
        });


        Typeface face = Typeface.createFromAsset(getAssets(),  "fonts/segoeui.ttf");



        name.setTypeface(face);
        batch.setTypeface(face);
        idnumber.setTypeface(face);
        dept.setTypeface(face);
        dob.setTypeface(face);
        phone.setTypeface(face);
        address.setTypeface(face);
        fine.setTypeface(face);

        String user_name = sp.getString("name", "kkkkkkkk");

        name.setText(user_name);
        String user_batch = sp.getString("batch", "");
        batch.setText("Batch         : "+user_batch);
        String user_idnumber = sp.getString("id", "");
        idnumber.setText("Student ID : "+user_idnumber);
        String user_dept = sp.getString("dept", "");
        dept.setText("Branch       : "+user_dept);
        String user_dob = sp.getString("dob", "");
        dob.setText("DOB           : "+user_dob);
        String user_phone = sp.getString("phone", "");
        phone.setText("Phone         : "+user_phone);
        String user_address = sp.getString("address", "");
        address.setText("Address      : "+user_address);
        String user_fine = sp.getString("credit", "");
        fine.setText("Credits(fine): "+user_fine);











    }
    }


