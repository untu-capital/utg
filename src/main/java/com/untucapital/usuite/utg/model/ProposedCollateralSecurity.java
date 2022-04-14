package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "proposedCollateralSecurity")
public class ProposedCollateralSecurity extends AbstractEntity {
    @NotNull
    @Column(nullable = false)
    private String loanId;
    private String type;
    private String description;
    private String ownership;
    private String marketValue;
    private String discountValue;

}
