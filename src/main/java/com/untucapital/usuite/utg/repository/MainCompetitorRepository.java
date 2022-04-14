package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.MainCompetitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainCompetitorRepository extends JpaRepository<MainCompetitor, String> {
    List<MainCompetitor> findByLoanId(String loanId);
}
