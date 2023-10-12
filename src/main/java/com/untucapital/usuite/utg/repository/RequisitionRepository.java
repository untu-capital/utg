package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.Requisitions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequisitionRepository extends JpaRepository<Requisitions, String> {

    Optional<Requisitions> getRequisitionsByPoNumber(String poNumber);

}
