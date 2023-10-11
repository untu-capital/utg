package com.untucapital.usuite.utg.micro.qualitativeAssesment.repo;

import com.untucapital.usuite.utg.model.InventoryTwoMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryTwoMicroRepository extends JpaRepository<InventoryTwoMicro, String> {
    List<InventoryTwoMicro> findAllByLoanId(String id);
}