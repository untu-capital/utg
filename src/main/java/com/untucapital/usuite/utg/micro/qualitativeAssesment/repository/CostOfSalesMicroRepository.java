package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.CostOfSalesMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostOfSalesMicroRepository extends JpaRepository<CostOfSalesMicro, String> {
    List<CostOfSalesMicro> findAllByLoanId(String id);
}
