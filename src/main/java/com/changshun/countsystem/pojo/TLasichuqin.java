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

public class TLasichuqin  {
    private Integer id;//

    private String workerid;//

    private String name;//

    private Double leijichuqin;//

    private Double zuzhangbutie;//

    private Double jiangli;//

    private Double chufa;//

    private Double jijiangongzi;//

    private Double gonglingmoney;//

    private Double yebanfei;//

    private Double gaowenfei;//

    private Double quanqingjiang;//

    private Double totalmoney;//

    private Double yingfamoney;//

    private Double pinjunzhi;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
