package com.example.SpringBootREST.common;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;

public class Result<T> {
    private String message;
    private T data;
    private String requestId;
    private HttpStatus code;

    // 对外接口需要的参数
    private String resultCode;
    private String resultStatus;

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getDesc();
    }

    public Result(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result() {
    }



    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public HttpStatus getCode() {
        return code;
    }

    public String getRequestId() {
        return requestId;
    }

    public Result setCode(HttpStatus code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> Result<T> OK() {
        Result<T> r = new Result<T>();
        r.setCode(HttpStatus.OK);
        r.setMessage("Success");
        return r;
    }

    public static <T> Result<T> OK(T data) {
        Result<T> r = new Result<T>();
        r.setCode(HttpStatus.OK);
        r.setData(data);
        r.setMessage("Success");
        return r;
    }

    public static <T> Result<T> OK(String message, T data) {
        Result<T> r = new Result<T>();
        r.setCode(HttpStatus.OK);
        r.setData(data);
        r.setMessage(message);
        return r;
    }
    public static <T> Result<T> OK(String message, T data, HttpStatus code) {
        Result<T> r = new Result<T>();
        r.setCode(code);
        r.setData(data);
        r.setMessage(message);
        return r;
    }


    public static Result<Object> error(String msg, String requestId) {
        Result<Object> r = new Result<Object>();
        r.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
        r.setMessage(msg);
        r.setRequestId(requestId);
        return r;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public static Result<Object> error(HttpStatus code, String msg, String requestId) {
        Result<Object> r = new Result<Object>();
        r.setCode(code);
        r.setMessage(msg);
        r.setRequestId(requestId);
        return r;
    }

    /**
     * 具体细节：OpenServerHttpStatusEnums
     *
     * @param resultNum         响应编码
     * @param resultCode        响应字符串编码
     * @param resultDescription 响应描述
     * @param resultStatus      响应状态
     * @param data              数据
     * @param requestId         错误编号id
     * @return
     */
    public static <T> Result<T> resultInfo(
            HttpStatus resultNum,
            String resultCode,
            String resultDescription,
            String resultStatus,
            T data,
            String requestId
    ) {
        Result<T> result = new Result<T>();
        result.setCode(resultNum);
        result.setResultCode(resultCode);
        result.setResultStatus(resultStatus);
        result.setMessage(resultDescription);
        result.setData(data);
        result.setRequestId(requestId);
        return result;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
