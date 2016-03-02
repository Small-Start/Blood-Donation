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
public class Login extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Toolbar t=(Toolbar)findViewById(R.id.toolbar_login);
        setSupportActionBar(t);
findViewById(R.id.b_login).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.b_login:
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
