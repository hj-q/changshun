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

public class TXianshubanzuzhang  {
    private Integer id;//

    private String workerid;//

    private String name;//

    private String gangwei;//

    private Double chejianpingjunmoney;//

    private Double gangweimoney;//

    private Double avgmoneybyday;//

    private Double gongshi;//

    private Double total;//

    private Double kouchujixiaogongzi;//

    private Double gonglinggongzi;//

    private Double gaowen;//

    private Double quanqing;//

    private Double total2;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
