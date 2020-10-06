package com.changshun.countsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TXingzhen  {
    private Integer id;//

    private String identitynumber;//

    private String zhiyuandaima;//

    private String name;//

    private String workerid;//

    private String department;//

    private String shijidepartment;//

    private Double jibenmoney;//

    private Double zhiwumoney;//

    private Double jixiaomoney;//

    private Double zizhimoney;//

    private Double gonglinmoney;//

    private Double total1;//

    private String shifoucanjia;//

    private Double days;//

    private Double qita;//

    private Double quanqin;//

    private Double jiangli;//

    private Double koukuan;//

    private Double gaowenmoney;//

    private Double total2;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
