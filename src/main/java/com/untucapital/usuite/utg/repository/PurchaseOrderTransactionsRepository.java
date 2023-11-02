package com.untucapital.usuite.utg.repository;


import com.untucapital.usuite.utg.model.PurchaseOrderTransactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseOrderTransactionsRepository extends JpaRepository<PurchaseOrderTransactions, String> {

    List<PurchaseOrderTransactions> getPurchaseOrderTransactionsByPoRequisitionId(String id);
}
