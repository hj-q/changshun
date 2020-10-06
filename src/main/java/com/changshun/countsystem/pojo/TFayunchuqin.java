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

public class TFayunchuqin  {
    private Integer id;//

    private String workerid;//

    private String name;//

    private Double leijichuqin;//

    private Double junzhi;//

    private Double money;//

    private Double qita;//

    private Double shifamoney;//

    private Double quanqin;//

    private Double gaowenmoney;//

    private Double total;//

    private String beizhu;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
