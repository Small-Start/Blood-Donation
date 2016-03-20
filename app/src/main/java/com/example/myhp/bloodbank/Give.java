package com.example.myhp.bloodbank;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by my hp on 3/2/2016.
 */
public class Give extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.give,container,false);
        SharedPreferences sf=getActivity().getSharedPreferences("userinfo",0);
        String name=sf.getString("response", "no response");
        TextView tv=(TextView)v.findViewById(R.id.give_name);
        tv.setText("Hello "+name+"!");
        return v;
    }

    @Override
    public void onClick(View v) {

    }
}