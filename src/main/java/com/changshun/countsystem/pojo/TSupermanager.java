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

public class TSupermanager  {
    private Integer id;//

    private String count;//

    private String password;//

	private Date create_time_begin;

	private Date create_time_end;}
