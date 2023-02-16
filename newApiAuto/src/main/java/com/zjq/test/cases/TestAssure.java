package com.zjq.test.cases;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;
import com.zjq.test.dataExcel.getExcelData;
import com.zjq.test.utils.Assertutil;
import com.zjq.test.utils.JsonUtil;
import com.zjq.test.utils.RestAssure111Util;
import org.testng.annotations.Test;

import java.io.IOException;


public class TestAssure {


    @Test(dataProvider = "maidianPara",dataProviderClass = getExcelData.class)
    public void test01(String caseId,String method,String paras) throws IOException {
        System.out.println(paras);
        String o = (String) RestAssure111Util.RestAssuredPost(paras);
        JSONObject parse = JSONObject.parseObject(o);
        // 使用JSONPath解析响应体
        JSONArray reads = JsonPath.read(parse, "$.data.data");
        for (int i = 0; i < reads.size(); i++) {
            Object o1 = reads.get(i);
            String str4 = JSON.toJSONString(o1);
            JSONObject jsonObject4 = JSONObject.parseObject(str4);
            // 响应断言
            if (jsonObject4.get("cls").equals(JSONObject.parseObject(paras).get("cls"))) {
                System.out.println("success");
            }
        }


    }


    @Test(dataProvider = "maidianPara",dataProviderClass = getExcelData.class)
    public void test02(String caseId,String method,String paras) throws IOException {
        System.out.println(paras);
        String o = (String) RestAssure111Util.RestAssuredPost(paras);
        JSONObject object = JsonUtil.toJSONObject(o);
        JSONArray reads = JsonPath.read(object, "$.data.data");
        System.out.println(JsonPath.read(object, "$.data").getClass().getSimpleName());
        if (object.get("msg").getClass().getSimpleName().equals("String")||object.get("msg").equals("操作成功")){
            System.out.println("成功");
        }

        System.out.println(o.getClass().getSimpleName());
//        JSONObject parse = JSONObject.parseObject(o);
//        // 使用JSONPath解析响应体
//        JSONArray reads = JsonPath.read(parse, "$.data.data");
//        for (int i = 0; i < reads.size(); i++) {
//            Object o1 = reads.get(i);
//            String str4 = JSON.toJSONString(o1);
//            JSONObject jsonObject4 = JSONObject.parseObject(str4);
//            // 响应断言
//            if (jsonObject4.get("cls").equals(JSONObject.parseObject(paras).get("cls"))) {
//                System.out.println("success");
//            }
//        }
//        JSONObject object = JsonUtil.toJSONObject(o);
//        System.out.println(object.getClass().getSimpleName());

    }

}
