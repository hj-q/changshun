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

public class TJiechajianjishi  {
    private Integer id;//

    private String workerid;//

    private String name;//

    private String gangwei;//

    private Double hours;//

    private Double junzhi;//

    private Double total;//

    private Double gonglingmoney;//

    private Double banjijianbutie;//

    private Double jiagongchanzhi;//

    private Double gaowen;//

    private Double quanqin;//

    private Double total2;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
