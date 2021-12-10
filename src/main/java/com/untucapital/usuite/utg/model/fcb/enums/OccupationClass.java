package com.untucapital.usuite.utg.model.fcb.enums;

/**
 * @author Chirinda Nyasha Dell - 8/12/2021
 */

public enum OccupationClass {

    N_A(0),
    MANAGER(1),
    PROFESSIONAL(2),
    TECHNICIAN_AND_ASSOCIATE_PRO(3),
    CLERICAL_SUPPORT_WORKER(4),
    SERVICE_AND_SALES_WORKERS(5),
    SKILLED_AGRIC_FORESTRY_AND_FISHERY_WORKER(6),
    CRAFT_AND_RELATED_TRADES_WORKER(7),
    PLANT_AND_MACHINE_OPERATOR_AND_ASSEMBLER(8),
    ELEMENTARY_OCCUPANT(9),
    ARMED_FORCES_OCCUPANT(10),
    UNEMPLOYED(11);

    public final int code;

    OccupationClass(int code) {
        this.code = code;
    }
}
