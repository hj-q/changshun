package com.changshun.countsystem.common;

public enum CodeStatus {

    SINGLESIGNON_JUMP(1,"上海长顺电梯电缆有限公司工资统计系统界面"),
    OPERATION_SUCCESS(2,"操作成功"),
    OPERATION_ERROR(3,"操作失败"),
    SUPPERMANAGER_IDENTITY_TRUE(4,"您是超级管理员"),
    SUPPERMANAGER_IDENTITY_FALSE(5,"您不是超级管理员"),
    SINGLESIGNON_WARN(6,"非公司成员，无法进入"),
    IDENTITY_SHOW(7,"展示身份"),
    EXCEPTION_TIP(8,"出现异常"),
    IDENTITY_ERROR(9,"您没有身份，无法进入相关页面，请联系超级管理员"),
    IDENTITY_EXIST(10,"身份已经设置，不可再次设置"),
    SEARCH_ERROR(11,"查询错误"),
    IDENTITY_TIP(12,"身份错误，无法执行相关操作");
    int code;
    String tip;

    CodeStatus(int code, String tip) {
        this.code = code;
        this.tip = tip;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
