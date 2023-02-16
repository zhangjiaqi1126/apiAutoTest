package com.zjq.test.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import java.util.HashMap;
import java.util.List;

/**
 * JSON工具类

 */
public class JsonUtil {
    /**
     * 字符串转为JSONObject
     * @param s 需要转为JSON的对象
     * @return JSON字符串
     */
    public static JSONObject toJSONObject(String s){
        return JSONObject.parseObject(s);
    }


    /**
     * 对象转为JSON字符串
     * @param object 需要转为JSON的对象
     * @return JSON字符串
     */
    public static String toJSONString(Object object){
        return JSON.toJSONString(object);
    }

    /**
     * JSON字符串转对象
     * @param text JSON字符串
     * @param tClass 对象类名
     * @param <T> 对象类型
     * @return 转换后的对象
     */
    public static final <T> T parseObject(String text,Class<T> tClass){
        return JSON.parseObject(text,tClass);
    }

    /**
     * JSON字符串转对象
     * @param text JSON字符串
     * @param tClass 对象类名
     * @param <T> 对象类型
     * @return 转换后的对象集合
     */
    public static final <T> List<T> parseArray(String text,Class<T> tClass){
        return JSON.parseArray(text,tClass);
    }


    /**
     * 将string转化成HashMap<String,String>
     * @param str 要转化的字符串
     * @return
     *
     * @author chenxiaoli1
     * @date 2015.3.25
     */
    public static HashMap<String,String> ConvertStringToHashMap(String str)
    {
        HashMap<String, String> data = new HashMap<String, String>();
        try {
            if(str!=null && !str.isEmpty()){
                // 将json字符串转换成jsonObject
                JSONObject jsonObject = (JSONObject)JSON.parse(str);
                //遍历jsonObject数据，添加到Map对象
                for (String key : jsonObject.keySet()){
                    data.put(key, (String) jsonObject.get(key));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //log.error("Converting The String("+str+") to type of HashMap<String, String> is failed.the info is:"+e.getMessage());
        }
        return data;
    }

}
