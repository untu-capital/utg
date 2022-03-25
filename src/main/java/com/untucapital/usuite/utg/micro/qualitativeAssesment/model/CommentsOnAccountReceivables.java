package com.untucapital.usuite.utg.micro.qualitativeAssesment.model;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CommentsOnAccountReceivablesMicro")
public class CommentsOnAccountReceivables extends AbstractEntity {
    private String loanId;
    private String description;
}
