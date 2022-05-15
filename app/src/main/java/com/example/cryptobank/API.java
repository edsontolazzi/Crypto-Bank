package com.example.cryptobank;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cryptobank.tools.CustomToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class API {
    public static String API_URL = "http://cryptobank.lucassilva.dev.br/api/";
    RequestQueue queue;

    private Context context;
    private Integer numberAccount = null;
    private String token = null;

    public API(Context context) {
        this.context = context;
        this.queue = Volley.newRequestQueue(context);
    }

    public API(Context context, Integer numberAccount, String token) {
        this.context = context;
        this.queue = Volley.newRequestQueue(context);

        this.numberAccount = numberAccount;
        this.token = token;
    }

    private void erroAPI(String mensagem) {
        CustomToast.showToast(mensagem, context);
    }

    public void authentication(MainActivity activity, String username, String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API.API_URL + "authentication", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                activity.setJsonDadosCliente(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                erroAPI(error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Accept","application/json");
                params.put("Content-Type","application/json");
                params.put("Authorization","Basic " + Base64.encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8), Base64.DEFAULT));

                return params;
            }
        };

        this.queue.add(stringRequest);
    }

    public void getBalance(HomeActivity activity) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API.API_URL + "account/balance/" + this.numberAccount, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                activity.setSaldo(response);
                Log.d("BALANCE", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                erroAPI(error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Accept","application/json");
                params.put("Content-Type","application/json");

                return params;
            }

            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            public byte[] getBody() throws AuthFailureError {
                try {
                    JSONObject json = new JSONObject();
                    json.put("token", token);
                    return json.toString().getBytes(StandardCharsets.UTF_8);
                } catch (JSONException uee) {
                    return null;
                }
            }
        };

        this.queue.add(stringRequest);
    }

}
