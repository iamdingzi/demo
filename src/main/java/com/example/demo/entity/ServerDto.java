package com.example.demo.entity;

import javax.validation.constraints.Pattern;

/**
 * Created by Administrator on 2019/5/21/021.
 */
public class ServerDto {

    /**
     *  主键id
     */
    private int id;

    /**
     *  状态 The status could be one of the following: STOPPED, BOOTING, STARTED
     */
    @Pattern(regexp = "(STOPPED|BOOTING|STARTED)",message = "The status field has incorrect values")
    private String status;
    /**
     *  ip address
     */
    @Pattern(regexp = "((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))", message = "The ip field has incorrect values")
    private String ip;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
