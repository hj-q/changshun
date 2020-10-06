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

public class TJiechajiankaixianchuqin  {
    private Integer id;//

    private String zhiyuandaima;//

    private String workerid;//

    private String name;//

    private String gangwei;//

    private Double leijichuqin;//

    private Double buzhu;//

    private Double jijianmoney;//

    private Double totalmoney;//

    private Double pinjunzhi;//

    private Double chidao;//

    private Double qingjia;//

    private Double wuqingjiadan;//

    private Double gongling;//

    private Double quanqin;//

    private Double gaowenfei;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
