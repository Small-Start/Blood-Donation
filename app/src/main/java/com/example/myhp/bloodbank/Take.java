package com.example.myhp.bloodbank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by my hp on 3/2/2016.
 */
 public class Take extends Fragment implements View.OnClickListener,AdapterView.OnItemSelectedListener{
    String result[]={"A+","A-","B+","B-","O+","O-","AB+","AB-"};
    String bloodgroup;
 @Nullable
 @Override
 public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){

        View v=inflater.inflate(R.layout.take,container,false);
Spinner sp=(Spinner)v.findViewById(R.id.spinner_take);
     ArrayAdapter<String> arr=new ArrayAdapter<String>(getContext(),R.layout.spinner_item,result);
     sp.setAdapter(arr);
     bloodgroup=result[0];
        return v;
        }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        bloodgroup=result[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}