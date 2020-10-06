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

public class TCangku  {
    private Integer id;//

    private String department;//

    private String name;//

    private String workerid;//

    private Double chuqin;//

    private Double unitprice;//

    private Double count;//

    private Double xiaoji;//

    private Double quanqinjiang;//

    private Double gaowenfei;//

    private Double buzhu;//

    private Double education;//

    private Double gongling;//

    private Double gonglingmoney;//

    private Double practical;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
