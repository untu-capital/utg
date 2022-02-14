package com.untucapital.usuite.utg.model.fcb.enums;

/**
 * @author Chirinda Nyasha Dell - 8/12/2021
 */

public enum LoanPurpose {

    CURRENT_ACC_OVERDRAFT(1),
    PERSONAL_LOAN_ACC(2),
    AUTO_LOAN(3),
    EDUCATIONAL_LOAN(4),
    HOME_IMPROVEMENT_LOAN(5),
    CONSOLIDATION_LOAN(6),
    CREDIT_CARD(7),
    LINE_OF_CREDIT(8),
    REVOLVING_CREDIT(9),
    BUSINESS_ASSET_LOAN(10),
    BUSINESS_IMPROVEMENT_LOAN(11),
    RENEWABLE_ENERGY_LOAN(12),
    WHOLESALE_LENDING(13),
    OTHER(14);

    public final int code;

    LoanPurpose(int code) {
        this.code = code;
    }
}
