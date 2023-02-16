package com.zjq.test.utils;

import java.io.File;
import java.io.IOException;


public class ParaUtil {

    /**
     * 功能：将string中用分隔符分割的数据项，逐个加入到set里
     * @param Excelname excel文件名称
     * @param workSheetname excel工作表
     * @return Object[][] 返回二维数组对象
     */
    public static Object[][] Dataworksheet(String Excelpath,String Excelname,String workSheetname) throws IOException {
        //get excel file
        File excelfile = getExcelFile(Excelpath,Excelname);
        ExcelDataUtil getExcelData = new ExcelDataUtil();
        return (Object[][]) getExcelData.getexceldata(excelfile,workSheetname);
    }

    /**
     * 功能：根据参数头名称，获取excel中的参数
     * @param Excelname excel名称
     * @param workSheetname worksheet名称
     * @param headers 参数名称
     * @return
     * @throws IOException
     */
    public static Object[][] getWorkSheetDataByHeaders(String excelPath,String Excelname ,String workSheetname,String headers) throws IOException {
        //get excel file
        File excelfile = getExcelFile(excelPath,Excelname);
        ExcelDataUtil getExcelData = new ExcelDataUtil();
        return (Object[][]) getExcelData.getDataFromExcel(excelfile,workSheetname,headers);
    }


    /**
     * 功能：根据测试数据的Excel名称获得该文件对象
     * @param excelPath Excel路径
     * @param excelName Excel名称
     * @return Excel文件对象
     * @throws IOException
     */
    public static File getExcelFile(String excelPath,String excelName) throws IOException
    {
        //get excel path
        String excelpath = System.getProperty("user.dir")+excelPath+excelName;
        File excelfile = new File(excelpath);
        return excelfile;
    }

    /**
     * 功能：将string中用分隔符分割的数据项，逐个加入到set里
     * @param para 参数字符串
     * @param splitChar 分割符
     * @return
     */
//    public static <T> List<T> AddAllToList(String para,String splitChar,String type)
//    {
//        List<T> result=new ArrayList<T>();
//        try {
//            String[] paraList = para.split(splitChar);
//            for(int i=0;i<paraList.length;i++)
//            {
//                T setEntity=(T)ResultUtil.ConvertStringToT(paraList[i],type);
//                result.add(setEntity);
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            log.error("Adding The parameter("+para+") to List<"+type+"> is failed.the info is:"+e.getStackTrace());
//        }
//        return result;
//    }

}
