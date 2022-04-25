package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, String> {
    @Modifying
    @Query(value = "select * from stock where loan_id = :loanId", nativeQuery = true)
    List<Stock> findbyLoanId(String loanId);
}
