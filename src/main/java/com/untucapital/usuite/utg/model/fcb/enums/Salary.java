package com.untucapital.usuite.utg.model.fcb.enums;

/**
 * @author Chirinda Nyasha Dell - 8/12/2021
 */

public enum Salary {

    _0_TO_150(1),
    _151_TO_250(2),
    _251_TO_500(3),
    _501_TO_1000(4),
    _1001_TO_2000(5),
    _2001_TO_5000(6),
    _OVER_5000(7),
    _UNKNOWN(8);

    public final int code;

    Salary(int code) {
        this.code = code;
    }
}
