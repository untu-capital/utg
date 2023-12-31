package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.PurchaseBehaviourMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseBehaviourMicroRepository extends JpaRepository<PurchaseBehaviourMicro, String> {
    List<PurchaseBehaviourMicro> findAllByLoanId(String id);
}
