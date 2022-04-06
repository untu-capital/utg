package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.MainProductPurchasesMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainProductPurchasesMicroRepository extends JpaRepository<MainProductPurchasesMicro, String> {
    List<MainProductPurchasesMicro> findAllByLoanId(String id);
}
