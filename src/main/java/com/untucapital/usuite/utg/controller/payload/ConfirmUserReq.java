package com.untucapital.usuite.utg.controller.payload;

import javax.validation.constraints.NotBlank;

/**
 * @author Chirinda Nyasha Dell 23/11/2021
 */

public class ConfirmUserReq {

    @NotBlank
    private String username;

    @NotBlank
    private String confirmationCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }
}
