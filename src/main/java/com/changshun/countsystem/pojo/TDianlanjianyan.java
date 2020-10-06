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

public class TDianlanjianyan  {
    private Integer id;//

    private String name;//

    private String workerid;//

    private Double chuqin;//

    private Double gonglinggongzi;//

    private Double jianglijin;//

    private Double fakuanjin;//

    private Double yebanbutied;//

    private Double quanqin;//

    private Double gaowenfei;//

    private Double gongzi;//

    private Double zongjinge;//

    private Double pinjun;//

    private String beizhu;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
