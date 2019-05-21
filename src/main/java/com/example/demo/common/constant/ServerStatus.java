package com.example.demo.common.constant;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum ServerStatus implements IEnum<Integer> {

    STOPPED(0),
    BOOTING(1),
    STARTED(2);

    private int code;


    ServerStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public Integer getValue() {
        return this.code;
    }
}
