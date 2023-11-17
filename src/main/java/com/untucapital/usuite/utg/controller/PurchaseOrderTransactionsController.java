package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.PurchaseOrderTransactionsRequestDTO;
import com.untucapital.usuite.utg.dto.response.PurchaseOrderTransactionsResponseDTO;
import com.untucapital.usuite.utg.service.PurchaseOrderTransactionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/poTransactions")
public class PurchaseOrderTransactionsController {

    @Autowired
    PurchaseOrderTransactionsService purchaseOrderTransactionsService;

    private static final Logger log = LoggerFactory.getLogger(PurchaseOrderTransactionsController.class);

    @GetMapping
    public List<PurchaseOrderTransactionsResponseDTO> list() {
        return purchaseOrderTransactionsService.getAllPurchaseOrderTransactions();
    }

    @PostMapping
    public void saveRequisitions(@RequestBody PurchaseOrderTransactionsRequestDTO purchaseOrderTransactions) {
        log.info(String.valueOf(purchaseOrderTransactions));
        purchaseOrderTransactionsService.savePurchaseOrderTransaction(purchaseOrderTransactions);
    }

    @DeleteMapping("/deletePurchaseOrderTransaction/{id}")
    public void delete(@PathVariable String id) {
        purchaseOrderTransactionsService.deletePurchaseOrderTransaction(id);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<PurchaseOrderTransactionsResponseDTO> getPurchaseOrderTransactionById(@PathVariable("id") String id) {
        PurchaseOrderTransactionsResponseDTO purchaseOrderTransactions = purchaseOrderTransactionsService.getPurchaseOrderTransactionById(id);

        if (purchaseOrderTransactions != null) {
            return new ResponseEntity<>(purchaseOrderTransactions, HttpStatus.OK);
        } else {
            // Handle the case when the Requisitions object is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getByRequisitionId/{id}")
    public ResponseEntity<List<PurchaseOrderTransactionsResponseDTO>> getPurchaseOrderTransactionsByRequisitionId(@PathVariable("id") String id) {
        List<PurchaseOrderTransactionsResponseDTO> purchaseOrderTransactions = purchaseOrderTransactionsService.getPurchaseOrderTransactionsByRequisitionId(id);

        if (!purchaseOrderTransactions.isEmpty()) {
            return new ResponseEntity<>(purchaseOrderTransactions, HttpStatus.OK);
        } else {
            // Handle the case when no PurchaseOrderTransactions objects are found
            return new ResponseEntity<>( Collections.emptyList(),HttpStatus.OK);
        }
    }


}
