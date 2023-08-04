package com.example.duduhgee;

import static com.example.duduhgee.RegisterRequest.getPinnedCertSslSocketFactory;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

public class LoginRequest extends StringRequest {
    //서버 URL 설정(php파일 연동)
    final static private String URL = "https://192.168.0.2:443/Login.php";
    //집: 192.168.45.185
    //final static private String URL = "https://591f-211-227-109-84.ngrok.io/Login.php";
    private Map<String ,String >map;

    public LoginRequest(String userID, String userPassword, Response.Listener<String> listener, Context context) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        super(Method.POST, URL, listener, null);

        // SSL 연결 설정
        SSLSocketFactory sslSocketFactory = getPinnedCertSslSocketFactory(context, R.raw.bpmserver);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);

        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });

        map= new HashMap<>();
        map.put("userID",userID);
        map.put("userPassword", userPassword);

    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
