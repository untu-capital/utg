package com.untucapital.usuite.utg.model.metabase;

import lombok.Data;

import java.util.List;

@Data
public class LoanApiResponse {
    private int totalFilteredRecords;
    private List<MetabaseLoanData> pageItems;

    // Getters and Setters
}
