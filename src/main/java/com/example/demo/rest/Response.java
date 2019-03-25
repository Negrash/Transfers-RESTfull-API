package com.example.demo.rest;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Response {
    SUCCESS(100, "SUCCESS"),
    FAILURE(102, "FAILURE");

    private final int code;
    private final String status;

    Response(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }
}

