package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.CurrentAssetsMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrentAssetsMicroRepository extends JpaRepository<CurrentAssetsMicro, String> {
    List<CurrentAssetsMicro> findAllByLoanId(String id);
}
