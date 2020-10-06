package com.changshun.countsystem.util;
import com.changshun.countsystem.pojo.ReadExcel;
import com.changshun.countsystem.pojo.TJiechajiankaixianchuqin;
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

public class TJiechajiankaixianchuqinExcelUtil {
    /**
     * 先创建一个实体类
     */
    ReadExcel readExcel = new ReadExcel();
    /**
     * 读取Excel文件，获取信息集合
     * @param mFile
     * @return
     */
    public List<TJiechajiankaixianchuqin> getExcelInfo(MultipartFile mFile) {
        //获取文件名
        String fileName = mFile.getOriginalFilename();
        List<TJiechajiankaixianchuqin> ilist = null;
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
 
public List<TJiechajiankaixianchuqin> createExcel(InputStream is , boolean isExcel2003){
        List<TJiechajiankaixianchuqin> ilist = null;
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
public List<TJiechajiankaixianchuqin> readExcelValue(Workbook wb){
        List<TJiechajiankaixianchuqin> ilist=new ArrayList<>();
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
            TJiechajiankaixianchuqin tJiechajiankaixianchuqin = new TJiechajiankaixianchuqin();
            //循环xcel的列
            for(int c = 0; c<readExcel.getTotalCells() ; c++){
                Cell cell = row.getCell(c);
                if(cell != null){ 
		if(c==0){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
    
		String zhiyuandaima = String.valueOf(cell.getNumericCellValue());
                    tJiechajiankaixianchuqin.setZhiyuandaima(zhiyuandaima.substring(0,zhiyuandaima.length()-2>0?zhiyuandaima.length()-2:1));
                }

               else{
                //如果不是纯数字可以直接获得名称
                tJiechajiankaixianchuqin.setZhiyuandaima(cell.getStringCellValue());
            }} 
		if(c==1){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
    
		String workerid = String.valueOf(cell.getNumericCellValue());
                    tJiechajiankaixianchuqin.setWorkerid(workerid.substring(0,workerid.length()-2>0?workerid.length()-2:1));
                }

               else{
                //如果不是纯数字可以直接获得名称
                tJiechajiankaixianchuqin.setWorkerid(cell.getStringCellValue());
            }} 
		if(c==2){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
    
		String name = String.valueOf(cell.getNumericCellValue());
                    tJiechajiankaixianchuqin.setName(name.substring(0,name.length()-2>0?name.length()-2:1));
                }

               else{
                //如果不是纯数字可以直接获得名称
                tJiechajiankaixianchuqin.setName(cell.getStringCellValue());
            }} 
		if(c==3){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
    
		String gangwei = String.valueOf(cell.getNumericCellValue());
                    tJiechajiankaixianchuqin.setGangwei(gangwei.substring(0,gangwei.length()-2>0?gangwei.length()-2:1));
                }

               else{
                //如果不是纯数字可以直接获得名称
                tJiechajiankaixianchuqin.setGangwei(cell.getStringCellValue());
            }} 
		if(c==4){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double leijichuqin = cell.getNumericCellValue();
                 tJiechajiankaixianchuqin.setLeijichuqin(leijichuqin);}
             else{
                //如果不是纯数字可以直接获得名称
                tJiechajiankaixianchuqin.setLeijichuqin(cell.getNumericCellValue());
            }
        } 
		if(c==5){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double buzhu = cell.getNumericCellValue();
                 tJiechajiankaixianchuqin.setBuzhu(buzhu);}
             else{
                //如果不是纯数字可以直接获得名称
                tJiechajiankaixianchuqin.setBuzhu(cell.getNumericCellValue());
            }
        } 
		if(c==6){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double jijianmoney = cell.getNumericCellValue();
                 tJiechajiankaixianchuqin.setJijianmoney(jijianmoney);}
             else{
                //如果不是纯数字可以直接获得名称
                tJiechajiankaixianchuqin.setJijianmoney(cell.getNumericCellValue());
            }
        } 
		if(c==7){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double totalmoney = cell.getNumericCellValue();
                 tJiechajiankaixianchuqin.setTotalmoney(totalmoney);}
             else{
                //如果不是纯数字可以直接获得名称
                tJiechajiankaixianchuqin.setTotalmoney(cell.getNumericCellValue());
            }
        } 
		if(c==8){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double pinjunzhi = cell.getNumericCellValue();
                 tJiechajiankaixianchuqin.setPinjunzhi(pinjunzhi);}
             else{
                //如果不是纯数字可以直接获得名称
                tJiechajiankaixianchuqin.setPinjunzhi(cell.getNumericCellValue());
            }
        } 
		if(c==9){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double chidao = cell.getNumericCellValue();
                 tJiechajiankaixianchuqin.setChidao(chidao);}
             else{
                //如果不是纯数字可以直接获得名称
                tJiechajiankaixianchuqin.setChidao(cell.getNumericCellValue());
            }
        } 
		if(c==10){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double qingjia = cell.getNumericCellValue();
                 tJiechajiankaixianchuqin.setQingjia(qingjia);}
             else{
                //如果不是纯数字可以直接获得名称
                tJiechajiankaixianchuqin.setQingjia(cell.getNumericCellValue());
            }
        } 
		if(c==11){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double wuqingjiadan = cell.getNumericCellValue();
                 tJiechajiankaixianchuqin.setWuqingjiadan(wuqingjiadan);}
             else{
                //如果不是纯数字可以直接获得名称
                tJiechajiankaixianchuqin.setWuqingjiadan(cell.getNumericCellValue());
            }
        } 
		if(c==12){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double gongling = cell.getNumericCellValue();
                 tJiechajiankaixianchuqin.setGongling(gongling);}
             else{
                //如果不是纯数字可以直接获得名称
                tJiechajiankaixianchuqin.setGongling(cell.getNumericCellValue());
            }
        } 
		if(c==13){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double quanqin = cell.getNumericCellValue();
                 tJiechajiankaixianchuqin.setQuanqin(quanqin);}
             else{
                //如果不是纯数字可以直接获得名称
                tJiechajiankaixianchuqin.setQuanqin(cell.getNumericCellValue());
            }
        } 
		if(c==14){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
 
		 Double gaowenfei = cell.getNumericCellValue();
                 tJiechajiankaixianchuqin.setGaowenfei(gaowenfei);}
             else{
                //如果不是纯数字可以直接获得名称
                tJiechajiankaixianchuqin.setGaowenfei(cell.getNumericCellValue());
            }
        } 
		if(c==15){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
}} 
		if(c==16){
                   if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
    
		String bumen = String.valueOf(cell.getNumericCellValue());
                    tJiechajiankaixianchuqin.setBumen(bumen.substring(0,bumen.length()-2>0?bumen.length()-2:1));
                }

               else{
                //如果不是纯数字可以直接获得名称
                tJiechajiankaixianchuqin.setBumen(cell.getStringCellValue());
            }} }
            }
            //最后将这些全部添加到ilist中
            ilist.add(tJiechajiankaixianchuqin);
        }        return ilist;
    }}
