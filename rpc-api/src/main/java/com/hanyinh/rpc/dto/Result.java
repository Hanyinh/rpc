package com.hanyinh.rpc.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回结构类
 *
 * @author Hanyinh
 * @date 2020/9/20 12:30
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 3530174822309735029L;

    private static final String SUCCESS_MSG = "成功";

    private static final Integer SUCCESS_CODE = 1;

    private static final Integer FAIL_CODE = 0;

    private String msg;

    private Integer code;

    private Boolean success;

    private T re;

    public Result(String msg, Integer code, Boolean success, T re) {
        this.msg = msg;
        this.code = code;
        this.success = success;
        this.re = re;
    }

    public static <T> Result<T> success(String msg, T t) {
        return new Result<>(msg, SUCCESS_CODE, true, t);
    }

    public static <T> Result<T> success(T t) {
        return new Result<>(SUCCESS_MSG, SUCCESS_CODE, true, t);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<T>(msg, FAIL_CODE, false, null);
    }
}
