package com.zjq.test.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;

public class Assertutil {

    public static void resultAssert(String o, String jsonpath, String v, String paras){
        JSONObject parse = JSONObject.parseObject(String.valueOf(o));
        // 使用JSONPath解析响应体
        JSONArray reads = JsonPath.read(parse, jsonpath);
        for (int i = 0; i < reads.size(); i++) {
            Object o1 = reads.get(i);
            String str4 = JSON.toJSONString(o1);
            JSONObject jsonObject4 = JSONObject.parseObject(str4);
            // 响应断言
            if (jsonObject4.get(v).equals(JSONObject.parseObject(paras).get(v))) {
                System.out.println("success");
            }
        }


    }

    public static void resultAssert1(String o, String jsonpath, String v, String paras){

        JSONObject parse = JSONObject.parseObject(String.valueOf(o));
        // 使用JSONPath解析响应体
        JSONArray reads = JsonPath.read(parse, jsonpath);
        if (reads.getClass().getSimpleName().equals("String")){

        }

    }
}
