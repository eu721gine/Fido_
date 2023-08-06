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

public class SavePKRequest extends StringRequest {
    private static final String URL = "https://192.168.0.2:443/SavePK.php";
    private final Map<String, String> map;

    public SavePKRequest(String publicKey, String userID, Response.Listener<String> listener, Response.ErrorListener errorListener, Context context) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        super(Method.POST, URL, listener, errorListener);

        SSLSocketFactory sslSocketFactory = getPinnedCertSslSocketFactory(context, R.raw.bpmserver);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);

        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("publicKey", publicKey);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

