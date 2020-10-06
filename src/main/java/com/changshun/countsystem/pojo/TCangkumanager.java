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

public class TCangkumanager  {
    private Integer id;//

    private String name;//

    private String workerid;//

    private Double workertime;//

    private Double standardtime;//

    private Double guding;//

    private Double gangwei;//

    private Double jixiao;//

    private Double quanqin;//

    private Double education;//

    private Double xiaojione;//

    private Double buzhu;//

    private Double xiaojitwo;//

    private Double jiaban;//

    private Double unitprice;//

    private Double jiabanfei;//

    private Double gongling;//

    private Double gonglingmoney;//

    private Double gaowenfei;//

    private Double total;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
