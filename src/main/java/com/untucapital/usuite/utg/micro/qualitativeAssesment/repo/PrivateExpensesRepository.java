package com.untucapital.usuite.utg.micro.qualitativeAssesment.repo;

import com.untucapital.usuite.utg.model.PrivateExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivateExpensesRepository extends JpaRepository<PrivateExpenses, String> {
    List<PrivateExpenses> findAllByLoanId(String id);
}