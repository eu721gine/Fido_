package com.example.duduhgee;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class BuyRequest extends StringRequest {
    final static private String URL = "https://192.168.0.2:443/SendChallenge.php";

    public BuyRequest(Response.Listener<String> listener){
        super(Method.GET, URL, listener, null);
    }

}