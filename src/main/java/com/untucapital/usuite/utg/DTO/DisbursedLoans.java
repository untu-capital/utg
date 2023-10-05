package com.untucapital.usuite.utg.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DisbursedLoans {
    private BigDecimal totalPrincipalDisbursed;
    private List<DisbursedLoanMonth> disbursedLoanMonths;
}
