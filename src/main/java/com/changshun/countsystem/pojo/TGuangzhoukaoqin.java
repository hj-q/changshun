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

public class TGuangzhoukaoqin  {
    private Integer id;//

    private String workerid;//

    private String name;//

    private Double chuqintotal;//

    private Double zhehedays;//

    private Double shijiatotal;//

    private Double cantiecishu;//

    private Double yingchuqindays;//

    private Double chidaocishu;//

    private Date lizhitime;//

    private String zubie;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
