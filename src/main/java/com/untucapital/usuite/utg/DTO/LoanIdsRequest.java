package com.untucapital.usuite.utg.dto;

import java.util.List;

public class LoanIdsRequest {
    private List<String> loanIds;

    // Getters and Setters
    public List<String> getLoanIds() {
        return loanIds;
    }

    public void setLoanIds(List<String> loanIds) {
        this.loanIds = loanIds;
    }
}

