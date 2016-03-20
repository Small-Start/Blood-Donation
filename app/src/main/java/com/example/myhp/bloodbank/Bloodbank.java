package com.example.myhp.bloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;

/**
 * Created by my hp on 3/21/2016.
 */
public class Bloodbank extends AppCompatActivity{

    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bloodbank);
        View v=findViewById(R.id.blood_bank_layout_main);

findViewById(R.id.b_bloodbank_next).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String result = ((EditText) findViewById(R.id.editText_cityname)).getText().toString();
        StringBuilder sb = new StringBuilder(result);
        if (!(result.equals("")||result.length()==0)) {

            if (result.charAt(0) >= (char) 97 && result.charAt(0) <= (char) 122)
                sb.setCharAt(0, (char) (result.charAt(0) - (char) 32));
            Class i = null;
            Bundle b = new Bundle();
            // b.putString("response",response);
            try {
                i = Class.forName("com.example.myhp.bloodbank.Resultsbloodbank");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            b.putString("city", sb.toString());
            Intent k = new Intent(getApplicationContext(), i);
            k.putExtras(b);
            startActivity(k);
        }
        else{
            Snackbar.make(v,"Enter the city name!",Snackbar.LENGTH_LONG).show();
        }
    }
});

    }
}
