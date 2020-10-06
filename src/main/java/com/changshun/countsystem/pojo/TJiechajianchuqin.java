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

public class TJiechajianchuqin  {
    private Integer id;//

    private String zhiyuandaihao;//

    private String workerid;//

    private String name;//

    private String gangwei;//

    private Double leijichuqin;//

    private Double butuochanzuzhangbuzhu;//

    private Double jijianmoney;//

    private Double totalmoney;//

    private Double pinjunzhi;//

    private Double gonglin;//

    private Double gonglingongzi;//

    private Double quanqin;//

    private Double gaowen;//

    private Double shifamoney;//

    private Date time;//

    private String bumen;//

	private Date create_time_begin;

	private Date create_time_end;}
