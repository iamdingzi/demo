package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.example.demo.common.config.ServerStatusEnumDeserializer;
import com.example.demo.common.constant.ServerStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@TableName("SERVER_TABLE")
public class ServerEntity {

    @Id
    @TableId(value = "SERVER_ID", type = IdType.AUTO)
    private int id;

    @TableField("VERSION")
    @Version
    private Integer version;

    @TableField("SERVER_IP")
    @NotNull(message = "The ip field is not allowed to be empty")
    @Pattern(regexp = "((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))", message = "The ip field has incorrect values")
    private String ip;

    @NotNull(message = "The status field has incorrect values")
    @TableField("SERVER_STATUS")
    @JsonDeserialize(using = ServerStatusEnumDeserializer.class)
    private ServerStatus status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public ServerStatus getStatus() {
        return status;
    }

    public void setStatus(ServerStatus status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ServerEntity{" +
                "id=" + id +
                ", version=" + version +
                ", ip='" + ip + '\'' +
                ", status=" + status +
                '}';
    }
}
