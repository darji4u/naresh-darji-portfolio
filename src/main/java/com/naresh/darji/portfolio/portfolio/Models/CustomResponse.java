package com.naresh.darji.portfolio.portfolio.Models;

public class CustomResponse<T> {

    String Status;
    String Message;

    T Data;

    public CustomResponse(String status, String message, T data) {
        Status = status;
        Message = message;
        Data = data;
    }

    public CustomResponse() {
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
