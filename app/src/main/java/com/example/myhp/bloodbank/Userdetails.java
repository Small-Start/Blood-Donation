package com.example.myhp.bloodbank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by my hp on 3/12/2016.
 */
public class Userdetails extends AppCompatActivity implements View.OnClickListener{
String response;
    String bloodgroup;
    String email;
    String pno;
    String name;
    int position;
    String distance;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdetails);
Toolbar t=(Toolbar)findViewById(R.id.toolbar_userdetails);
        setSupportActionBar(t);
      //  pd=new ProgressDialog(getApplicationContext());
       // pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
       // pd.show();
        response=getIntent().getExtras().getString("response");
        JSONArray jr= null;
        try {
            jr = new JSONArray(response);
            JSONObject jo=jr.getJSONObject(position);
            pno=jo.getString("pno");
            email=jo.getString("email");
            bloodgroup=jo.getString("bloodgroup");
            name=jo.getString("name");
            distance=jo.getString("km");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        double km=Double.parseDouble(distance);
        distance=String.format("%.2f",km);
                ((TextView) findViewById(R.id.text_userdetails_contactdetails_email)).setText(email);
        ((TextView)findViewById(R.id.text_userdetails_contactdetails_mobile)).setText(pno);
        ((TextView)findViewById(R.id.tv_user_details_name)).setText(name);
        ((TextView)findViewById(R.id.text_userdetails_bloodgroup)).setText(bloodgroup);
        ((TextView)findViewById(R.id.text_userdetails_distance)).setText(distance);
      //  pd.hide();
    findViewById(R.id.button_userdetails_call).setOnClickListener(this);
        findViewById(R.id.button_userdetails_message).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_userdetails_call){
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + pno));
            startActivity(intent);
        }
        else
        {

        }
    }
}
