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

public class TJiaqi  {
    private Integer id;//

    private String workerid;//

    private String department;//

    private String name;//

    private String qingjiaxingzhi;//

    private Double days;//

    private Double biaozhunmoneybyday;//

    private Double moneybyday;//

    private String company;//

    private String beizhu;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
