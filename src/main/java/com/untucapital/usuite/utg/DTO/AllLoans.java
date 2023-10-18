package com.untucapital.usuite.utg.DTO;


import com.untucapital.usuite.utg.DTO.loanObjects.Loan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllLoans {
    private Integer totalFilteredRecords;
    private List<Loan> pageItems;
}
