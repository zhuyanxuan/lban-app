package com.app.lban.utils;


import com.alibaba.fastjson.JSONObject;

public class ResultJSON extends JSONObject {

    public ResultJSON(int code, String msg){
        this.put("code", code);
        this.put("msg", msg);
    }
}
