package com.example.phonestore.activity;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Connect_sv {
    public void insert_volley_POST(Context context, TextView tvKQ,
                                   EditText txtName, EditText txtPrice, EditText txtDes)  {
        //b1. Tao request
        RequestQueue queue  = Volley.newRequestQueue(context);
        //b2. url
        String url = "https://nvdat250902.000webhostapp.com/phonestore/register_db.php";
        //b3. truyen tham so
        //stringRequest(method,url,ThanhCOng,ThatBai)
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tvKQ.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());
            }
        })
        {
            //truyen tham so
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> myData = new HashMap<>();
                myData.put("username",txtName.getText().toString());
                myData.put("password",txtPrice.getText().toString());
                myData.put("address",txtDes.getText().toString());
                return myData;
            }
        };
        //b4. xu ly
        queue.add(stringRequest);
    }
    public void login_volley_POST(Context context, TextView tvKQ,
                                   EditText txtName, EditText txtPrice)  {
        //b1. Tao request
        RequestQueue queue  = Volley.newRequestQueue(context);
        //b2. url
        String url = "https://nvdat250902.000webhostapp.com/phonestore/login_db.php";
        //b3. truyen tham so
        //stringRequest(method,url,ThanhCOng,ThatBai)
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tvKQ.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());
            }
        })
        {
            //truyen tham so
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> myData = new HashMap<>();
                myData.put("username",txtName.getText().toString());
                myData.put("password",txtPrice.getText().toString());
                return myData;
            }
        };
        //b4. xu ly
        queue.add(stringRequest);
    }
}
