package com.example.myhp.bloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by my hp on 3/11/2016.
 */
public class Searchresult extends AppCompatActivity implements AdapterView.OnItemClickListener{
String response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresults);
ListView lv=(ListView)findViewById(R.id.listview_searchresult);
        response=getIntent().getExtras().getString("response");
        JSONArray jr= null;
        try {
            jr = new JSONArray(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<HashMap<String,String>> list=new ArrayList<>();
if(jr.length()!=0){
        for(int i=0;i<jr.length();i++)
        {
            JSONObject ob= null;
            HashMap<String,String> map=new HashMap<>();
            try {
                ob = jr.getJSONObject(i);
                map.put("name", ob.getString("name"));

                map.put("km", String.format("%.2f", Float.parseFloat(ob.getString("km"))));
                list.add(map);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
}
        else {
    HashMap<String, String> map = new HashMap<>();
    map.put("name", "Aw Snap! We could not find any donors for you! Try contacting Blood Banks near you!");
    map.put("km", "large");
    list.add(map);
}
        ArrayAdapter items=new CustomListAdapter(getApplicationContext(),R.layout.custom_list,list);
        lv.setAdapter(items);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Class i = null;
        Bundle b=new Bundle();
        b.putString("response",response);
        b.putInt("position",position);
        try {
            i = Class.forName("com.example.myhp.bloodbank.Userdetails");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Intent k = new Intent(getApplicationContext(), i);
        k.putExtras(b);
        startActivity(k);
    }
}
