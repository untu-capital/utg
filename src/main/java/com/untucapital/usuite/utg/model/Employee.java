package com.untucapital.usuite.utg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private int id;
    private String firstname;
    private String lastname;
    private String displayName;
    private int officeId;
    private String officeName;
    private boolean isLoanOfficer;
    private boolean isActive;
    private int[] joiningDate;
    private int userId;
    private int tellerId;
}
