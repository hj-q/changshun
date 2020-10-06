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

public class TDianlanjiangli  {
    private Integer id;//

    private String workerid;//

    private String name;//

    private String gongwei;//

    private Double jielintou;//

    private Double jiucuo;//

    private Double wuzhiliang;//

    private Double gongyijianyi;//

    private Double jianglijinge;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
