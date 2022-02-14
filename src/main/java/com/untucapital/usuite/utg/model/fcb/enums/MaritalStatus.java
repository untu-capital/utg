package com.untucapital.usuite.utg.model.fcb.enums;

/**
 * @author Chirinda Nyasha Dell - 8/12/2021
 */

public enum MaritalStatus {
    SINGLE("S"),
    MARRIED("M"),
    DIVORCED("D"),
    WIDOWED("W");

    public final String code;

    MaritalStatus(String code) {
        this.code = code;
    }
}
