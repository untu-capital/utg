package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.PurchaseOrderTransactionsRequestDTO;
import com.untucapital.usuite.utg.dto.response.PurchaseOrderTransactionsResponseDTO;
import com.untucapital.usuite.utg.exception.DuplicateEntryException;
import com.untucapital.usuite.utg.model.PurchaseOrderTransactions;
import com.untucapital.usuite.utg.repository.PurchaseOrderTransactionsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@javax.transaction.Transactional
public class PurchaseOrderTransactionsService {

    @Autowired
    private PurchaseOrderTransactionsRepository purchaseOrderTransactionsRepository;

    @Transactional(value = "transactionManager")
    public List<PurchaseOrderTransactionsResponseDTO> getAllPurchaseOrderTransactions() {

        List<PurchaseOrderTransactionsResponseDTO> response = new ArrayList<>();
        List<PurchaseOrderTransactions> purchaseOrderTransactionsList= purchaseOrderTransactionsRepository.findAll();

        for (PurchaseOrderTransactions purchaseOrderTransaction : purchaseOrderTransactionsList) {
            PurchaseOrderTransactionsResponseDTO responseDTO = new PurchaseOrderTransactionsResponseDTO();
            BeanUtils.copyProperties(purchaseOrderTransaction, responseDTO);
            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void savePurchaseOrderTransaction(PurchaseOrderTransactionsRequestDTO request) {

        PurchaseOrderTransactions purchaseOrderTransactions = new PurchaseOrderTransactions();
        if (!purchaseOrderTransactionsRepository.existsByPoItemAndPoSupplierAndPoCategoryAndPoQuantityAndPoAmountAndPoRequisitionId(
                request.getPoItem(), request.getPoSupplier(), request.getPoCategory(),
                request.getPoQuantity(), request.getPoAmount(), request.getPoRequisitionId())) {

            BeanUtils.copyProperties(request, purchaseOrderTransactions);
            purchaseOrderTransactionsRepository.save(purchaseOrderTransactions);
        }
        else {
            throw new DuplicateEntryException("Duplicate entry for PurchaseOrderTransactions");
        }
    }

    @Transactional(value = "transactionManager")
    public PurchaseOrderTransactionsResponseDTO getPurchaseOrderTransactionById(String id) {

        PurchaseOrderTransactionsResponseDTO response = new PurchaseOrderTransactionsResponseDTO();
        Optional<PurchaseOrderTransactions> purchaseOrderTransactions= purchaseOrderTransactionsRepository.findById(id);

        if(purchaseOrderTransactions.isPresent()){

            PurchaseOrderTransactions purchaseOrderTransactions1 = purchaseOrderTransactions.get();
            BeanUtils.copyProperties(purchaseOrderTransactions1,response);

            return response;
        }else {
            return null;
        }
    }

    @Transactional(value = "transactionManager")
    public List<PurchaseOrderTransactionsResponseDTO> getPurchaseOrderTransactionsByRequisitionId(String id) {

        List<PurchaseOrderTransactionsResponseDTO> response = new ArrayList<>();
        Optional<List<PurchaseOrderTransactions>> purchaseOrderTransactionsList= purchaseOrderTransactionsRepository.getPurchaseOrderTransactionsByPoRequisitionId(id);

        if(purchaseOrderTransactionsList.isPresent()) {
            List<PurchaseOrderTransactions> purchaseOrderTransactionsList1 = purchaseOrderTransactionsList.get();
            for (PurchaseOrderTransactions purchaseOrderTransaction : purchaseOrderTransactionsList1) {
                PurchaseOrderTransactionsResponseDTO responseDTO = new PurchaseOrderTransactionsResponseDTO();
                BeanUtils.copyProperties(purchaseOrderTransaction, responseDTO);
                response.add(responseDTO);
            }
        } else {
           return Collections.emptyList();
        }

            return response;
    }

    @Transactional(value = "transactionManager")
    public void deletePurchaseOrderTransaction(String id) {
        purchaseOrderTransactionsRepository.deleteById(id);
    }
}
