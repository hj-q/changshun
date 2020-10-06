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

public class THouqincanjimoney  {
    private Integer id;//

    private String name;//

    private Double jibenmoney;//

    private Double zhiwujixiao;//

    private Double jixiaomoney;//

    private Double yingfajiangli;//

    private Double jiangli;//

    private Double koukuan;//

    private Double baoxian;//

    private Double gonghuifei;//

    private Double getiaoshui;//

    private Double shifamoney;//

    private String beizhu;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
