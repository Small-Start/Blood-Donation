package com.example.myhp.bloodbank;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by my hp on 3/12/2016.
 */
public class Userdetails extends AppCompatActivity{
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

        pd=new ProgressDialog();
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();
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
        ((TextView)findViewById(R.id.text_userdetails_contactdetails_email)).setText(email);
        ((TextView)findViewById(R.id.text_userdetails_contactdetails_mobile)).setText(pno);
        ((TextView)findViewById(R.id.tv_user_details_name)).setText(name);
        ((TextView)findViewById(R.id.text_userdetails_bloodgroup)).setText(bloodgroup);
    }
}
