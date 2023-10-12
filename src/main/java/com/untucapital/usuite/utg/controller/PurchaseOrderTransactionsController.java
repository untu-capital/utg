package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.PurchaseOrderTransactions;
import com.untucapital.usuite.utg.model.Requisitions;
import com.untucapital.usuite.utg.service.PurchaseOrderTransactionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/poTransactions")
public class PurchaseOrderTransactionsController {

    @Autowired
    PurchaseOrderTransactionsService purchaseOrderTransactionsService;

    private static final Logger log = LoggerFactory.getLogger(PurchaseOrderTransactionsController.class);

    @GetMapping
    public List<PurchaseOrderTransactions> list() {
        return purchaseOrderTransactionsService.getAllPurchaseOrderTransactions();
    }

    @PostMapping
    public void saveRequisitions(@RequestBody PurchaseOrderTransactions purchaseOrderTransactions) {
        log.info(String.valueOf(purchaseOrderTransactions));
        purchaseOrderTransactionsService.savePurchaseOrderTransaction(purchaseOrderTransactions);
    }

    @DeleteMapping("/deletePurchaseOrderTransaction/{id}")
    public void delete(@PathVariable String id) {
        purchaseOrderTransactionsService.deletePurchaseOrderTransaction(id);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<PurchaseOrderTransactions> getPurchaseOrderTransactionById(@PathVariable("id") String id) {
        Optional<PurchaseOrderTransactions> purchaseOrderTransactions = purchaseOrderTransactionsService.getPurchaseOrderTransactionById(id);

        if (purchaseOrderTransactions.isPresent()) {
            return new ResponseEntity<>(purchaseOrderTransactions.get(), HttpStatus.OK);
        } else {
            // Handle the case when the Requisitions object is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getByRequisitionId/{id}")
    public ResponseEntity<List<PurchaseOrderTransactions>> getPurchaseOrderTransactionsByRequisitionId(@PathVariable("id") String id) {
        List<PurchaseOrderTransactions> purchaseOrderTransactions = purchaseOrderTransactionsService.getPurchaseOrderTransactionsByRequisitionId(id);

        if (!purchaseOrderTransactions.isEmpty()) {
            return new ResponseEntity<>(purchaseOrderTransactions, HttpStatus.OK);
        } else {
            // Handle the case when no PurchaseOrderTransactions objects are found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
