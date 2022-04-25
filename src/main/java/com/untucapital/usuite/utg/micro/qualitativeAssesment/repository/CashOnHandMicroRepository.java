package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.CashOnHandMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashOnHandMicroRepository extends JpaRepository<CashOnHandMicro, String> {
    List<CashOnHandMicro> findAllByLoanId(String id);
}
