package com.ph.DriverPH.common;

import org.springframework.http.HttpStatus;

public enum ResultCode {
    SUCCESS( HttpStatus.OK, "SUCCESS" ),//成功
    CREATED(HttpStatus.CREATED, "CREATED"),
    FAILURE( HttpStatus.BAD_REQUEST, "FAILURE" ),//失败
    UNAUTHORIZED( HttpStatus.UNAUTHORIZED, "Not signed in"),//"未登录" ),//未认证（签名错误、token错误）
    NOT_FOUND( HttpStatus.NOT_FOUND, "Not found");//"网络错误联系客服(4004)" ),//接口不存在


    private HttpStatus code;
    private String desc;

    ResultCode(HttpStatus code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
