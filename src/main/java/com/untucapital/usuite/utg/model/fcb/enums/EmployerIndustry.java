package com.untucapital.usuite.utg.model.fcb.enums;

/**
 * @author Chirinda Nyasha Dell - 8/12/2021
 */

public enum EmployerIndustry {

    AGRICULTURE(1),
    MANUFACTURING(2),
    MINING_QUARRYING(3),
    ENERGY_WATER(4),
    TRADE(5),
    TOURISM_RESTAURANT_HOTELS(6),
    TRANSPORT(7),
    REAL_ESTATE(8),
    FINANCE(9),
    GOVERNMENT(10),
    OTHER(11),
    NON_EMPLOYED(12),
    UNKNOWN(13),
    HEALTH(14),
    PRIVATE_SECURITY(15),
    POLICE(16),
    ARMY(17),
    PRISONS_CORRECTIONAL_SERVICES(18),
    ICT_COMMUNICATIONS(19),
    RETAIL(20);

    public final int code;

    EmployerIndustry(int code) {
        this.code = code;
    }

}
