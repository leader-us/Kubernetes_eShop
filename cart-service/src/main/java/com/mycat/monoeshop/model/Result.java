package com.mycat.monoeshop.model;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */
public class Result<T> {
    private int code;
    private String desc;
    private T data;

    public Result()
    {
    
    }
    public Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.desc = resultEnum.getDesc();
    }

    public Result(ResultEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.desc = resultEnum.getDesc();
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
