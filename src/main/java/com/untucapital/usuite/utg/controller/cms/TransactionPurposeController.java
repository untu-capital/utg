package com.untucapital.usuite.utg.controller.cms;

import com.untucapital.usuite.utg.model.cms.TransactionPurpose;
import com.untucapital.usuite.utg.service.cms.TransactionPurposeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tjchidanika
 * @created 5/10/2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("cms/transaction-purpose")
public class TransactionPurposeController {
    private final TransactionPurposeService transactionVoucherService;

    //SAVE
    @PostMapping("/save")
    public ResponseEntity<TransactionPurpose> saveTransactionVoucher(@RequestBody TransactionPurpose request) {
        return ResponseEntity.ok(transactionVoucherService.save(request));
    }

    @PutMapping("/update")
    //UPDATE
    public ResponseEntity<TransactionPurpose> updateTransactionVoucher(@RequestBody TransactionPurpose request) {
        return ResponseEntity.ok(transactionVoucherService.update(request));
    }

    //GET ALL
    @GetMapping("/all")
    public ResponseEntity<List<TransactionPurpose>> getAllTransactionVoucher() {
        return ResponseEntity.ok(transactionVoucherService.getAll());
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<TransactionPurpose> getTransactionVoucherById(@PathVariable Integer id) {
        return ResponseEntity.ok(transactionVoucherService.getById(id));
    }

    //DELETE
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTransactionVoucher(@RequestBody TransactionPurpose request) {
        transactionVoucherService.delete(request);
        return ResponseEntity.ok("Transaction Purpose Deleted Successfully");
    }
}
