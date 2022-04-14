package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.FixedAssetsMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FixedAssetsMicroRepository extends JpaRepository<FixedAssetsMicro, String > {
    List<FixedAssetsMicro> findFixedAssetsMicroByLoanId(String loanId);
}
