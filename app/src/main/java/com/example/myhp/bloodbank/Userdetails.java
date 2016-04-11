package com.example.myhp.bloodbank;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by my hp on 3/12/2016.
 */
public class Userdetails extends AppCompatActivity implements View.OnClickListener {
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
        Toolbar t = (Toolbar) findViewById(R.id.toolbar_userdetails);
        setSupportActionBar(t);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //  pd=new ProgressDialog(getApplicationContext());
        // pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // pd.show();
        response = getIntent().getExtras().getString("response");
        position=getIntent().getExtras().getInt("position");
        JSONArray jr = null;
        try {
            jr = new JSONArray(response);
            JSONObject jo = jr.getJSONObject(position);
            pno = jo.getString("pno");
            email = jo.getString("email");
            bloodgroup = jo.getString("bloodgroup");
            name = jo.getString("name");
            distance = jo.getString("km");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        double km = Double.parseDouble(distance);
        distance = String.format("%.2f", km);
        ((TextView) findViewById(R.id.text_userdetails_contactdetails_email)).setText(email);
        ((TextView) findViewById(R.id.text_userdetails_contactdetails_mobile)).setText(pno);
        ((TextView) findViewById(R.id.tv_user_details_name)).setText(name);
        ((TextView) findViewById(R.id.text_userdetails_bloodgroup)).setText(bloodgroup);
        ((TextView) findViewById(R.id.text_userdetails_distance)).setText(distance);
        //  pd.hide();
        findViewById(R.id.button_userdetails_call).setOnClickListener(this);
        findViewById(R.id.button_userdetails_message).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_userdetails_call) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + pno));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(intent);
        }
        else
        {
         //   startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("Hello! I'm "+name +", I'm in need of a blood donor! Can you help me out?", pno, null)));
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);

            smsIntent.setData(Uri.parse("smsto:"));
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address", pno);
            smsIntent.putExtra("sms_body"  , "Hello! I'm "+name +", I'm in need of a blood donor! Can you help me out?");
            startActivity(smsIntent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
