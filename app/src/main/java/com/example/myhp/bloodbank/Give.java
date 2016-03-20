package com.example.myhp.bloodbank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

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
        try {
            name=new JSONArray(name).getString(0);
        } catch (JSONException e) {
            e.printStackTrace();
            name="response error";
        }
        TextView tv=(TextView)v.findViewById(R.id.give_name);
        tv.setText("Hello "+name+"!");

        v.findViewById(R.id.b_search_blood_banks).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class i = null;
                //  Bundle b=new Bundle();
                // b.putString("response",response);
                try {
                    i = Class.forName("com.example.myhp.bloodbank.Bloodbank");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Intent k = new Intent(getContext(), i);
                startActivity(k);
            }
        });
        return v;
    }

    @Override
    public void onClick(View v) {

    }
}