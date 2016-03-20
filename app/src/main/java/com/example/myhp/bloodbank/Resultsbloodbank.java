package com.example.myhp.bloodbank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by my hp on 3/21/2016.
 */
public class Resultsbloodbank extends AppCompatActivity implements AdapterView.OnItemClickListener{

  ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_bloodbank);
        pd=new ProgressDialog(this,ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Loading Results");
        pd.show();
        String city=getIntent().getExtras().getString("city");
        String url="https://data.gov.in/api/datastore/resource.json?resource_id=e16c75b6-7ee6-4ade-8e1f-2cd3043ff4c9&api-key=86771c8650a6363eabbbdad2aeca7a39&filters[city]="+city+"&fields=state,city,address,contact,h_name";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        try {
                            setlist(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pd.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        pd.hide();
                        Toast.makeText(getApplicationContext(), "its not working"+error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setShouldCache(false);
        requestQueue.add(stringRequest);

        int socketTimeout = 20000;//20 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);

    }

    private void setlist(String response) throws JSONException {
        ListView lv=(ListView)findViewById(R.id.listview_searchresult_blood_bank);
        JSONObject jo= null;
        JSONArray jr=null;
        try {
            jo = new JSONObject(response);
            if(jo.getBoolean("success"))
            jr=jo.getJSONArray("records");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        if(jo.getBoolean("success")) {

            if (jr.length() != 0) {
                for (int i = 0; i < jr.length(); i++) {
                    JSONObject ob = null;
                    HashMap<String, String> map = new HashMap<>();
                    try {
                        ob = jr.getJSONObject(i);

                        map.put("address", ob.getString("address"));
                        map.put("contact", ob.getString("contact") + "\n" + ob.getString("h_name"));


                        list.add(map);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            } else {
                HashMap<String, String> map = new HashMap<>();
                map.put("address", "Aw Snap! We could not find any blood banks near you! Try contacting Blood Banks near you!");

                map.put("contact", "");
                list.add(map);
            }
        }
        else
        {
            HashMap<String, String> map = new HashMap<>();
            map.put("address", "Aw Snap! We could not find any blood banks in the specified area!");

            map.put("contact", "");
            list.add(map);
        }
        ArrayAdapter items=new CustomListAdapterbank(getApplicationContext(),R.layout.listitem_bloodbank,list,jo.getBoolean("success"));
        lv.setAdapter(items);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
