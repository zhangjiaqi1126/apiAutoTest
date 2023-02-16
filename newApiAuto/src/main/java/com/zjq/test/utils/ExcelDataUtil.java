package com.zjq.test.utils;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class ExcelDataUtil {
    /**
     * 功能：读取excel.xls格式文件,把数据存入  String[][]种返回
     * 第一列为列名，从第二行开始读取，数据列自增读取，
     * 倒数第一列为判断该数据行是否执行的flag标记列，默认“N”不读取该列
     * @param file        Excel文件名称
     * @param sheetstr    Excel的worksheet名称
     * @return 返回对象    String[][]
     */
    public String[][] getexceldata(File file,String sheetstr)
    {
        //[start]
        List<String[]> results = new ArrayList<String[]>();
        //读取文件流
        FileInputStream fis = null;
        int colSize = 0;
        try {
            fis = new FileInputStream(file);
            //得到文件后,采用POIFSFileSystem 进行解析
//        POIFSFileSystem POIStream = new POIFSFileSystem(fis);
            //创建一个新的excel实例，根据数据流
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fis);
            //创建一个新的sheet实例，根据指定的sheet名字
            XSSFSheet sheet = xssfWorkbook.getSheet(sheetstr);
            //获取数据行号
            int rowSize = sheet.getLastRowNum();
            //获取数据列号
            colSize = sheet.getRow(0).getLastCellNum();

            for (int rowIndex =1; rowIndex <= rowSize; rowIndex++ ) {
                XSSFRow row = sheet.getRow(rowIndex);
                XSSFCell lastcell = row.getCell(colSize-2); //最后一列为备注，第二列为是否读取的标签列
                if (lastcell.getStringCellValue().equalsIgnoreCase("n")){
                    continue;
                }

                String[] values = new String[colSize-2]; //最后一列为备注，第二列为是否读取的标签列
                Arrays.fill(values, "defaultvalue");
                for (int columnIndex=0; columnIndex < colSize-2 ; columnIndex++){       //不读取用例描述信息
                    XSSFCell cell = row.getCell(columnIndex);
                    // 替换原有代码，使用封装好的方法来获取Excel单元格的值
                    String value = getValueFromCell(cell);
                    values[columnIndex] = value.trim();
                }
                results.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        String[][] returnArray = new String[results.size()][colSize-2];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) results.get(i);
        }
        return returnArray;
        //[end]
    }


    /**
     * 根据参数名称，从excel中获取参数
     * @param file
     * @param sheetStr
     * @param headers 参数名称  注意要有包含关系
     * @return
     * @throws IOException
     */
    public String[][] getDataFromExcel(File file, String sheetStr, String headers)throws IOException
    {
        List<String[]> results = new ArrayList<String[]>();
        //读取文件流
        FileInputStream fis = new FileInputStream(file);
        //得到文件后,采用POIFSFileSystem 进行解析
//        POIFSFileSystem POIStream = new POIFSFileSystem(fis);
        //创建一个新的excel实例，根据数据流
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fis);
        //创建一个新的sheet实例，根据指定的sheet名字
        XSSFSheet sheet = xssfWorkbook.getSheet(sheetStr);
        //获取数据行号
        int rowSize = sheet.getLastRowNum();

        //设置要获取的列
        XSSFRow rowHeader = sheet.getRow(0);
        int colSize=rowHeader.getLastCellNum();
        List<Integer> colIndexList=new ArrayList<Integer>();
        for(int j=0;j<colSize;j++)
        {
            XSSFCell headCell=rowHeader.getCell(j);
            String headerStr=getValueFromCell(headCell);
            if(headers.contains(headerStr))
            {
                colIndexList.add(Integer.parseInt(String.valueOf(j)));
            }
        }
        int colNums=colIndexList.size();//要获取列的数量
        //遍历数据的每一行 ，取出value值
        for (int rowIndex =1; rowIndex <= rowSize; rowIndex++ ) {
            XSSFRow row = sheet.getRow(rowIndex);
            String[] values = new String[colNums];
            Arrays.fill(values, "defaultvalue");
            int reIndex=0;
            for(Integer colNum :colIndexList)
            {
                XSSFCell cell = row.getCell(colNum);
                String value=getValueFromCell(cell);
                values[reIndex] = value.trim();
                reIndex++;
            }
            results.add(values);
        }
        fis.close();

        String[][] returnArray = new String[results.size()][colNums];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) results.get(i);
        }
        return returnArray;
    }

    /**
     * 功能：从单元格中获取数据
     * @param cell 单元格
     * @return
     */
    public String getValueFromCell(XSSFCell cell)
    {
        String value = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case XSSFCell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue();
                    break;
                case XSSFCell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        if (date != null) {
                            value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                        } else {
                            value = "";
                        }
                    } else {
                        value = new DecimalFormat("0").format(cell.getNumericCellValue());
                    }
                    break;
                case XSSFCell.CELL_TYPE_FORMULA:
                    if (!cell.getStringCellValue().equals("")) {
                        value = cell.getStringCellValue();
                    } else {
                        value = cell.getNumericCellValue() + "";
                    }
                    break;
                case XSSFCell.CELL_TYPE_BLANK:
                    break;
                case XSSFCell.CELL_TYPE_ERROR:
                    value = "";
                    break;
                case XSSFCell.CELL_TYPE_BOOLEAN:
                    value = (cell.getBooleanCellValue() == true ? "Y" : "N");
                    break;
                default:
                    value = "default";
            }
        }
        return value;
    }
}
