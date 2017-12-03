package com.dinesh.library;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SemActivity extends Activity {
    Button sem1,sem2,sem3,sem4,sem5,sem6,sem7,sem8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem);
        sem1=(Button)findViewById(R.id.btnsem1);
        sem2=(Button)findViewById(R.id.btnsem2);
        sem3=(Button)findViewById(R.id.btnsem3);
        sem4=(Button)findViewById(R.id.btnsem4);
        sem5=(Button)findViewById(R.id.btnsem5);
        sem6=(Button)findViewById(R.id.btnsem6);
        sem7=(Button)findViewById(R.id.btnsem7);
        sem8=(Button)findViewById(R.id.btnsem8);

        Typeface face = Typeface.createFromAsset(getAssets(),  "fonts/segoeui.ttf");

        sem1.setTypeface(face);
        sem2.setTypeface(face);
        sem3.setTypeface(face);
        sem4.setTypeface(face);
        sem5.setTypeface(face);
        sem6.setTypeface(face);
        sem7.setTypeface(face);
        sem8.setTypeface(face);
        sem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sem1=new Intent(SemActivity.this,Sem1Activity.class);
                startActivity(sem1);
            }
        });
        sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sem2=new Intent(SemActivity.this,Sem2Activity.class);
                startActivity(sem2);
            }
        });
        sem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sem3=new Intent(SemActivity.this,Sem3Activity.class);
                startActivity(sem3);
            }
        });
        sem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sem4=new Intent(SemActivity.this,Sem4Activity.class);
                startActivity(sem4);
            }
        });
        sem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sem5=new Intent(SemActivity.this,Sem5Activity.class);
                startActivity(sem5);
            }
        });
        sem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sem6=new Intent(SemActivity.this,Sem6Activity.class);
                startActivity(sem6);
            }
        });
        sem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sem7=new Intent(SemActivity.this,Sem7Activity.class);
                startActivity(sem7);
            }
        });
        sem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sem8=new Intent(SemActivity.this,Sem8Activity.class);
                startActivity(sem8);
            }
        });
    }
}
