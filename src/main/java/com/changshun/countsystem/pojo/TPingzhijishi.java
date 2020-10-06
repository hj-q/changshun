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

public class TPingzhijishi  {
    private Integer id;//

    private String workerid;//

    private String name;//

    private String gangwei;//

    private Double chuqindays;//

    private Double fadingdays;//

    private Double jiabanqueqingdays;//

    private Double jibenmoney;//

    private Double gangweimoney;//

    private Double mingyijixiao;//

    private Double jixiaokaohejieguo;//

    private Double shijijixiaogongzi;//

    private Double gonglinggongzi;//

    private Double zizhibutie;//

    private Double jiabangongzi;//

    private Double gaowenfei;//

    private Double quanqin;//

    private Double total;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
