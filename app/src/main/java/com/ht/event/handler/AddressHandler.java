package com.ht.event.handler;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ht.event.Interface.DataHandler;
import com.ht.event.application.AppController;
import com.ht.event.model.Item;
import com.ht.event.utils.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AddressHandler {
    private HashMap<String, Object> map = new HashMap<String, Object>();
    private DataHandler mDataHandler;


    public AddressHandler(DataHandler dataHandler) {
        mDataHandler = dataHandler;
    }

    public void fetchAddress(String url) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            response.getJSONArray("result").getJSONObject(0)
                                    .getJSONObject("geometry")
                                    .getJSONObject("location").getString("lat");

                            response.getJSONArray("result").getJSONObject(0)
                                    .getJSONObject("geometry")
                                    .getJSONObject("location").getString("lng");

                            Item item = new Item();
                           // item.setLat();
                            //item.setLng();
                            map.put(Config.KEY_DATA,item);
                            mDataHandler.onSuccess(map);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                map.put(Config.KEY_ERROR, error);
                mDataHandler.onFailure(map);


            }
        });
// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }
}




