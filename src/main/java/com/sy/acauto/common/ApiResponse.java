package com.sy.acauto.common;

import java.util.HashMap;
import java.util.Map;

public class ApiResponse {

    private int code;

    private Object data;

    private String msg;

    public ApiResponse(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static ApiResponse success(Object data) {
        return new ApiResponse(0, data, "success");
    }

    public static ApiResponse success() {
        return new ApiResponse(0, null, "success");
    }

    public static ApiResponse fail(String msg) {
        return fail(msg, -10411);
    }

    public static ApiResponse fail(String msg, int code) {
        return new ApiResponse(code, null, msg);
    }


    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("data", data);
        map.put("msg", msg);

        return map;
    }
}

