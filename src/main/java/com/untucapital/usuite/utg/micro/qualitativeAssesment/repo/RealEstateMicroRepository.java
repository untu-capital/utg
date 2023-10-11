package com.untucapital.usuite.utg.micro.qualitativeAssesment.repo;

import com.untucapital.usuite.utg.model.RealEstateMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealEstateMicroRepository extends JpaRepository<RealEstateMicro, String> {
    List<RealEstateMicro> findAllByLoanId(String id);
}