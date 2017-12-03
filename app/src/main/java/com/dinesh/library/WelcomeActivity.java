package com.dinesh.library;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.hardware.fingerprint.FingerprintManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.Selection;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.zip.Inflater;

public class WelcomeActivity extends FragmentActivity implements Animation.AnimationListener{
   /* EditText regno, password;
    private Button login, cancel;
    String regno1, password1;
    private ProgressDialog loading;
    public static String ip = "http://192.168.43.88:81/login/";
    ProgressDialog progress;
    Context context;
    TextView errorDisplay;*/
    ViewFlipper viewFlipper;
     ImageView logo;
    TextView txtMessage;
    Button btnStart;
    // Animation
    Animation animFadein,animMove;
    Animation right_slide,left_slide;
    ImageButton next;
    Button nextt;

    //ViewFlipper view;
    Inflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtMessage=(TextView)findViewById(R.id.quote);
        next=(ImageButton)findViewById(R.id.Imgbtnnext);
        nextt=(Button)findViewById(R.id.btnnext);

       /* Typeface custom_font=Typeface.createFromAsset(getAssets(),"fonts/batmfa.ttf");
        txtMessage.setTypeface(custom_font);*/
       // setContentView(R.layout.second);
        logo=(ImageView)findViewById(R.id.logo);
        nextt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n=new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(n);
            }
        });

       // load the animation
        /*animFadein = AnimationUtils.loadAnimation(getApplicationContext(),                R.anim.fade_in);

         //set animation listener
        animFadein.setAnimationListener(this);
        logo.setVisibility(View.VISIBLE);
        logo.startAnimation(animFadein);
        // sMet animation listener
        animMove = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        animMove.setAnimationListener(this);
        logo.startAnimation(animMove);*/


    /*    new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {*/


               /* FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.right_slide, R.anim.left_slide);

                ft.replace(R.id.frame_container, (Fragment) new Second());

                // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();

            /*}
        }.start();*/


        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

    }
    @Override
    public void onAnimationEnd(Animation animation) {
        // Take any action after completing the animation


    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub


    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub


    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                case 0: return FirstFragment.newInstance("");

                case 1: return Second.newInstance("");
                /*case 2: return ThirdFragment.newInstance("ThirdFragment, Instance 1");
                case 3: return ThirdFragment.newInstance("ThirdFragment, Instance 2");
                case 4: return ThirdFragment.newInstance("ThirdFragment, Instance 3");*/
                default: return Second.newInstance("");
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

    }
}







/*
        regno = (EditText) findViewById(R.id.ETregno);
        password = (EditText) findViewById(R.id.ETpassword);
        login = (Button) findViewById(R.id.Btnlogin);
        //cancel = (Button) findViewById(R.id.cancel);
        progress = new ProgressDialog(this);
        progress.setMessage("Loging in...");
        //errorDisplay =(TextView) findViewById(R.id.tverror);
/*

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
          FingerprintManager fingerprintManager = (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            if (!fingerprintManager.isHardwareDetected()) {
        //        fp.setVisibility(View.VISIBLE);
                fp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent a=new Intent(LoginActivity.this,FpActivity.class);
                        startActivity(a);
                    }
                });
            }
        }}catch (Exception e)
        {
            e.printStackTrace();
        }*/


  /*      login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent content=new Intent(WelcomeActivity.this,ContentsActivity.class);
                startActivity(content);
                /*   regno1 = regno.getText().toString();
                password1 = password.getText().toString();
                if (regno1.length() != 0) {
                    try {
                        BackTask st = new BackTask();
                        st.execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }*/
    //        }
      //  });
    //}


    /*private class BackTask extends AsyncTask<String, String, String> {

        private String result = null;

        @Override
        protected String doInBackground(String... params) {
            try {
                String data = URLEncoder.encode("username", "UTF-8") + "=" +
                        URLEncoder.encode(regno1, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" +
                        URLEncoder.encode(password1, "UTF-8");
                BufferedReader reader = null;
                Log.d("checking",data);
                try {
                    URL url = new URL(ip);
                    URLConnection con = url.openConnection();
                    con.setDoOutput(true);
                    OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                    writer.write(data);
                    writer.flush();
                    //getting response back
                    reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuilder s = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        s.append(line + "\n");
                    }
                    result = s.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return result;

        }

        @Override
        protected void onPostExecute(String result) {

            progress.dismiss();
            //notification.setText(result);
            if (result != null)
                Toast.makeText(getBaseContext(),result,Toast.LENGTH_SHORT).show();
                result = result.trim();
                if (!result.equals("Not loggedin")) {
                    Intent intent = new Intent(LoginActivity.this, ContentsActivity.class);
                    intent.putExtra("json", result);
                    startActivity(intent);
                } else {
                    errorDisplay.setText("Check id / password");
                }
        }

        @Override
        protected void onPreExecute() {
            progress.show();
        }

        @Override
        protected void onProgressUpdate(String... text) {
            // progress. For example updating ProgessDialog
        }


    }*/

//}








