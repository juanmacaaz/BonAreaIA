package com.example.clientnotificator;

import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class APICheck extends Thread{

    private PrestatgeAdabter pa;
    private RequestQueue     rq;
    private String           url;
    private boolean stop;
    public APICheck(PrestatgeAdabter pa, RequestQueue rq, String url) {
        this.pa = pa;
        this.rq = rq;
        this.url = url;
        stop = false;
    }

    @Override
    public void interrupt() {
        super.interrupt();
        stop = true;
    }

    public void run() {
        while(!stop) {
             JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                List<PrestatgeView> listTest = new ArrayList<>();
                                String prestatge = response.getString("Prestatge");
                                String ubicacion = response.getString("Ubicacion");
                                int Void         = response.getInt("void");
                                listTest.add(new PrestatgeView("Prestatge" + prestatge, "Ubicacion" + ubicacion, Void));
                                pa.updateData(listTest);
                                pa.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });
            rq.add(jsonObjectRequest);
            SystemClock.sleep(1000);
        }
    }
}
