package com.changshun.countsystem.common;

import java.util.HashMap;

public class IdentityShow {

    public static int getIdentity(String identity){

        HashMap<String, Integer> map = new HashMap<>();
        map.put("身份不明",0);
        map.put("办公室全勤",1);
        map.put("仓库员工薪资",21);
        map.put("仓库班组长薪资",22);
        map.put("仓库考勤",23);
        map.put("电缆车间工资",3);
        map.put("发运部工资",4);
        map.put("广州车间工资及工时统计",5);
        map.put("行政部",6);
        map.put("后勤部工资表",7);
        map.put("计划员工资",8);
        map.put("假期工资",9);
        map.put("接插件班组长计时工资表",10);
        map.put("接插件车间及配件组计件工资明细",11);
        map.put("开线组",12);
        map.put("拉丝车间工资",13);
        map.put("品质部工资统计表",14);
        map.put("线束车间班组工资表",15);
        map.put("线束车间工资",16);
        map.put("质量罚款汇总",17);
        map.put("质量奖励汇总",18);
        map.put("人事",19);
        map.put("财务",20);
        for (String ide: map.keySet()) {
            if (ide.equals(identity)){
                return map.get(ide);
            }
        }
        return 0;


    }

}
