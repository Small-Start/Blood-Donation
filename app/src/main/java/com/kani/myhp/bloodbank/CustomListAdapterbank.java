package com.kani.myhp.bloodbank;

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
 * Created by my hp on 3/21/2016.
 */
public class CustomListAdapterbank  extends ArrayAdapter {

    private Context mContext;
    private int id;
    boolean success;
    protected List<HashMap<String,String>> items ;

    public CustomListAdapterbank(Context context, int textViewResourceId, List<HashMap<String,String>> list,boolean success)
    {
        super(context, textViewResourceId, list);
        mContext = context;
        this.success=success;
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
TextView name=(TextView)mView.findViewById(R.id.textView_bloodbank_name);
        TextView address=(TextView)mView.findViewById(R.id.textView_bloodbank_address);
        TextView contact=(TextView)mView.findViewById(R.id.textView_bloodbank_contact);


        HashMap<String,String> hash=new HashMap<>();


        if(items.get(position) != null&& success)
        {
            hash=items.get(position);
            String s[]=hash.get("contact").split("\n");
            name.setTextColor(Color.BLACK);
            name.setText(s[1]);
            address.setTextColor(Color.BLACK);
            address.setText(hash.get("address"));
            contact.setTextColor(Color.BLACK);
            contact.setText(s[0]);
            // int color = Color.argb(200, 255, 64, 64);
            // text.setBackgroundColor( color );

        }
        else {
            address.setTextColor(Color.BLACK);
            address.setText("could not load data!");
            name.setText("");
            contact.setText("");
        }


        return mView;
    }

}

