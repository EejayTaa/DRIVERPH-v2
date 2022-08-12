package com.example.SpringBootREST.common;

import com.example.SpringBootREST.utils.DateUtils;

import java.util.Date;

public class ResultGenerator {

    public static Result genSuccessResult(){
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(DateUtils.toStringYMDHM(new Date()));
        return result;
    }

    public static Result genCreatedResult(){
        Result result = new Result(ResultCode.CREATED);
        result.setData(DateUtils.toStringYMDHM(new Date()));
        return result;
    }
}
