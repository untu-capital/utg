package com.untucapital.usuite.utg.model.fcb.enums;

/**
 * @author Chirinda Nyasha Dell - 8/12/2021
 */

public enum SearchPurpose {
    NEW_CUSTOMER(1),
    NEW_LOAN_APPLICATION(2),
    LOAN_RENEWAL_REPEAT_LOAN(3),
    PERIODIC_ACC_REVIEW(4),
    NON_PERFORMING_ACC(5),
    RECOVERY_COLLECTION(6),
    SUSPICIOUS_ACTIVITY(7),
    COMPULSORY_CLOSURE(8),
    OTHER(9),
    MEDICAL_HEALTH(10),
    INSURANCE(11),
    SCHOOL_LOANS(12),
    PORTFOLIO_MONITORING(13),
    FCB_SCORE(14),
    MY_CREDIT_REPORT(15);

    public final int code;

    SearchPurpose(int code) {
        this.code = code;
    }
}
