package com.example.demo.entity;
public class ResponseData<T> {

    private String message;
    private String code;
    private T data = null;

    public ResponseData(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{"
                + "message='" + message + '\''
                + ", code='" + code + '\''
                + ", data=" + data
                + '}';
    }
}
