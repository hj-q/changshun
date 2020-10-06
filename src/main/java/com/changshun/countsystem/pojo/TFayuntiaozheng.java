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

public class TFayuntiaozheng  {
    private Integer id;//

    private String name;//

    private Double muqianprice;//

    private Double tiaozhengprice;//

    private String zhiwei;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
