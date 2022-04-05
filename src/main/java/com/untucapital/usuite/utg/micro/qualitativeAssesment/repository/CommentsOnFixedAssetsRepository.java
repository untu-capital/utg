package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.CommentsOnFixedAssets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsOnFixedAssetsRepository extends JpaRepository<CommentsOnFixedAssets, String> {
    List<CommentsOnFixedAssets> findAllByLoanId(String id);
}
