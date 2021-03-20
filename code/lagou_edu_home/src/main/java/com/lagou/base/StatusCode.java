package com.lagou.base;

import com.alibaba.fastjson.JSONObject;

public enum StatusCode {
    SUCCESS(0,"success"),Fail(1,"fail");

    private int code;
    private String message;

    StatusCode() {
    }

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("status",code);
        object.put("msg",message);
        return object.toString();
    }
}
