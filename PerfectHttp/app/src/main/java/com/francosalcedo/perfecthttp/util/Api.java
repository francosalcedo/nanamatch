package com.francosalcedo.perfecthttp.util;

import android.util.ArrayMap;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by francosalcedo on 4/11/17.
 */

public class Api {

    public void Api(){
        Log.d("Mensaje", "Dentro del constructor");
    }

    protected JSONObject jsonInit(String c)
    {
        try {
            JSONObject reader = new JSONObject(c);
            return reader;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    /*
    public String getQuery(ArrayMap<String, String>)
    {

    }
    */

}
