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

public class TDianlanchuqin  {
    private Integer id;//

    private String zhiyuandaima;//

    private String name;//

    private String workerid;//

    private String gangwei;//

    private Double chuqin;//

    private Double zuzhangbutie;//

    private Double gonglingongzi;//

    private Double jianglijinge;//

    private Double fakuanjinge;//

    private Double yebanbutie;//

    private Double quanqin;//

    private Double gaowen;//

    private Double jijiangongzi;//

    private Double zongjinge;//

    private Double pinjun;//

    private String beizhu;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
