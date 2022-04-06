package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.PastPurchasesMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PastPurchasesMicroRepository extends JpaRepository<PastPurchasesMicro, String> {
    List<PastPurchasesMicro> findAllByLoanId(String id);
}
