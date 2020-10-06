package com.changshun.countsystem.util;
import com.changshun.countsystem.pojo.ReadExcel;
import com.changshun.countsystem.pojo.TPingzhijishi;
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

public class TPingzhijishiExcelUtil {
    /**
     * 先创建一个实体类
     */
    ReadExcel readExcel = new ReadExcel();
    /**
     * 读取Excel文件，获取信息集合
     * @param mFile
     * @return
     */
    public List<TPingzhijishi> getExcelInfo(MultipartFile mFile) {
        //获取文件名
        String fileName = mFile.getOriginalFilename();
        List<TPingzhijishi> ilist = null;
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
 
public List<TPingzhijishi> createExcel(InputStream is , boolean isExcel2003){
        List<TPingzhijishi> ilist = null;
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
public List<TPingzhijishi> readExcelValue(Workbook wb){
        List<TPingzhijishi> ilist=new ArrayList<>();
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
            TPingzhijishi tPingzhijishi = new TPingzhijishi();
            //循环xcel的列
            for(int c = 0; c<readExcel.getTotalCells() ; c++){
                Cell cell = row.getCell(c);
                if(cell != null){ 
		if(c==0){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
    
		String workerid = String.valueOf(cell.getNumericCellValue());
                    tPingzhijishi.setWorkerid(workerid.substring(0,workerid.length()-2>0?workerid.length()-2:1));
                }

               else{
                //如果不是纯数字可以直接获得名称
                tPingzhijishi.setWorkerid(cell.getStringCellValue());
            }} 
		if(c==1){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
    
		String name = String.valueOf(cell.getNumericCellValue());
                    tPingzhijishi.setName(name.substring(0,name.length()-2>0?name.length()-2:1));
                }

               else{
                //如果不是纯数字可以直接获得名称
                tPingzhijishi.setName(cell.getStringCellValue());
            }} 
		if(c==2){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
    
		String gangwei = String.valueOf(cell.getNumericCellValue());
                    tPingzhijishi.setGangwei(gangwei.substring(0,gangwei.length()-2>0?gangwei.length()-2:1));
                }

               else{
                //如果不是纯数字可以直接获得名称
                tPingzhijishi.setGangwei(cell.getStringCellValue());
            }} 
		if(c==3){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double chuqindays = cell.getNumericCellValue();
                 tPingzhijishi.setChuqindays(chuqindays);}
             else{
                //如果不是纯数字可以直接获得名称
                tPingzhijishi.setChuqindays(cell.getNumericCellValue());
            }
        } 
		if(c==4){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double fadingdays = cell.getNumericCellValue();
                 tPingzhijishi.setFadingdays(fadingdays);}
             else{
                //如果不是纯数字可以直接获得名称
                tPingzhijishi.setFadingdays(cell.getNumericCellValue());
            }
        } 
		if(c==5){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double jiabanqueqingdays = cell.getNumericCellValue();
                 tPingzhijishi.setJiabanqueqingdays(jiabanqueqingdays);}
             else{
                //如果不是纯数字可以直接获得名称
                tPingzhijishi.setJiabanqueqingdays(cell.getNumericCellValue());
            }
        } 
		if(c==6){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double jibenmoney = cell.getNumericCellValue();
                 tPingzhijishi.setJibenmoney(jibenmoney);}
             else{
                //如果不是纯数字可以直接获得名称
                tPingzhijishi.setJibenmoney(cell.getNumericCellValue());
            }
        } 
		if(c==7){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double gangweimoney = cell.getNumericCellValue();
                 tPingzhijishi.setGangweimoney(gangweimoney);}
             else{
                //如果不是纯数字可以直接获得名称
                tPingzhijishi.setGangweimoney(cell.getNumericCellValue());
            }
        } 
		if(c==8){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double mingyijixiao = cell.getNumericCellValue();
                 tPingzhijishi.setMingyijixiao(mingyijixiao);}
             else{
                //如果不是纯数字可以直接获得名称
                tPingzhijishi.setMingyijixiao(cell.getNumericCellValue());
            }
        } 
		if(c==9){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double jixiaokaohejieguo = cell.getNumericCellValue();
                 tPingzhijishi.setJixiaokaohejieguo(jixiaokaohejieguo);}
             else{
                //如果不是纯数字可以直接获得名称
                tPingzhijishi.setJixiaokaohejieguo(cell.getNumericCellValue());
            }
        } 
		if(c==10){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double shijijixiaogongzi = cell.getNumericCellValue();
                 tPingzhijishi.setShijijixiaogongzi(shijijixiaogongzi);}
             else{
                //如果不是纯数字可以直接获得名称
                tPingzhijishi.setShijijixiaogongzi(cell.getNumericCellValue());
            }
        } 
		if(c==11){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double gonglinggongzi = cell.getNumericCellValue();
                 tPingzhijishi.setGonglinggongzi(gonglinggongzi);}
             else{
                //如果不是纯数字可以直接获得名称
                tPingzhijishi.setGonglinggongzi(cell.getNumericCellValue());
            }
        } 
		if(c==12){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double zizhibutie = cell.getNumericCellValue();
                 tPingzhijishi.setZizhibutie(zizhibutie);}
             else{
                //如果不是纯数字可以直接获得名称
                tPingzhijishi.setZizhibutie(cell.getNumericCellValue());
            }
        } 
		if(c==13){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double jiabangongzi = cell.getNumericCellValue();
                 tPingzhijishi.setJiabangongzi(jiabangongzi);}
             else{
                //如果不是纯数字可以直接获得名称
                tPingzhijishi.setJiabangongzi(cell.getNumericCellValue());
            }
        } 
		if(c==14){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double gaowenfei = cell.getNumericCellValue();
                 tPingzhijishi.setGaowenfei(gaowenfei);}
             else{
                //如果不是纯数字可以直接获得名称
                tPingzhijishi.setGaowenfei(cell.getNumericCellValue());
            }
        } 
		if(c==15){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double quanqin = cell.getNumericCellValue();
                 tPingzhijishi.setQuanqin(quanqin);}
             else{
                //如果不是纯数字可以直接获得名称
                tPingzhijishi.setQuanqin(cell.getNumericCellValue());
            }
        } 
		if(c==16){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double total = cell.getNumericCellValue();
                 tPingzhijishi.setTotal(total);}
             else{
                //如果不是纯数字可以直接获得名称
                tPingzhijishi.setTotal(cell.getNumericCellValue());
            }
        } 
		if(c==17){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
}} 
		if(c==18){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
    
		String bumen = String.valueOf(cell.getNumericCellValue());
                    tPingzhijishi.setBumen(bumen.substring(0,bumen.length()-2>0?bumen.length()-2:1));
                }

               else{
                //如果不是纯数字可以直接获得名称
                tPingzhijishi.setBumen(cell.getStringCellValue());
            }} }
            }
            //最后将这些全部添加到ilist中
            ilist.add(tPingzhijishi);
        }        return ilist;
    }}
