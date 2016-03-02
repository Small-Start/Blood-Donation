package com.example.myhp.bloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * Created by my hp on 3/2/2016.
 */
public class Signup extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Toolbar t=(Toolbar)findViewById(R.id.toolbar_signup);
        setSupportActionBar(t);
        String result[]={"A","B"};
        ArrayAdapter<String> arr=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,result);
        Spinner sp=(Spinner)findViewById(R.id.spinner_signup);
        sp.setAdapter(arr);
        findViewById(R.id.bsignup).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bsignup:
                Class i = null;
                try {
                    i = Class.forName("com.example.myhp.bloodbank.Bloodmain");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Intent k = new Intent(getApplicationContext(), i);
                startActivity(k);
                break;
        }
    }
}
