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

public class TXianshujishirenyuan  {
    private Integer id;//

    private String workerid;//

    private String name;//

    private String gangwei;//

    private Double leijichuqing;//

    private Double jishigongzibyday;//

    private Double jishigongzi;//

    private Double gonglinggongzi;//

    private Double gaowen;//

    private Double quanqing;//

    private Double totalmoney;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
