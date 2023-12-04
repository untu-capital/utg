package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.po.Requisitions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RequisitionRepository extends JpaRepository<Requisitions, String> {

    Optional<Requisitions> getRequisitionsByPoNumber(String poNumber);

    List<Requisitions> findRequisitionsByUserId(String userId);

    List<Requisitions> findRequisitionsByPoApproverIsNotNullAndCmsApproverIsNotNull();

    List<Requisitions> findRequisitionByApprovers(String userId);

    List<Requisitions> findRequisitionsByPoApproverIsNotNullAndCmsApproverIsNull();

    List<Requisitions> findRequisitionsByTeller(String tellerId);

//    List<Requisitions> findRequisitionsBy
}
