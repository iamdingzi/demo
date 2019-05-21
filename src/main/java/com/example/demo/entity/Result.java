package com.example.demo.entity;

public class Result<T> {

    private String message;

    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static  Result ok() {
        Result<String> resultEntiy = new Result<>();
        resultEntiy.setData("{}");
        resultEntiy.setMessage("SUCESSS");
        return resultEntiy;
    }

    public static <T> Result ok(T data) {
        Result<T> resultEntiy = new Result<>();
        resultEntiy.setData(data);
        resultEntiy.setMessage("SUCESSS");
        return resultEntiy;
    }

    public static <T> Result ok(String message, T data) {
        Result<T> resultEntiy = new Result<>();
        resultEntiy.setData(data);
        resultEntiy.setMessage(message);
        return resultEntiy;
    }

    public static Result error(String message) {
        Result<String> resultEntiy = new Result<>();
        resultEntiy.setMessage(message);
        resultEntiy.setData("{}");
        return resultEntiy;
    }
}
