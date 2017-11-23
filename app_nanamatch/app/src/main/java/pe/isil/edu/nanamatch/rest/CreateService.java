package pe.isil.edu.nanamatch.rest;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import pe.isil.edu.nanamatch.util.ApiCallback;
import pe.isil.edu.nanamatch.util.GlobalConstants;

/**
 * Created by francosalcedo on 23/11/17.
 */

public class CreateService {

    public void createService(Context contextThis, final String id_client, final String id_nana, final String comment, final ApiCallback callback)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GlobalConstants.API_URL + GlobalConstants.API_METHOD_CREATE_SERVICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", "no hubo respuesta del servidor");
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("id_client", id_client);
                params.put("id_nana", id_nana);
                params.put("comment", comment);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(contextThis);
        requestQueue.add(stringRequest);
    }
}
