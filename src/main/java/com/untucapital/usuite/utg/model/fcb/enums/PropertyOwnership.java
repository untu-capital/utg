package com.untucapital.usuite.utg.model.fcb.enums;

/**
 * @author Chirinda Nyasha Dell - 8/12/2021
 */

public enum PropertyOwnership {
    OWNED(1),
    RENTED(2),
    MORTGAGED(3),
    PARENTS(4),
    EMPLOYER_OWNED(5);

    public final int code;

    PropertyOwnership(int code) {
        this.code = code;
    }

}
