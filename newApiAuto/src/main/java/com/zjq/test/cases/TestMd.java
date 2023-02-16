package com.zjq.test.cases;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjq.test.utils.RestAssure111Util;
import org.testng.annotations.Test;

import java.util.*;

import static com.zjq.test.utils.ReadProper.readPropertiesFile;
import static io.restassured.RestAssured.given;

public class TestMd {

    private String split;

    public List<String> getCls(String s){
        Properties properties = readPropertiesFile("application.properties");
        String clss = properties.getProperty(s);
        List<String> split1 = Arrays.asList(clss.split(","));
        return split1;
    }
    @Test
    public void testMaidian(){
        String test= "{\n" +
                "    \"start\":1675211940000,\n" +
                "    \"end\":1675219140000,\n" +
                "    \"pin\":\"15600553885_p\",\n" +
                "    \"cls\":\"kmhhc|myjl\",\n" +
                "    \"paramKey\":\"\",\n" +
                "    \"from\":0,\n" +
                "    \"size\":20,\n" +
                "    \"timeType\":1,\n" +
                "    \"searchType\":\"cic\"\n" +
                "}";
        String resp = given().header("Cookie",getCls("cookie"))
                .body(test)
                .contentType("application/json").log().uri().log().params().log().headers().
                when()
                .post("http://qd.jd.com/api/qdDataES/esSearch/h5").
                then()
                .log().all().extract().response().asString();

        System.out.println(resp);



        // 使用JSONPath解析响应体
        JSONObject jsonObject = JSONObject.parseObject(resp);
        Object data = jsonObject.get("data");
        String str2 = JSON.toJSONString(data);
        JSONObject jsonObject2 = JSONObject.parseObject(str2);
        Object data3 = jsonObject2.get("data");
        String str3 = JSON.toJSONString(data3);
        JSONArray arr = JSONArray.parseArray(str3);
        for (int i=0; i<arr.size();i++) {
            Object o = arr.get(i);
            String str4 = JSON.toJSONString(o);
            JSONObject jsonObject4 = JSONObject.parseObject(str4);
            // 响应断言
            if (jsonObject4.get("cls").equals("kmhhc|myjl")) {
                System.out.println("success");
            }
        }
    }

    @Test
    public void testMaidian2(){
        //构造一个Map对象，用来存多个参数和值
       for (int i=0;i<getCls("cls1").size();i++){
           Map parameters = new HashMap<String,String>();
           parameters.put("start", "1675302060000");
           parameters.put("end", "1675309260000");
           for (String cls:getCls("cls1")){
               parameters.put("cls", cls);

           }
           //parameters.put("cls", getCls());
           parameters.put("paramKey", "");
           parameters.put("from", "0");
           parameters.put("size", "200");
           parameters.put("timeType", "1");
           parameters.put("searchType", "cic");


           String json = String.valueOf(new JSONObject(parameters));
           System.out.println(json);
           String resp = given().header("Cookie",getCls("cookie"))
                   .body(json)
                   .contentType("application/json").log().uri().log().params().log().headers().
                   when()
                   .post("http://qd.jd.com/api/qdDataES/esSearch/h5").
                   then()
                   .log().all().extract().response().asString();

           // 使用JSONPath解析响应体
           JSONObject jsonObject = JSONObject.parseObject(resp);
           Object data = jsonObject.get("data");
           String str2 = JSON.toJSONString(data);
           JSONObject jsonObject2 = JSONObject.parseObject(str2);
           Object data3 = jsonObject2.get("data");
           String str3 = JSON.toJSONString(data3);
           JSONArray arr = JSONArray.parseArray(str3);
           for (int j=0; j<arr.size();j++) {
               Object o = arr.get(j);
               String str4 = JSON.toJSONString(o);
               JSONObject jsonObject4 = JSONObject.parseObject(str4);
               // 响应断言
               if (jsonObject4.get("cls").equals("")) {
                   System.out.println("success");
               }
           }
       }

    }

    @Test
    public void test111(){
        String json= "{\n" +
                "    \"start\":1675211940000,\n" +
                "    \"end\":1675219140000,\n" +
                "    \"pin\":\"15600553885_p\",\n" +
                "    \"cls\":\"kmhhc|myjl\",\n" +
                "    \"paramKey\":\"\",\n" +
                "    \"from\":0,\n" +
                "    \"size\":20,\n" +
                "    \"timeType\":1,\n" +
                "    \"searchType\":\"cic\"\n" +
                "}";
        Object o = RestAssure111Util.RestAssuredPost(json);
        System.out.println(o);
    }
}
