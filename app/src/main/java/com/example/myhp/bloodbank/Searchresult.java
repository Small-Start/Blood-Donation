package com.example.myhp.bloodbank;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
public class Searchresult extends AppCompatActivity{
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
        HashMap<String,String> map=new HashMap<>();
        for(int i=0;i<jr.length();i++)
        {
            JSONObject ob= null;
            try {
                ob = jr.getJSONObject(i);
                map.put("name",ob.getString("name"));
                map.put("km",ob.getString("km"));
                list.add(map);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        ArrayAdapter items=new CustomListAdapter(getApplicationContext(),R.layout.custom_list,list);
        lv.setAdapter(items);
    }
}
