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

public class TFayunrenyuan  {
    private Integer id;//

    private String workerid;//

    private String name;//

    private String beizhu;//

    private Date time;//

	private Date create_time_begin;

	private Date create_time_end;}
