package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.FixedAsset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FixedAssetsRepository extends JpaRepository<FixedAsset, String> {
    List<FixedAsset> findFixedAssetByLoanId(String loanId);
}
