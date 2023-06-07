package com.example.registerlogin;

import androidx.annotation.MenuRes;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class BuyRequest extends StringRequest{

    final static private String URL = "http://192.168.0.7:8888/Sendchallenge.php";

    //private Map<String ,String > map;

    public BuyRequest(Response.Listener<String> listener){
        super(Method.GET, URL, listener, null);

    }


}
