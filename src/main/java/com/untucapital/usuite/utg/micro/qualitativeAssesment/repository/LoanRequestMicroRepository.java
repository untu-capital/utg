package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.LoanRequestMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRequestMicroRepository extends JpaRepository<LoanRequestMicro, String > {
    List<LoanRequestMicro> findAllByLoanId(String id);
}
