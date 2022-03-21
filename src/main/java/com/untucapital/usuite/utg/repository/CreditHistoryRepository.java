package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.CreditHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditHistoryRepository extends JpaRepository<CreditHistory, String> {
    List<CreditHistory> findByLoanId(String loanId);
}
