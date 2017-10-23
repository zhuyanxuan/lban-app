package com.app.lban.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class ResultJSON extends JSONObject {

    public ResultJSON(int code, String msg){
        this.put("code", code);
        this.put("msg", msg);
    }

    public ResultJSON(int code, String msg, JSONArray data){
        this.put("code", code);
        this.put("msg", msg);
        this.put("data", data);
    }

    public ResultJSON(int code, String msg, List data){
        this.put("code", code);
        this.put("msg", msg);
        this.put("data", data);
    }
}
