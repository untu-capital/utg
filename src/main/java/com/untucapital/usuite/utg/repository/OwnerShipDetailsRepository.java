package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.OwnershipDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerShipDetailsRepository extends JpaRepository<OwnershipDetails, String> {
    List<OwnershipDetails> findByLoanId(String loanId);

}
