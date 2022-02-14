package com.untucapital.usuite.utg.controller.payload;

import java.time.LocalDateTime;

/**
 * @author Chirinda Nyasha Dell 23/11/2021
 */

public class UsuiteApiErrorResp {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;

    public UsuiteApiErrorResp(int status, String error, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
