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

public class TJiechajiankaixianjijian  {
    private Integer id;//

    private String workerid;//

    private String name;//

    private String gangwei;//

    private Double leijichuqin;//

    private Double buzhu;//

    private Double jijiangongzi;//

    private Double totalmoney;//

    private Double pingjunzhi;//

    private Double gongling;//

    private Double gonglingfei;//

    private Double quanqing;//

    private Double gaowenfei;//

    private Double shifamoney;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
