package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.MostImportantClients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MostImportantClientsRepository extends JpaRepository<MostImportantClients, String> {
    List<MostImportantClients> findByLoanId(String loanId);
}
