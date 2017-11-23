package pe.isil.edu.nanamatch.util;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import pe.isil.edu.nanamatch.util.GlobalConstants;

/**
 * Created by porlaswebases on 17/11/17.
 */

public class Register {

    int register_status;
    JSONObject register_data;
    RegisterListener listener;


    public void registerResult(String data)
    {
        try{
            JSONObject datax = new JSONObject(data.toString());

            this.register_status   = datax.getInt("status");

            if(this.register_status == 1){
                this.register_data = datax.getJSONObject("data");
            }

            if(listener != null){
                listener.onRegisterIntent(this);
            }

        }catch (JSONException e){
            e.printStackTrace();
            Log.d("Error", "Al leer el json respondido -REGISTER- " + data.toString());
        }

    }

    public int returnRegisterSatus()
    {
        return this.register_status;
    }

    public JSONObject returnRegisterData()
    {
        return this.register_data;
    }

    public void addConnectionListener(RegisterListener l){
        this.listener = l;
    }

    public JSONObject register(Context contextThis, final String email, final String password, final String name, final String last_name, final String gender
    ,final String address, final String phone_number, final String id_distrit)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GlobalConstants.API_URL + GlobalConstants.API_METHOD_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Enviado la respuesta al pre-listener
                        registerResult(response);
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
                params.put("email", email);
                params.put("password", password);
                params.put("name", name);
                params.put("last_name", last_name);
                params.put("gender",gender);
                params.put("address", address);
                params.put("phone_number", phone_number);
                params.put("id_distrit",id_distrit);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(contextThis); // Conexto
        requestQueue.add(stringRequest);

        return null;
    }










    /*

    public void Connect(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_API,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject data = new JSONObject(response.toString());
                            String login_msj = data.getString("msj");
                            JSONObject login_data = data.getJSONObject("data");

                            //String nombre_login = reader.

                            if(data.getInt("status") == 1){
                                mTextView.setText("Bienvenido: "+ login_data.getString("name"));
                            }else{
                                mTextView.setText("fallido");
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Log.d("Error", "Al leer el json respondido" + response.toString());
                        }
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
                params.put("email","felix@felix.com");
                params.put("password","hola");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this); // Conexto
        requestQueue.add(stringRequest);
    }
    */

}
