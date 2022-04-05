package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.OtherBusinessAndIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtherBusinessAndIncomeRepository extends JpaRepository<OtherBusinessAndIncome, String> {
    List<OtherBusinessAndIncome> findAllByLoanId(String id);
}
