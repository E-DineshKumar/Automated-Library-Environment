package com.dinesh.library;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dinesh.library.com.google.zxing.integration.android.IntentIntegrator;
import com.dinesh.library.com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class BarcodeActivity extends Activity implements View.OnClickListener{
    private ImageButton scanBtn;
    private TextView formatTxt, contentTxt;
    public String contentTxt1,rr;
    ProgressDialog progress;

    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);
        Intent intent=getIntent();
         rr=intent.getStringExtra("rr");
        scanBtn = (ImageButton)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        progress = new ProgressDialog(this);
        progress.setMessage("Updating...");

        contentTxt = (TextView)findViewById(R.id.scan_content);
        scanBtn.setOnClickListener(this);
        contentTxt1="";
        sp = getSharedPreferences("MyProfile", MODE_PRIVATE);
    }

    public void onClick(View v){
        if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("CONTENT: " + scanContent);
            contentTxt1 =  scanContent;
            new BackTask().execute();

        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    private class BackTask extends AsyncTask<String, String, String> {

        private String result = null;
        String user_idnumber = sp.getString("id", "jhjk");


        @Override
        protected String doInBackground(String... params) {
            try {

                String data = URLEncoder.encode("id", "UTF-8") + "=" +
                        URLEncoder.encode(user_idnumber, "UTF-8") + "&" +
                        URLEncoder.encode(rr, "UTF-8") + "=" +
                        //URLEncoder.encode(rr,"UTF-8")+"&"+
                       // URLEncoder.encode("code","UTF-8")+"="+
                        URLEncoder.encode(contentTxt1, "UTF-8");
                BufferedReader reader = null;
                Log.d("checking", data);
                try {
                    URL url = new URL(LoginActivity.ip + "rr/");
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
            //Toast.makeText(getBaseContext(), "DataBase Updated Successfully", Toast.LENGTH_LONG).show();
            Toast.makeText(getBaseContext(), result, Toast.LENGTH_SHORT).show();
            Update update=new Update();
            update.execute();
        //  new LoginActivity().new BackTask().execute();

//            result = result.trim();
           // setBooks(result);
        }

        @Override
        protected void onPreExecute() {
           // progress.show();
        }

        @Override
        protected void onProgressUpdate(String... text) {
            // progress. For example updating ProgessDialog
        }


    }
    public class Update extends AsyncTask<String, String, String> {

        private String result = null,name,batch,id,dept,dob,phno,add,credits;

        @Override
        protected String doInBackground(String... params) {
            try {
                String data = URLEncoder.encode("username", "UTF-8") + "=" +
                        URLEncoder.encode(LoginActivity.regno1, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" +
                        URLEncoder.encode(LoginActivity.password1, "UTF-8");
                BufferedReader reader = null;
                Log.d("checking", data);
                try {
                    URL url = new URL(LoginActivity.ip + "login/");
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
            if (result != null) {
                //Toast.makeText(getBaseContext(), result, Toast.LENGTH_SHORT).show();
                result = result.trim();
                setBooks(result);
            }
            if (!result.equals("Not loggedin")) {
                Intent intent = new Intent(BarcodeActivity.this, ContentsActivity.class);
                //intent.putExtra("json",result);
                startActivity(intent);
            } else {
                // errorDisplay.setText("Check id / password");
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

        String credit;
    public void setBooks(String s){
        try {
            JSONObject jsonObject = new JSONObject(s);
            SharedPreferences.Editor e = sp.edit();
            /*batch = jsonObject.optString("batch").toString();
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
            credits = jsonObject.getString("credit").toString();*/
            e.commit();

        } catch (JSONException e) {
            //Toast.makeText(getBaseContext(),e.toString(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    }
}

