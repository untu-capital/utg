package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.SeasonalityOfSalesMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonalityOfSalesMicroRepository extends JpaRepository<SeasonalityOfSalesMicro, String> {

    List<SeasonalityOfSalesMicro> findAllByLoanId(String id);
}
