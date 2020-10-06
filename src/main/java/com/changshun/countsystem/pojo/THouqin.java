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

public class THouqin  {
    private Integer id;//

    private String workerid;//

    private Double chuqin;//

    private Double jibenmoney;//

    private Double zhiwujintie;//

    private Double jixiao;//

    private Double quanqin;//

    private Double qita;//

    private Double gonglingbutie;//

    private Double total;//

    private String beizhu;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
