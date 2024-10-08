package com.untucapital.usuite.utg.model.metabase;

import com.untucapital.usuite.utg.dto.loanObjects.*;
import com.untucapital.usuite.utg.dto.musoni.savingsaccounts.AuditData;
import com.untucapital.usuite.utg.model.AbstractEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "musoni_loans")
@Data
public class MusoniLoans extends AbstractEntity {

    private Integer totalFilteredRecords;

    // One-to-many relationship with Loan
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "musoniLoans")
//    private List<Loan> pageItems;

    // Getters and Setters...
}

// Other embeddable classes for LoanType, Currency, etc. remain unchanged, so here’s one as an example:

// Rest of the embeddable classes (Currency, TermPeriodFrequencyType, etc.) follow a similar pattern...
