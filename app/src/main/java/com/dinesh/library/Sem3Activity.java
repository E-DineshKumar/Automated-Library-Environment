package com.dinesh.library;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Sem3Activity extends Activity {
    String[] sem3subcode={"MA6351","CS6301","CS6302","CS6303","CS6304","GE6351"};

    String[] sem3bname={"Transforms and Partial Differential Equations","Data Structures and Algorithm Analysis in C","Fundamentals of Database Systems","Computer Organization and Design","Advanced Electronic Communication Systems","Introduction to Environmental Engineering and Science"};
    String[] sem3aname={"Veerarajan","Mark Alllen Weiss","Ramez Elmasri and Shamkant B. Navathe","David A. Patterson and John L.Hennessey.","Wayne Tomasi","Gilbert M.Masters"};
    String[] sem3edition={"2nd Edition","2nd Edition","5th Edition","5th Edition","6th Edition","2nd Edition"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem3);
        ListView books=(ListView)findViewById(R.id.listViewsem3);
        books.setAdapter(new dataListAdapter(sem3bname,sem3aname,sem3edition,sem3subcode));
    }
    class dataListAdapter extends BaseAdapter {
        String[] sem2bname1;
        String[] sem2aname1;
        String[] sem2edition1;
        String[] sem2subcode1;

        dataListAdapter() {
            sem2bname1 = null;
            sem2aname1 = null;
            sem2edition1 = null;
            sem2subcode1=null;
        }

        public dataListAdapter(String[] text1, String[] text2, String[] text3,String[] text4) {
            sem2bname1 = text1;
            sem2aname1 = text2;
            sem2edition1 = text3;
            sem2subcode1=text4;
        }

        public int getCount() {
            // TODO Auto-generated method stub
            return sem2bname1.length;
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
            row = inflater.inflate(R.layout.sem1, parent, false);
            TextView sem1_b_name, sem1_a_name, sem1_b_edition,sem1_sub_code;
            sem1_b_name = (TextView) row.findViewById(R.id.tVBookname);
            sem1_a_name = (TextView) row.findViewById(R.id.tVauthorname);
            sem1_b_edition = (TextView) row.findViewById(R.id.tVEdition);
            sem1_sub_code = (TextView) row.findViewById(R.id.tVsubcode);

            Typeface face = Typeface.createFromAsset(getAssets(),  "fonts/segoeui.ttf");

            sem1_a_name.setTypeface(face);
            sem1_b_edition.setTypeface(face);
            sem1_b_name.setTypeface(face);
            sem1_sub_code.setTypeface(face);

            sem1_b_name.setText(sem2bname1[position]);
            sem1_a_name.setText(sem2aname1[position]);
            sem1_b_edition.setText(sem2edition1[position]);
            sem1_sub_code.setText(sem2subcode1[position]);
            /*if(position==0)
                row.setBackgroundColor(Color.parseColor("#6a1b9a"));
            if(position==1)
                row.setBackgroundColor(Color.parseColor("#7b1fa2"));
            if(position==2)
                row.setBackgroundColor(Color.parseColor("#8e24aa"));
            if(position==3)
                row.setBackgroundColor(Color.parseColor("#9c27b0"));
            if(position==4)
                row.setBackgroundColor(Color.parseColor("#ab47bc"));
            if(position==5)
                row.setBackgroundColor(Color.parseColor("#ba68c8"));
*/
            return (row);
        }

    }
}
