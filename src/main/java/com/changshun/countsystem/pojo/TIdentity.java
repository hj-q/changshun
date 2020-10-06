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

public class TIdentity  {
    private Integer id;//

    private String phone;//

    private String identity;//

	private Date create_time_begin;

	private Date create_time_end;}
