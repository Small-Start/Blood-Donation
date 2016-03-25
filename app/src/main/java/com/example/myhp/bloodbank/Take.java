package com.example.myhp.bloodbank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by my hp on 3/2/2016.
 */
 public class Take extends Fragment implements View.OnClickListener,AdapterView.OnItemSelectedListener{
    String result[]={"A+","A-","B+","B-","O+","O-","AB+","AB-"};
    String bloodgroup,username;
    ProgressDialog pd;
    View view;
 @Nullable
 @Override
 public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){

        view=inflater.inflate(R.layout.take,container,false);
     view.findViewById(R.id.bsearch).setOnClickListener(this);
Spinner sp=(Spinner)view.findViewById(R.id.spinner_take);
     ArrayAdapter<String> arr=new ArrayAdapter<String>(getContext(),R.layout.spinner_item,result);
     sp.setAdapter(arr);
     pd=new ProgressDialog(getContext(),ProgressDialog.STYLE_SPINNER);
     pd.setMessage("Finding people near you....");
     bloodgroup=result[0];
     username=getActivity().getSharedPreferences("user",0).getString("username","hi");
        return view;
        }


    @Override
    public void onClick(final View v) {
        String s=((EditText)view.findViewById(R.id.edit_name_patient)).getText().toString();
        if(!s.equals("")) {
            pd.show();
            String url = "http://bloodbanksys.esy.es/bloodbank/blood.php";
            // Toast.makeText(this,mLastLocation.getLatitude()+" "+mLastLocation.getLongitude(),Toast.LENGTH_LONG).show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                            Class i = null;
                            Bundle b = new Bundle();
                            b.putString("response", response);
                            try {
                                i = Class.forName("com.example.myhp.bloodbank.Searchresult");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            Intent k = new Intent(getContext(), i);
                            k.putExtras(b);
                            pd.hide();
                            startActivity(k);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want
                            //  Toast.makeText(getContext(), "its not working", Toast.LENGTH_LONG).show();
                            pd.hide();
                            Snackbar.make(v, "Check Your Connection", Snackbar.LENGTH_LONG);
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request
                    params.put("username", username);
                    params.put("bloodgroup", bloodgroup);

                    //returning parameter
                    return params;
                }
            };

            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);

            int socketTimeout = 20000;//30 seconds - change to what you want
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            stringRequest.setRetryPolicy(policy);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        bloodgroup=result[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}