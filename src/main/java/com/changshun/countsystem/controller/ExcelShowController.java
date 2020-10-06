package com.changshun.countsystem.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.changshun.countsystem.common.CodeStatus;
import com.changshun.countsystem.common.ExcelCode;
import com.changshun.countsystem.common.ResponseVo;
import com.changshun.countsystem.pojo.*;
import com.changshun.countsystem.pojo.TOffice;
import com.changshun.countsystem.pojo.TCangkukaoqin;
import com.changshun.countsystem.pojo.TCangkumanager;
import com.changshun.countsystem.pojo.TCangku;
import com.changshun.countsystem.service.*;
import com.changshun.countsystem.service.TOfficeService;
import com.changshun.countsystem.service.TCangkukaoqinService;
import com.changshun.countsystem.service.TCangkumanagerService;
import com.changshun.countsystem.service.TCangkuService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/excelsearch")
public class ExcelShowController {

    @Autowired
    private TOfficeService officeService;

    @Autowired
    private TCangkuService wareHouseWorkerService;

    @Autowired
    private TCangkumanagerService wareHouseManagerService;
    @Autowired
    private ToalService toalService;

    @Autowired
    private TCangkukaoqinService wareHouseKaoQinService;

    //用于存储缓存中的数据
    static Map totalExcelMap=new HashMap();

    //根据用户的身份码来查看对应的excel内容
    @GetMapping("/show")
    public ResponseVo officeShow(@RequestParam("identitycode") int identitycode,@RequestParam( "currentPage")int currentPage,
                                 @RequestParam("pageSize") int pageSize, @RequestParam(value = "create_time_begin",required = false) Date create_time_begin,
                                 @RequestParam(value = "create_time_end",required = false) Date create_time_end){
        if (identitycode== ExcelCode.OFFICE){
            List<TOffice> officeByPage = officeService.findByPage(currentPage, pageSize, TOffice.builder().build());
            return ResponseVo.success(CodeStatus.OPERATION_SUCCESS.getCode(),CodeStatus.OPERATION_SUCCESS.getTip(),officeByPage);
        }else if (identitycode==ExcelCode.WAREHOUSEWORKER){
            List<TCangku> workerByPage = wareHouseWorkerService.findByPage(currentPage, pageSize, TCangku.builder().build());
            return ResponseVo.success(CodeStatus.OPERATION_SUCCESS.getCode(),CodeStatus.OPERATION_SUCCESS.getTip(),workerByPage);
        }else if (identitycode==ExcelCode.WAREHOUSEMANAGER){
            List<TCangkumanager> managerByPage = wareHouseManagerService.findByPage(currentPage, pageSize,TCangkumanager.builder().build());
            return ResponseVo.success(CodeStatus.OPERATION_SUCCESS.getCode(),CodeStatus.OPERATION_SUCCESS.getTip(),managerByPage);
        }else if(identitycode==ExcelCode.WAREHOUSEKAOQIN){
            List<TCangkukaoqin> kaoQinByPage = wareHouseKaoQinService.findByPage(currentPage, pageSize, TCangkukaoqin.builder().build());
            return ResponseVo.success(CodeStatus.OPERATION_SUCCESS.getCode(),CodeStatus.OPERATION_SUCCESS.getTip(),kaoQinByPage);
        }
        return ResponseVo.error(CodeStatus.SEARCH_ERROR.getCode(),CodeStatus.SEARCH_ERROR.getTip());
    }

    //人事执行汇总功能，将所有表的内容汇总起来
    //基于spring的caffeineCache缓存技术
    @PostMapping("/gatherexcel")
    public ResponseVo gatherExcel(@RequestParam("identitycode") int identitycode){
        //判断身份码是否为人事的身份码，如果是，则执行汇总功能
        if (identitycode!=19){
            return ResponseVo.error(CodeStatus.IDENTITY_TIP.getCode(),CodeStatus.IDENTITY_TIP.getTip());
        }else{
            List<TOffice> offices = officeService.getList(TOffice.builder().build());
            List<TCangku> wareHouseWorkers = wareHouseWorkerService.getList(TCangku.builder().build());
            List<TCangkumanager> wareHouseManagers = wareHouseManagerService.getList(TCangkumanager.builder().build());
            List<TCangkukaoqin> wareHouseKaoQins = wareHouseKaoQinService.getList(TCangkukaoqin.builder().build());
            /*
             * 办公室全勤加入缓存并读取
             * */
            LinkedList<Object> officelist = new LinkedList<>();
            officeService.addInfo(TOffice.builder().build()); //加入缓存
            for (int i=0;i<offices.size();i++){
                TOffice officeInfo = officeService.getInfo(offices.get(i).getWorkerid());
                officelist.add(officeInfo);
            }
            /*
             * 仓库员工薪资加入缓存并读取
             * */
            LinkedList<Object> workerlist = new LinkedList<>();
            wareHouseWorkerService.addInfo(TCangku.builder().build());//加入缓存
            for (int i=0;i<wareHouseWorkers.size();i++){
                TCangku workerInfo = wareHouseWorkerService.getInfo(wareHouseWorkers.get(i).getWorkerid());
                workerlist.add(workerInfo);
            }
            /*
             * 仓库班组长薪资加入缓存并读取
             * */
            LinkedList<Object> managerlist = new LinkedList<>();
            wareHouseManagerService.addInfo(TCangkumanager.builder().build());//加入缓存
            for (int i=0;i<wareHouseManagers.size();i++){
                TCangkumanager managerInfo = wareHouseManagerService.getInfo(wareHouseManagers.get(i).getWorkerid());
                managerlist.add(managerInfo);
            }
            /*
             * 仓库考勤加入缓存并读取
             * */
            LinkedList<Object> kaoqinlist = new LinkedList<>();
            wareHouseKaoQinService.addInfo(TCangkukaoqin.builder().build());//加入缓存
            for (int i=0;i<wareHouseKaoQins.size();i++){
                TCangkukaoqin kaoQinInfo = wareHouseKaoQinService.getInfo(wareHouseKaoQins.get(i).getWorkerid());
                kaoqinlist.add(kaoQinInfo);
            }
            totalExcelMap.put("office",officelist);
            totalExcelMap.put("worker",workerlist);
            totalExcelMap.put("manager",managerlist);
            totalExcelMap.put("kaoqin",kaoqinlist);
            return ResponseVo.success(CodeStatus.OPERATION_SUCCESS.getCode(),CodeStatus.OPERATION_SUCCESS.getTip());
        }

    }

    //财务查看汇总之后的表数据,从缓存中读取，效率更快
    @GetMapping("/check")
    public ResponseVo output(@RequestParam("identitycode") int identitycode){
        //是否符合财务身份，符合则有权查看，不符合则返回提示
        if (identitycode==20){
            return ResponseVo.success(CodeStatus.OPERATION_SUCCESS.getCode(),CodeStatus.OPERATION_SUCCESS.getTip(),totalExcelMap);
        }else {
            return ResponseVo.error(CodeStatus.IDENTITY_TIP.getCode(),CodeStatus.IDENTITY_TIP.getTip());
        }
    }

    //财务审核完毕,然后导出excel文件。
    // TODO  导出方式可以是将缓存中的数据导出，也可以是将各个excel表格对应的数据库表汇总成一个表导出。
    @RequestMapping(value = "/export",method = RequestMethod.GET)
    private void export(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Toal> list = toalService.getList();
        // 创建easypoi核心工作簿对象
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), Toal.class, list);
        // 设置响应参数
        // 设置响应内容类型
        response.setContentType("application/vnd.ms-excel");
        // 设置文件名
        String filename = new String("人事汇总表.xls".getBytes("UTF-8"), "ISO8859-1");
        response.addHeader("Content-Disposition", "attachment; filename=" + filename);
        // 4.通过响应流将文件传输到浏览器
        workbook.write(response.getOutputStream());

    }

}
