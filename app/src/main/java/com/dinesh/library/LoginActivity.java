package com.dinesh.library;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class LoginActivity extends Activity {
    EditText regno, password;
    private Button login;
    static String  regno1, password1;
    private ProgressDialog loading;
    public static String ip = "http://192.168.43.221:8089/";
    ProgressDialog progress;
    Context context;

    SharedPreferences sp,sp1;
    TextView errormsg;
    public boolean code=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        Intent n=getIntent();
        sp = getSharedPreferences("MyProfile", MODE_PRIVATE);
        sp1 = getSharedPreferences("login", MODE_PRIVATE);
        regno = (EditText) findViewById(R.id.ETregno);
        password = (EditText) findViewById(R.id.ETpassword);
        login = (Button) findViewById(R.id.Btnlogin);
        errormsg=(TextView)findViewById(R.id.error);
        Typeface face = Typeface.createFromAsset(getAssets(),  "fonts/segoeui.ttf");
        regno.setTypeface(face);
        password.setTypeface(face);
        login.setTypeface(face);
        errormsg.setTypeface(face);
        sp.getString("key",null);
        progress = new ProgressDialog(this);
        progress.setMessage("Loading...");
        String auser = sp1.getString("uname",null);
        String pass=sp1.getString("pass",null);


            if (auser != null) {

                regno.setText(auser);
                password.setText(pass);

            }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regno1 = regno.getText().toString();
                password1 = password.getText().toString();
                if (regno1.length() != 0) {
                    try {
                        BackTask st = new BackTask();
                        st.execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }




    public class BackTask extends AsyncTask<String, String, String> {

        private String result = null,name,batch,id,dept,dob,phno,add,credits;

        @Override
        protected String doInBackground(String... params) {
            try {
                String data = URLEncoder.encode("username", "UTF-8") + "=" +
                        URLEncoder.encode(regno1, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" +
                        URLEncoder.encode(password1, "UTF-8");
                BufferedReader reader = null;
                Log.d("checking", data);
                try {
                    URL url = new URL(ip + "login/");
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
                    Toast.makeText(getBaseContext(),"Connection error",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                Toast.makeText(getBaseContext(),"Connection error",Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            return result;

        }

        @Override
        protected void onPostExecute(String result) {
            SharedPreferences.Editor e = sp1.edit();
            if(!sp1.equals(null))
            {

                e.putString("uname", regno1);
                e.putString("pass", password1);
                e.apply();
            }
            progress.dismiss();

            if (result != null) {
                result = result.trim();
                setBooks(result);
            }
            if (!result.equals("Not loggedin")) {
                Intent intent = new Intent(LoginActivity.this, ContentsActivity.class);
              startActivity(intent);
            } else {
               errormsg.setText("*Check id / password");
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

        public void setBooks(String s){
            try {
                JSONObject jsonObject = new JSONObject(s);
                SharedPreferences.Editor e = sp.edit();
                name = jsonObject.optString("name").toString();

                e.putString("name",name);
                batch = jsonObject.optString("batch").toString();
                e.putString("batch",batch);
                id = jsonObject.optString("id").toString();
                e.putString("id",id);
                dept = jsonObject.getString("dept").toString();
                e.putString("dept",dept);
                dob = jsonObject.getString("dob").toString();
                e.putString("dob",dob);
                phno=jsonObject.getString("phno").toString();
                e.putString("phone",phno);
                add = jsonObject.getString("address").toString();
                e.putString("address",add);
               credits = jsonObject.getString("credit").toString();
                e.putString("credit",credits);
                e.commit();

            } catch (JSONException e) {
                Toast.makeText(getBaseContext(),e.toString(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }

    }

}
