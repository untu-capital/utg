package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.MostImportantSuppliers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MostImportantSuppliersRepository extends JpaRepository<MostImportantSuppliers, String> {
    List<MostImportantSuppliers> findByLoanId(String loanId);
}
