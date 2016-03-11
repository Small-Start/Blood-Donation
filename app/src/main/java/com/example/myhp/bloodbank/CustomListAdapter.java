package com.example.myhp.bloodbank;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by my hp on 2/7/2016.
 */
public class CustomListAdapter extends ArrayAdapter {

    private Context mContext;
    private int id;
    private List<HashMap<String,String>> items ;

    public CustomListAdapter(Context context, int textViewResourceId, List<HashMap<String,String>> list)
    {
        super(context, textViewResourceId, list);
        mContext = context;
        id = textViewResourceId;
        items = list ;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent)
    {
        View mView = v ;
        if(mView == null){
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(id, null);
        }

        TextView text = (TextView) mView.findViewById(R.id.custom_tv_list);
        TextView tv=(TextView)mView.findViewById(R.id.tv_custom_distance);
        HashMap<String,String> hash=new HashMap<>();


        if(items.get(position) != null )
        {
            hash=items.get(position);
            text.setTextColor(Color.BLACK);
            text.setText(hash.get("name"));
            tv.setTextColor(Color.BLACK);
            tv.setText(hash.get("km"));
            // int color = Color.argb(200, 255, 64, 64);
            // text.setBackgroundColor( color );

        }


        return mView;
    }

}

