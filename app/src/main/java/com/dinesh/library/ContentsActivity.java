package com.dinesh.library;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class ContentsActivity extends Activity {
    LayoutInflater layoutInflater;
    Button retn,renewal;

    String data = "";
    String[] bname = new String[6];
    String[] aname = new String[6];
    String[] date = new String[6];
     SharedPreferences sp;


    Activity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);
        Intent content = getIntent();
        sp = getSharedPreferences("MyProfile", MODE_PRIVATE);


       /* ListView books=(ListView)findViewById(R.id.listView);
        books.setAdapter(new dataListAdapter(bname,aname,date));*/
        ImageButton userprofile = (ImageButton) findViewById(R.id.Btnuserprofile);
        retn=(Button)findViewById(R.id.Btnreturn) ;
        TextView name = (TextView) findViewById(R.id.TVusername);
        String user_name = sp.getString("name", "kkkkkkkk");
        //TextView tx = (TextView)findViewById(R.id.tVdob);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/segoeui.ttf");

        name.setTypeface(custom_font);

        name.setText(user_name);

        userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile = new Intent(ContentsActivity.this, ProfileActivity.class);
                startActivity(profile);
            }
        });
        renewal = (Button) findViewById(R.id.Btnrenewal);
        renewal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent barcode = new Intent(ContentsActivity.this, BarcodeActivity.class);
                String ss="ren";
                barcode.putExtra("rr",ss);
                startActivity(barcode);

            }
        });
        retn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent barcode = new Intent(ContentsActivity.this, BarcodeActivity.class);
                String ss="ret";
                barcode.putExtra("rr",ss);
                startActivity(barcode);

            }
        });
        String user="{\"name\":\"shiva\",\"batch\":\"2010-2014\",\"id\":\"root\",\"dept\":\"cse\",\"dob\":\"8-04-1996\",\"phno\":\"89898989898\",\"address\":\"seome addres\"}";
        String strJson = "{" +
                "\"arr\" :[" + "{" + "\"title\":\"Introduction to C Programming\"," + "\"author\":\"Kenith Jordan\"," + "\"date\":\"10-02-2016\"," + " \"accno\":\"15674A\"" + "            }," + "            {" + "                \"title\":\"Complete JAVA Reference\"," + "                \"author\":\"Herbert Schildt\"," + "                \"date\":\"17-09-1996\"," + "                \"accno\":\"15673A\"" + "            }" + "            ]" + "        }";
        new BackTask().execute();

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("key",user);
        editor.commit();
  //      setBooks(strJson);
    }

    private String getMonth(String date){
        String d[] = date.split("-");
        try{
        switch (d[1]){
            case "01" : return "JAN " + d[0];
                        //break;
            case "02" : return "FEB " + d[0];// break;
            case "03" : return "MAR " + d[0]; //break;
            case "04" : return "APR " + d[0];// break;
            case "05" : return "MAY " + d[0]; //break;
            case "06" : return "JUN " + d[0]; //break;
            case "07" : return "JUL " + d[0]; //break;
            case "08" : return "AUG " + d[0]; //break;
            case "09" : return "SEP " + d[0]; //break;
            case "10" : return "OCT " + d[0]; //break;
            case "11" : return "NOV " + d[0];// break;
            case "12" : return "DEC " + d[0]; //break;
        }}catch (ArrayIndexOutOfBoundsException e){e.printStackTrace();
            Toast.makeText(getBaseContext(),e.toString(),Toast.LENGTH_LONG).show();}
        return "";
    }
    public void setBooks(String strJson){
       // Toast.makeText(getBaseContext(),"hfdgdgd"+strJson,Toast.LENGTH_LONG).show();
        try {
            JSONObject jsonRootObject = new JSONObject(strJson);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("arr");


            //Iterate the jsonArray and print the info of JSONObjects
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String accno = jsonObject.optString("accno").toString();
                bname[i] = jsonObject.optString("title").toString();
                aname[i] = jsonObject.optString("author").toString();
                date[i] = jsonObject.getString("date").toString();
                date[i] = getMonth(date[i]);
//                Toast.makeText(getBaseContext(),"hfdgdgd"+bname[i],Toast.LENGTH_LONG).show();

            }

        } catch (JSONException e) {
            Toast.makeText(getBaseContext(),e.toString(),Toast.LENGTH_LONG).show();}
        ListView books = (ListView) findViewById(R.id.listView);
        books.setAdapter(new dataListAdapter(bname, aname, date));

    }
    private class BackTask extends AsyncTask<String, String, String> {
        private SharedPreferences sp;
        private String result = null;

        @Override
        protected String doInBackground(String... params) {
            try {
                sp = getSharedPreferences("MyProfile", MODE_PRIVATE);
                String user_idnumber = sp.getString("id", "");
                String data = URLEncoder.encode("id", "UTF-8") + "=" +
                        URLEncoder.encode(user_idnumber, "UTF-8");
                BufferedReader reader = null;
                Log.d("checking",data);
                try {
                    URL url = new URL(LoginActivity.ip+"getBD/");
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
//            result = result.trim();
           // Toast.makeText(getBaseContext(),result,Toast.LENGTH_SHORT).show();
            if (result != null)
            setBooks(result);
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(String... text) {
            // progress. For example updating ProgessDialog
        }


    }
    class dataListAdapter extends BaseAdapter {
        String[] bname1;
        String[] aname1;
        String[] date1;

        dataListAdapter() {
            bname1 = null;
            aname1 = null;
            date1 = null;
        }

        public dataListAdapter(String[] text1, String[] text2, String[] text3) {
            bname1 = text1;
            aname1 = text2;
            date1 = text3;
        }

        public int getCount() {
            // TODO Auto-generated method stub
            return bname1.length;
        }

        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            View row;
            row = inflater.inflate(R.layout.bookdetails, parent, false);
            TextView b_name, a_name, b_date;
            b_name = (TextView) row.findViewById(R.id.bookname);
            a_name = (TextView) row.findViewById(R.id.author);
            b_date = (TextView) row.findViewById(R.id.date);
            Typeface face = Typeface.createFromAsset(getAssets(),  "fonts/segoeui.ttf");
            b_name.setTypeface(face);
            a_name.setTypeface(face);
            b_date.setTypeface(face);
            b_name.setText(bname1[position]);
            a_name.setText(aname1[position]);
             /* if(position==0)
                row.setBackgroundColor(Color.parseColor("#424242"));

            if(position==1)
                row.setBackgroundColor(Color.parseColor("#616161"));
            if(position==2)
                row.setBackgroundColor(Color.parseColor("#757575"));
            if(position==3)
                row.setBackgroundColor(Color.parseColor("#9e9e9e"));
            if(position==4)
                row.setBackgroundColor(Color.parseColor("#bdbdbd"));
            if(position==5)
                row.setBackgroundColor(Color.parseColor("#e0e0e0"));*/
b_date.setText(date1[position]);
           /* if(position==0)
                row.setBackgroundColor(Color.parseColor("#424242"));

            if(position==1)
                row.setBackgroundColor(Color.parseColor("#616161"));
            if(position==2)
                row.setBackgroundColor(Color.parseColor("#757575"));
            if(position==3)
                row.setBackgroundColor(Color.parseColor("#9e9e9e"));
            if(position==4)
                row.setBackgroundColor(Color.parseColor("#bdbdbd"));
            if(position==5)
                row.setBackgroundColor(Color.parseColor("#e0e0e0"));*/


            return (row);
        }

    }


}