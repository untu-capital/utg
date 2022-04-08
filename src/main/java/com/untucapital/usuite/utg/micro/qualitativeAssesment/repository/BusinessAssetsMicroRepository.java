package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.BusinessAssetsMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessAssetsMicroRepository extends JpaRepository<BusinessAssetsMicro, String> {
    List<BusinessAssetsMicro> findAllByLoanId(String id);
}
