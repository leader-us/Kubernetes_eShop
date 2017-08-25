package com.mycat.monoeshop.model;

/**
 * Desc:
 *
 * @date: 27/08/2017
 * @author: Leader us
 */
public enum ResultEnum {
    SUCCESS(200, "success"), ERROR(502, "error"), NOT_LOGIN(503, "not login");

    private int code;
    private String desc;

    ResultEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
