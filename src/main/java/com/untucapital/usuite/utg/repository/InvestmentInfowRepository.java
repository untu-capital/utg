package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.InvestmentInfow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface InvestmentInfowRepository extends JpaRepository<InvestmentInfow, String> {
    List<InvestmentInfow> findInvestmentInfowByLoanId(String loanId);
}
