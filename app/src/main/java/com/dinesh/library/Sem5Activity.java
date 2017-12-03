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

public class Sem5Activity extends Activity {

    String[] sem1subcode={"MA6566","CS6501","CS6502","CS6503","CS6504"};
    String[] sem1bname={"Discrete Mathematics and its Applications","Internet and World Wide Web - How to Program","Applying UML and Patterns: An Introduction to Object-Oriented Analysis and\n" +
            "Design and Iterative Development","Introduction to Automata Theory, Languages and\n" +
            "Computations","Computer Graphics"};
    String[] sem1aname={"Kenneth H.Rosen","Deitel and Deitel and Nieto","Craig Larman","Hopcroft J.E., Motwani R. and Ullman J.D","Donald Hearn and Pauline Baker M,"};
    String[] sem1edition={"7th Edition","5th Edition","3rd Edition","2nd Edition","2nd Edition"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem5);
        ListView books=(ListView)findViewById(R.id.listViewsem5);
        books.setAdapter(new dataListAdapter(sem1bname,sem1aname,sem1edition,sem1subcode));


    }
    class dataListAdapter extends BaseAdapter {
        String[] sem1bname1;
        String[] sem1aname1;
        String[] sem1edition1;
        String[] sem1subcode1;

        dataListAdapter() {
            sem1bname1 = null;
            sem1aname1 = null;
            sem1edition1 = null;
            sem1subcode1=null;
        }

        public dataListAdapter(String[] text1, String[] text2, String[] text3,String[] text4) {
            sem1bname1 = text1;
            sem1aname1 = text2;
            sem1edition1 = text3;
            sem1subcode1=text4;
        }

        public int getCount() {
            // TODO Auto-generated method stub
            return sem1bname1.length;
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

            sem1_b_name.setText(sem1bname1[position]);
            sem1_a_name.setText(sem1aname1[position]);
            sem1_b_edition.setText(sem1edition1[position]);
            sem1_sub_code.setText(sem1subcode1[position]);
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
