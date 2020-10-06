package com.changshun.countsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.changshun.countsystem.common.CodeStatus;
import com.changshun.countsystem.common.ExcelCode;
import com.changshun.countsystem.common.IdentityShow;
import com.changshun.countsystem.common.ResponseVo;
import com.changshun.countsystem.pojo.*;
import com.changshun.countsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//@RestController
@CrossOrigin
@RestController
@RequestMapping("/chanshunnn")
public class ChangShunController {

    @Autowired
    private TSupermanagerService superManagerService;

    @Autowired
    private TCompanysService companyService;

    @Autowired
    private TIdentityService userIdentityService;

    @Autowired
    private TUserService userService;


    @Autowired
    private TOfficeService officeService;

    @Autowired
    private TCangkuService wareHouseWorkerService;

    @Autowired
    private TCangkumanagerService wareHouseManagerService;

    @Autowired
    private TCangkukaoqinService wareHouseKaoQinService;


    //单点登录接口
    @GetMapping("/singlesignon")
    public ResponseVo  sso(@RequestParam("phone") String phone,
                      @RequestParam(value = "companyCode",required = false) String companycode,
                      @RequestParam(value = "companyName",required = false) String companyname,
                      @RequestParam(value = "userName",required = false) String username,
                      @RequestParam(value = "appCode",required = false) String appcode
                    ){
        //以手机号为验证方式,验证此用户是否已经被赋予身份，如果已经被赋予身份，则返回对应的身份码，前端根据身份码做相应的跳转
        TIdentity Identity = userIdentityService.getOne(TIdentity.builder().phone(phone).build());
        if (Identity==null){
            return ResponseVo.error(CodeStatus.IDENTITY_ERROR.getCode(),CodeStatus.IDENTITY_ERROR.getTip());
        }else {
            String identity = Identity.getIdentity();
            int identityCode = IdentityShow.getIdentity(identity);
            System.out.println(identityCode);
            return ResponseVo.success(CodeStatus.OPERATION_SUCCESS.getCode(),CodeStatus.OPERATION_SUCCESS.getTip(),identityCode);
        }
    }
    //根据公司代码查询公司人员
    @GetMapping("/companyStaff")
    public ResponseVo rest(@RequestParam("companyCode")String companyCode){
        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("companyCode", JSONObject.toJSONString(companyCode));
         String url = " https://apiv2.michain.tech/api/user/list";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> companyStaff= restTemplate.postForEntity(url, paramMap, String.class);
        return ResponseVo.success(CodeStatus.OPERATION_SUCCESS.getCode(),CodeStatus.OPERATION_SUCCESS.getTip(),companyStaff);
    }

    /*
    * 超级管理员登录
    * */
    @PostMapping("/privilegeset")
    public ResponseVo setPrivilege(@RequestParam("count") String count,@RequestParam("password") String password){
        TSupermanager superManager = superManagerService.getOne(TSupermanager.builder().count(count).password(password).build());
        if (superManager==null){
            return ResponseVo.error(CodeStatus.SUPPERMANAGER_IDENTITY_FALSE.getCode(),CodeStatus.SUPPERMANAGER_IDENTITY_FALSE.getTip());
        }
        return ResponseVo.success(CodeStatus.SUPPERMANAGER_IDENTITY_TRUE.getCode(),CodeStatus.SUPPERMANAGER_IDENTITY_TRUE.getTip());
    }

    /*
    * 超级管理员设置公司成员身份
    * */
    @PostMapping("/identityset")
    public ResponseVo setIdentity(@RequestParam("phone") String phone,@RequestParam("identity") String identity){
        //因为一个人只能设置一个身份，所以设置前先查看用户是否有身份,如果没有，则添加，有就不添加
       // UserIdentity userIdentity = userIdentityService.select(phone,identity);
        TIdentity userIdentity = userIdentityService.getOne(TIdentity.builder().phone(phone).build());
        if (userIdentity==null){
            int i = userIdentityService.insert(TIdentity.builder().phone(phone).identity(identity).build());
            if (i==1){
                return ResponseVo.success(CodeStatus.OPERATION_SUCCESS.getCode(),CodeStatus.OPERATION_SUCCESS.getTip());
            }else {
                return ResponseVo.error(CodeStatus.OPERATION_ERROR.getCode(),CodeStatus.OPERATION_ERROR.getTip());
            }
        }else {
            return ResponseVo.error(CodeStatus.IDENTITY_EXIST.getCode(),CodeStatus.IDENTITY_EXIST.getTip());

        }
    }

    /*
    * 人事查看各个部门的成员状况
    * */
    @GetMapping("/renshi")
    public ResponseVo searchDepartment(@RequestParam("identitycode") int identitycode,@RequestParam("excelcode") int excelcode){
        if (identitycode==19){
            if (excelcode== ExcelCode.OFFICE){
                List<TOffice> offices = officeService.getList(TOffice.builder().build());
                return ResponseVo.success(CodeStatus.OPERATION_SUCCESS.getCode(),CodeStatus.OPERATION_SUCCESS.getTip(),offices);
            }else if (excelcode==ExcelCode.WAREHOUSEWORKER){
                List<TCangku> wareHouseWorkers = wareHouseWorkerService.getList(TCangku.builder().build());
                return ResponseVo.success(CodeStatus.OPERATION_SUCCESS.getCode(),CodeStatus.OPERATION_SUCCESS.getTip(),wareHouseWorkers);
            }else if(excelcode==ExcelCode.WAREHOUSEMANAGER){
                List<TCangkumanager> wareHouseManagers = wareHouseManagerService.getList(TCangkumanager.builder().build());
                return ResponseVo.success(CodeStatus.OPERATION_SUCCESS.getCode(),CodeStatus.OPERATION_SUCCESS.getTip(),wareHouseManagers);
            }else if (excelcode==ExcelCode.WAREHOUSEKAOQIN){
                List<TCangkukaoqin> wareHouseKaoQins = wareHouseKaoQinService.getList(TCangkukaoqin.builder().build() );
                return ResponseVo.success(CodeStatus.OPERATION_SUCCESS.getCode(),CodeStatus.OPERATION_SUCCESS.getTip(),wareHouseKaoQins);
            }
            else {
                return ResponseVo.error(CodeStatus.OPERATION_ERROR.getCode(),CodeStatus.OPERATION_ERROR.getTip());
            }
        }
        //非人事不得查看excel
        else {
            return ResponseVo.error(CodeStatus.OPERATION_ERROR.getCode(),CodeStatus.OPERATION_ERROR.getTip());
        }
    }



}

