package com.smart.org.util;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by asus on 2017/1/23.
 */
public class JudgeRequest {
    public static boolean OkRequest(JSONObject jsonObject){
        String errorcode=jsonObject.get("errCode").toString();
        if ("000000".equals(errorcode)){
            return true;
        }else {
            return false;
        }
    }
}
