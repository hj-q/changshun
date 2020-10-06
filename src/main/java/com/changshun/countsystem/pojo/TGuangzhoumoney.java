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

public class TGuangzhoumoney  {
    private Integer id;//

    private String workerid;//

    private String name;//

    private String department;//

    private Double days;//

    private Double jiabandays;//

    private Double jishu;//

    private Double yingdea;//

    private Double yingdeb;//

    private Double gangweimoney;//

    private Double jixaiomoneyc;//

    private Double monthabc;//

    private Double quanqinjishu;//

    private Double quanqinyingde;//

    private Double zhusujishu;//

    private Double zhusuyingde;//

    private Double huoshidunshu;//

    private Double huoshiyingde;//

    private Double gaowenyingfa;//

    private Double gaowenshifa;//

    private Double jiangfa;//

    private Double total;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
