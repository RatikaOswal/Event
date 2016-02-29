package com.ht.event.Interface;


import java.util.HashMap;

public interface DataHandler {

    public void onSuccess(HashMap<String, Object> map);

    public void onFailure(HashMap<String, Object> map);


}
