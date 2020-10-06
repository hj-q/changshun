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

public class TDianlankoufa  {
    private Integer id;//

    private String zhiyuandaima;//

    private String name;//

    private String gongwei;//

    private Double guocheng;//

    private Double neibutousu;//

    private Double guifanzhixing;//

    private Double koufajinge;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}