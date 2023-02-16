package com.zjq.test.cases;



import com.zjq.test.dataExcel.getExcelData;
import com.zjq.test.utils.RestAssure111Util;
import org.testng.annotations.Test;

import java.io.IOException;


public class Test01 {
//
//    @DataProvider(name = "readCases")
//    //向测试用例提供Excel数据
//    public Object[] readCases(){
//        List<CaseInfo> listDatas = EasyPoiExcelUtil.readExcel(0);
//        return listDatas.toArray();
//    }



    @Test(dataProvider = "maidianPara",dataProviderClass = getExcelData.class)
    public void test01(String caseId,String method,String paras) throws IOException {
        System.out.println(paras);
        RestAssure111Util.RestAssuredPost(paras);
    }


}
