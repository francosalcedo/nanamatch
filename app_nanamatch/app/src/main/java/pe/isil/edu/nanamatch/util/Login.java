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
 * Created by francosalcedo on 12/11/17.
 */

public class Login {

    int login_status;
    JSONObject login_data;
    LoginListener listener;



    public void loginResult(String data)
    {
        try{
            JSONObject datax = new JSONObject(data.toString());

            this.login_status = datax.getInt("status");

            if(this.login_status == 1){
                this.login_data = datax.getJSONObject("data");
            }

            if(listener != null){
                listener.onLoginIntent(this);
            }


        }catch (JSONException e){
            e.printStackTrace();
            Log.d("Error", "Al leer el json respondido -LOGIN- " + data.toString());
        }

    }

    public int returnLoginSatus()
    {
        return this.login_status;
    }

    public JSONObject returnLoginData()
    {
        return this.login_data;
    }

    public void addConnectionListener(LoginListener l){
        this.listener = l;
    }

    public JSONObject login(Context contextThis, final String email, final String password)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GlobalConstants.API_URL + GlobalConstants.API_METHOD_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Enviado la respuesta al pre-listener
                        loginResult(response);
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
