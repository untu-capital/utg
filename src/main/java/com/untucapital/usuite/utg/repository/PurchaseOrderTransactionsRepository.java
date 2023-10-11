package com.untucapital.usuite.utg.repository;


import com.untucapital.usuite.utg.model.PurchaseOrderTransactions;
import com.untucapital.usuite.utg.model.Requisitions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderTransactionsRepository extends JpaRepository<PurchaseOrderTransactions, String> {
    
    List<PurchaseOrderTransactions> getPurchaseOrderTransactionsByPoRequisitionId(String id);
}
