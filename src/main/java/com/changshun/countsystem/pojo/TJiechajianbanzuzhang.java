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

public class TJiechajianbanzuzhang  {
    private Integer id;//

    private String workerid;//

    private String name;//

    private String gangwei;//

    private Double chejianpingjungongzi;//

    private String gangweimoney;//

    private Double avgmoneybyday;//

    private Double gongshi;//

    private Double hejigongzi;//

    private Double kouchujixiaogongzi;//

    private Double gonglinggongzi;//

    private Double gaowen;//

    private Double quanqin;//

    private Double total;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
