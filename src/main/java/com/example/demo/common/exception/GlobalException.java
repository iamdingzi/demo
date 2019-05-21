package com.example.demo.common.exception;

/**
 * Created by Administrator on 2019/5/20/020.
 * globalException
 */
public class GlobalException extends Exception{

    private int code;

    GlobalException(String message) {
        super(message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
