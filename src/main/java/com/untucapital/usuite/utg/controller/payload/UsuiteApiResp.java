package com.untucapital.usuite.utg.controller.payload;

/**
 * @author Chirinda Nyasha Dell 23/11/2021
 */

public class UsuiteApiResp {

    private String message;

    public UsuiteApiResp(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
