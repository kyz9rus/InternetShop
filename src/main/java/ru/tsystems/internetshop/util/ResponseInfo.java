package ru.tsystems.internetshop.util;

import lombok.Data;

@Data
public class ResponseInfo {
    private int statusCode;
    private String message;

    public ResponseInfo(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
