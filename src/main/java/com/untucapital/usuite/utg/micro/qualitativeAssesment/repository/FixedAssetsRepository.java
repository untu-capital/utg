package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.FixedAssets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FixedAssetsRepository extends JpaRepository<FixedAssets, String > {
    List<FixedAssets> findAllByLoanId(String id);
}
