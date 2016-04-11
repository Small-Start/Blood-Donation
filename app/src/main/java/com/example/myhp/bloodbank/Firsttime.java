package com.example.myhp.bloodbank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by my hp on 3/2/2016.
 */
public class Firsttime extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodmain);
        int i=getSharedPreferences("firstime",0).getInt("first",0);
        if(i==1){
Intent intent=new Intent(getApplicationContext(),Bloodmain.class);
            startActivity(intent);
        }



        findViewById(R.id.firsttime_login).setOnClickListener(this);
        findViewById(R.id.firsttime_signup).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.firsttime_login:
                Class i = null;
                try {
                    i = Class.forName("com.example.myhp.bloodbank.Login");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                Intent k = new Intent(getApplicationContext(), i);
                startActivity(k);
                break;
            case R.id.firsttime_signup:
                Class l = null;
                try {
                    l = Class.forName("com.example.myhp.bloodbank.Signup");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Intent f = new Intent(getApplicationContext(), l);
                startActivity(f);
                break;
        }
    }
}
