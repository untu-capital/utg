package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "musoni_clients")
public class MusoniClient extends AbstractEntity{

    @NotNull
    @Column(nullable = false)
    private String clientId;

    @Column(nullable = false)
    private String accountNo;

    @Column(nullable = false)
    private String externalId;

    private String status;

    @Column(nullable = false)
    private String activationDate;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String displayName;

    @Column(nullable = false)
    private String officeName;

    @Column(nullable = false)
    private String submittedByUsername;

    @Column(nullable = false)
    private String savingsAccountId;



}
