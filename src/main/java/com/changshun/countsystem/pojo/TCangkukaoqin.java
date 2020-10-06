package com.changshun.countsystem.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TCangkukaoqin  {
    private Integer id;//

    private String workercode;//
   @Excel(name = "工作号")
    private String workerid;//

    private String name;//

    private String gangwei;//

    private Double totalchuqin;//

    private Double jijiansalary;//

    private Double totalmoney;//

    private Double average;//

    private Double late;//

    private Double qingjia;//

    private String wuqingjiadan;//

    private Double gongling;//

    private Double quanqin;//

    private Double gaowenfei;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
