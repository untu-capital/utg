package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.PurchaseOrderTransactions;
import com.untucapital.usuite.utg.model.Requisitions;
import com.untucapital.usuite.utg.repository.PurchaseOrderTransactionsRepository;
import com.untucapital.usuite.utg.repository.RequisitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@javax.transaction.Transactional
public class PurchaseOrderTransactionsService {

    @Autowired
    private PurchaseOrderTransactionsRepository purchaseOrderTransactionsRepository;

    @Transactional(value = "transactionManager")
    public List<PurchaseOrderTransactions> getAllPurchaseOrderTransactions() {
        return purchaseOrderTransactionsRepository.findAll();
    }

    @Transactional(value = "transactionManager")
    public void savePurchaseOrderTransaction(PurchaseOrderTransactions purchaseOrderTransactions) {
        purchaseOrderTransactionsRepository.save(purchaseOrderTransactions);
    }

    @Transactional(value = "transactionManager")
    public Optional<PurchaseOrderTransactions> getPurchaseOrderTransactionById(String id) {
        return purchaseOrderTransactionsRepository.findById(id);
    }

    @Transactional(value = "transactionManager")
    public List<PurchaseOrderTransactions> getPurchaseOrderTransactionsByRequisitionId(String id) {
        return purchaseOrderTransactionsRepository.getPurchaseOrderTransactionsByPoRequisitionId(id);
    }

    @Transactional(value = "transactionManager")
    public void deletePurchaseOrderTransaction(String id) {
        purchaseOrderTransactionsRepository.deleteById(id);
    }
}
