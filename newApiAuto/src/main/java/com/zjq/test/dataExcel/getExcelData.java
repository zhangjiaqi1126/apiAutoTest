package com.zjq.test.dataExcel;

import com.zjq.test.utils.ParaUtil;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class getExcelData   {
    private final static String excelPath="/src/main/java/com/zjq/test/dataExcel/";

//    @DataProvider(name = "readCases")
//    //向测试用例提供Excel数据
//    public Object[] readCases(){
//        List<CaseInfo> listDatas = EasyPoiExcelUtil.readExcel(0);
//        return listDatas.toArray();
//    }


    @DataProvider(name = "maidianPara")
    public static Object[][] maidianPara() throws IOException {
        Object[][] maidianParas = new Object[0][];
        try {
            maidianParas = ParaUtil.Dataworksheet(excelPath, "apiPara22.xlsx", "maidianPara");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maidianParas;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(maidianPara());
    }
}
