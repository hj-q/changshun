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

public class TJihuachuqin  {
    private Integer id;//

    private String workerid;//

    private String name;//

    private String gangwei;//

    private Double leijichuqin;//

    private Double qingjia;//

    private Double jibenmoney;//

    private Double zhiwujintie;//

    private Double jixiaomoney;//

    private Double fadingdays;//

    private Double jiabanmoney;//

    private Double total;//

    private Double gonglinmoney;//

    private Double gaowenquanqin;//

    private Double shifamoney;//

    private Double gonglin;//

    private String zaizhiqingkuang;//

    private Double pinjun;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
