package com.changshun.countsystem.common;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseVo {

    private int code;
    private String tip;
    private Object data;

    //无参数成功返回
    public static ResponseVo success(int code, String tip){
        return new ResponseVo(code,tip);
    }

    //有参数返回
    public static ResponseVo success(int code, String tip, Object data){
        return new ResponseVo(code,tip,data);
    }

    //失败返回
    public  static ResponseVo error(int code, String tip){
        return new ResponseVo(code,tip);
    }


    public ResponseVo(int code, String tip) {
        this.code = code;
        this.tip = tip;
    }

    public ResponseVo(int code, String tip, Object data) {
        this.code = code;
        this.tip = tip;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
