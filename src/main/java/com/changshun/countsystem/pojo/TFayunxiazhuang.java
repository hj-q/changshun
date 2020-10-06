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

public class TFayunxiazhuang  {
    private Integer id;//

    private String name;//

    private String yangshi;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
