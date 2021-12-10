package com.untucapital.usuite.utg.model.fcb.enums;

/**
 * @author Chirinda Nyasha Dell - 8/12/2021
 */

public enum PropertyDensity {

    LOW(1),
    MEDIUM(2),
    HIGH(3),
    RURAL(4),
    INDUSTRIAL(5);

    public final int code;

    PropertyDensity(int code) {
        this.code = code;
    }
}
