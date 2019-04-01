package ru.tsystems.internetshop.util;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class ResponseInfo {
    private String message;

    public ResponseInfo(String message) {
        this.message = message;
    }
}
