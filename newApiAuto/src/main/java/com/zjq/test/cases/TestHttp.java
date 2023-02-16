package com.zjq.test.cases;//package com.zjq.api.cases;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.zjq.test.config.Contants;
import com.zjq.test.dataExcel.getExcelData;
import com.zjq.test.utils.httpUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class TestHttp {


    @Test(dataProvider = "maidianPara",dataProviderClass = getExcelData.class)
    public void test01(String caseId,String method,String paras) throws Exception {
        String url = Contants.BASE_URL;
        httpUtil.doPost(url,paras);

    }
}
