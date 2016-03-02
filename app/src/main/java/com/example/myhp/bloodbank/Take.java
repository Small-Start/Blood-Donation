package com.example.myhp.bloodbank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by my hp on 3/2/2016.
 */
 public class Take extends Fragment implements View.OnClickListener{
 @Nullable
 @Override
 public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){

        View v=inflater.inflate(R.layout.take,container,false);

        return v;
        }


    @Override
    public void onClick(View v) {

    }
}