package com.dinesh.library;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by DINESH on 30-Aug-16.
 */
public class Second extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.second, container, false);

            //TextView tv = (TextView) v.findViewById(R.id.tvFragSecond);
            //tv.setText(getArguments().getString("msg"));

            return v;
        }

        public static Second newInstance(String text) {

            Second f = new Second();
            Bundle b = new Bundle();
            b.putString("msg", text);

            f.setArguments(b);

            return f;
        }
}
