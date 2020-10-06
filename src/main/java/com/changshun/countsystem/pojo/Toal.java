package com.changshun.countsystem.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Toal {
    private Integer id;//
    @Excel(name = "标题")
    private String workerid;//
    private String workercode;//
    private String name;//
    private String gangwei;//
    private Double chuqin;//
    private Double jijiansalary;//
    private Double jiabanfei;//
    private Double total;//
    private Double gongling;//
    private Double gonglingmoney;//
    private Double quanqin;//
    private Double jixiao;//
    private Double jiaban;//
    private Double gaowenfei;//
    private Double buzhu;//
    private String beizhu;//
    private Double practical;//
    private Double jianglijinge;//
    private Double fakuanjinge;//
    private Date time;//
    private String bumen;//
    private Date create_time_begin;
    private Date create_time_end;
}
