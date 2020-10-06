package com.changshun.countsystem.util;
import com.changshun.countsystem.pojo.ReadExcel;
import com.changshun.countsystem.pojo.THouqincanjimoney;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class THouqincanjimoneyExcelUtil {
    /**
     * 先创建一个实体类
     */
    ReadExcel readExcel = new ReadExcel();
    /**
     * 读取Excel文件，获取信息集合
     * @param mFile
     * @return
     */
    public List<THouqincanjimoney> getExcelInfo(MultipartFile mFile) {
        //获取文件名
        String fileName = mFile.getOriginalFilename();
        List<THouqincanjimoney> ilist = null;
        try {
            //验证文件名是否合格
            if(!validateExcel(fileName)){
                //不合格的话直接return
                return null;
            }
            //根据文件名判断是2003版本的还是2007版本的
            boolean isExcel2003 = true;
            if(isExcel2007(fileName)){
                isExcel2003 = false;
            }
            ilist= createExcel(mFile.getInputStream(),isExcel2003);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ilist;
    }
 
public List<THouqincanjimoney> createExcel(InputStream is , boolean isExcel2003){
        List<THouqincanjimoney> ilist = null;
        try {
            Workbook wb = null;
            if(isExcel2003){
                //如果是2003版本的就new一个2003的wb出来
                wb = new HSSFWorkbook(is);
            }else{
                //否则就new 一个2007版的出来
                wb = new XSSFWorkbook(is);

            }
            //再让wb去解析readExcelValue(Workbook wb)方法
            ilist = readExcelValue(wb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ilist;
    }
 /**
     * 判断是不是2003版本的excel
     * @param filePath
     * @return
     */
    public static boolean isExcel2003(String filePath){
        return filePath.matches("^.+\\.(?i)(xls)$");
    }
    /**
     * 判断是不是2007版本的excel
     * @param filePath
     * @return
     */
    public static boolean isExcel2007(String filePath){
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
    /**
     * 判断是不是excel文件格式
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath){
        if(filePath ==null||!(isExcel2003(filePath) || isExcel2007(filePath))){
            readExcel.setErrorMsg("文件名不是excel格式");
            return false;
        }
        return true;
    } 
public List<THouqincanjimoney> readExcelValue(Workbook wb){
        List<THouqincanjimoney> ilist=new ArrayList<>();
        //先得到一个sheet
        Sheet sheet = wb.getSheetAt(0);
        //得到excel里面的行数
        int totalRows = sheet.getPhysicalNumberOfRows();
        readExcel.setTotalRows(totalRows);
        //得到excel里面的列，前提是有行
        //大于1是因为我从第二行就是数据了，这个大家看情况而定
        if(totalRows >1 && sheet.getRow(0)!=null){
            int totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
            readExcel.setTotalCells(totalCells);
        }
        for (int r = 1 ; r < totalRows; r++){
            Row row = sheet.getRow(r);
            if(row == null){
                continue;//如果行为空的话直接中断
            }
            THouqincanjimoney tHouqincanjimoney = new THouqincanjimoney();
            //循环xcel的列
            for(int c = 0; c<readExcel.getTotalCells() ; c++){
                Cell cell = row.getCell(c);
                if(cell != null){ 
		if(c==0){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
    
		String name = String.valueOf(cell.getNumericCellValue());
                    tHouqincanjimoney.setName(name.substring(0,name.length()-2>0?name.length()-2:1));
                }

               else{
                //如果不是纯数字可以直接获得名称
                tHouqincanjimoney.setName(cell.getStringCellValue());
            }} 
		if(c==1){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double jibenmoney = cell.getNumericCellValue();
                 tHouqincanjimoney.setJibenmoney(jibenmoney);}
             else{
                //如果不是纯数字可以直接获得名称
                tHouqincanjimoney.setJibenmoney(cell.getNumericCellValue());
            }
        } 
		if(c==2){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double zhiwujixiao = cell.getNumericCellValue();
                 tHouqincanjimoney.setZhiwujixiao(zhiwujixiao);}
             else{
                //如果不是纯数字可以直接获得名称
                tHouqincanjimoney.setZhiwujixiao(cell.getNumericCellValue());
            }
        } 
		if(c==3){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double jixiaomoney = cell.getNumericCellValue();
                 tHouqincanjimoney.setJixiaomoney(jixiaomoney);}
             else{
                //如果不是纯数字可以直接获得名称
                tHouqincanjimoney.setJixiaomoney(cell.getNumericCellValue());
            }
        } 
		if(c==4){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double yingfajiangli = cell.getNumericCellValue();
                 tHouqincanjimoney.setYingfajiangli(yingfajiangli);}
             else{
                //如果不是纯数字可以直接获得名称
                tHouqincanjimoney.setYingfajiangli(cell.getNumericCellValue());
            }
        } 
		if(c==5){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double jiangli = cell.getNumericCellValue();
                 tHouqincanjimoney.setJiangli(jiangli);}
             else{
                //如果不是纯数字可以直接获得名称
                tHouqincanjimoney.setJiangli(cell.getNumericCellValue());
            }
        } 
		if(c==6){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double koukuan = cell.getNumericCellValue();
                 tHouqincanjimoney.setKoukuan(koukuan);}
             else{
                //如果不是纯数字可以直接获得名称
                tHouqincanjimoney.setKoukuan(cell.getNumericCellValue());
            }
        } 
		if(c==7){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double baoxian = cell.getNumericCellValue();
                 tHouqincanjimoney.setBaoxian(baoxian);}
             else{
                //如果不是纯数字可以直接获得名称
                tHouqincanjimoney.setBaoxian(cell.getNumericCellValue());
            }
        } 
		if(c==8){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double gonghuifei = cell.getNumericCellValue();
                 tHouqincanjimoney.setGonghuifei(gonghuifei);}
             else{
                //如果不是纯数字可以直接获得名称
                tHouqincanjimoney.setGonghuifei(cell.getNumericCellValue());
            }
        } 
		if(c==9){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double getiaoshui = cell.getNumericCellValue();
                 tHouqincanjimoney.setGetiaoshui(getiaoshui);}
             else{
                //如果不是纯数字可以直接获得名称
                tHouqincanjimoney.setGetiaoshui(cell.getNumericCellValue());
            }
        } 
		if(c==10){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double shifamoney = cell.getNumericCellValue();
                 tHouqincanjimoney.setShifamoney(shifamoney);}
             else{
                //如果不是纯数字可以直接获得名称
                tHouqincanjimoney.setShifamoney(cell.getNumericCellValue());
            }
        } 
		if(c==11){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
    
		String beizhu = String.valueOf(cell.getNumericCellValue());
                    tHouqincanjimoney.setBeizhu(beizhu.substring(0,beizhu.length()-2>0?beizhu.length()-2:1));
                }

               else{
                //如果不是纯数字可以直接获得名称
                tHouqincanjimoney.setBeizhu(cell.getStringCellValue());
            }} 
		if(c==12){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
}} 
		if(c==13){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
    
		String bumen = String.valueOf(cell.getNumericCellValue());
                    tHouqincanjimoney.setBumen(bumen.substring(0,bumen.length()-2>0?bumen.length()-2:1));
                }

               else{
                //如果不是纯数字可以直接获得名称
                tHouqincanjimoney.setBumen(cell.getStringCellValue());
            }} }
            }
            //最后将这些全部添加到ilist中
            ilist.add(tHouqincanjimoney);
        }        return ilist;
    }}
