package com.changshun.countsystem.controller;

import com.changshun.countsystem.common.CodeStatus;
import com.changshun.countsystem.common.ResponseVo;
import com.changshun.countsystem.pojo.TCangkukaoqin;
import com.changshun.countsystem.service.TOfficeService;
import com.changshun.countsystem.service.TCangkukaoqinService;
import com.changshun.countsystem.service.TCangkumanagerService;
import com.changshun.countsystem.service.TCangkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/excelupload")
public class ExcelUpLoadController {

    @Autowired
    private TOfficeService officeService;

    @Autowired
    private TCangkuService wareHouseWorkerService;

    @Autowired
    private TCangkumanagerService wareHouseManagerService;

    @Autowired
    private TCangkukaoqinService wareHouseKaoQinService;

    //用户上传excel
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ResponseVo upload(@RequestParam("file") MultipartFile file, @RequestParam("identitycode") int identitycode){
        if (identitycode==1){
            String result = officeService.readExcelFile(file);
            if (result.equals("上传成功")){
                return ResponseVo.success(CodeStatus.OPERATION_SUCCESS.getCode(),CodeStatus.OPERATION_SUCCESS.getTip(),result);
            }else {
                return ResponseVo.error(CodeStatus.EXCEPTION_TIP.getCode(),CodeStatus.EXCEPTION_TIP.getTip());
            }
        }
        if (identitycode==21){
            String wareresult = wareHouseWorkerService.readExcelFile(file);
            if (wareresult.equals("上传成功")){
                return ResponseVo.success(CodeStatus.OPERATION_SUCCESS.getCode(),CodeStatus.OPERATION_SUCCESS.getTip(),wareresult);
            }else {
                return ResponseVo.error(CodeStatus.EXCEPTION_TIP.getCode(),CodeStatus.EXCEPTION_TIP.getTip());
            }
        }
        if (identitycode==22){
            //String wareresult1 = wareHouseManagerService.readExcelFile(file);
            String wareresultone= wareHouseManagerService.readExcelFile(file);
            if (wareresultone.equals("上传成功")){
                return ResponseVo.success(CodeStatus.OPERATION_SUCCESS.getCode(),CodeStatus.OPERATION_SUCCESS.getTip(),wareresultone);
            }else {
                return ResponseVo.error(CodeStatus.EXCEPTION_TIP.getCode(),CodeStatus.EXCEPTION_TIP.getTip());
            }
        }
        if (identitycode==23){
            String wareresult2 = wareHouseKaoQinService.readExcelFile(file);
            if(wareresult2.equals("上传成功")){
                return ResponseVo.success(CodeStatus.OPERATION_SUCCESS.getCode(),CodeStatus.OPERATION_SUCCESS.getTip(),wareresult2);
            }else{
                return ResponseVo.error(CodeStatus.EXCEPTION_TIP.getCode(),CodeStatus.EXCEPTION_TIP.getTip());
            }
        }
        else{
            return ResponseVo.error(CodeStatus.EXCEPTION_TIP.getCode(),CodeStatus.EXCEPTION_TIP.getTip());
        }

    }



}
