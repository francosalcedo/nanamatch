package com.francosalcedo.perfecthttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView mTextView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.xx);

        btn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.xx:

            mTextView =   (TextView) findViewById(R.id.text);


                final String URL_API = "http://nanamatch.ml/login";


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

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);





                break;

        }
    }


}
