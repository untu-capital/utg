package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.CurrentAsset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrentAssetsRepository extends JpaRepository<CurrentAsset, String> {
    List<CurrentAsset> findCurrentAssetByLoanId(String loanId);
}
