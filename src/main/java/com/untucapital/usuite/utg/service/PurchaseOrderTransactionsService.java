package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.PurchaseOrderTransactions;
import com.untucapital.usuite.utg.model.Requisitions;
import com.untucapital.usuite.utg.repository.PurchaseOrderTransactionsRepository;
import com.untucapital.usuite.utg.repository.RequisitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PurchaseOrderTransactionsService {

    @Autowired
    private PurchaseOrderTransactionsRepository purchaseOrderTransactionsRepository;

    public List<PurchaseOrderTransactions> getAllPurchaseOrderTransactions() {
        return purchaseOrderTransactionsRepository.findAll();
    }

    public void savePurchaseOrderTransaction(PurchaseOrderTransactions purchaseOrderTransactions) {
        purchaseOrderTransactionsRepository.save(purchaseOrderTransactions);
    }

    public Optional<PurchaseOrderTransactions> getPurchaseOrderTransactionById(String id) {
        return purchaseOrderTransactionsRepository.findById(id);
    }

    public List<PurchaseOrderTransactions> getPurchaseOrderTransactionsByRequisitionId(String id) {
        return purchaseOrderTransactionsRepository.getPurchaseOrderTransactionsByPoRequisitionId(id);
    }



    public void deletePurchaseOrderTransaction(String id) {
        purchaseOrderTransactionsRepository.deleteById(id);
    }
}
