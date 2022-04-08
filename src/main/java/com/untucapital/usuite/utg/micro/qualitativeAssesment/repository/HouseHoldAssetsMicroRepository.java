package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.HouseHoldAssetsMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseHoldAssetsMicroRepository extends JpaRepository<HouseHoldAssetsMicro, String> {
    List<HouseHoldAssetsMicro> findAllByLoanId(String id);
}
