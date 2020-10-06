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

public class TDianlanbanzu  {
    private Integer id;//

    private String name;//

    private String workerid;//

    private Double gongshi;//

    private Double pinjungongzi;//

    private String anbiaozhunjia;//

    private Double shifarigongzi;//

    private Double xiaoji;//

    private String kouchuyiqian;//

    private String jixiaokaohejieguo;//

    private Double jixiaogongzi;//

    private Double shijijixiaogongzi;//

    private Double zongji;//

    private Double gonglin;//

    private Double gaowenfei;//

    private Double quanqinjiang;//

    private Double jianglijinge;//

    private Double fakuanjinge;//

    private Double zongjinge;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
