package com.untucapital.usuite.utg.controller.cms;

import com.untucapital.usuite.utg.DTO.cms.res.TransactionPurposeResponseDTO;
import com.untucapital.usuite.utg.dto.cms.req.TransactionPurposeRequestDTO;
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
    public ResponseEntity<TransactionPurposeResponseDTO> saveTransactionVoucher(@RequestBody TransactionPurposeRequestDTO request) {
        return ResponseEntity.ok(transactionVoucherService.save(request));
    }

    @PutMapping("/update")
    //UPDATE
    public ResponseEntity<TransactionPurposeResponseDTO> updateTransactionVoucher(@RequestBody TransactionPurposeRequestDTO request) {
        return ResponseEntity.ok(transactionVoucherService.update(request));
    }

    //GET ALL
    @GetMapping("/all")
    public ResponseEntity<List<TransactionPurposeResponseDTO>> getAllTransactionVoucher() {
        return ResponseEntity.ok(transactionVoucherService.getAll());
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<TransactionPurposeResponseDTO> getTransactionVoucherById(@PathVariable Integer id) {
        return ResponseEntity.ok(transactionVoucherService.getById(id));
    }

    //DELETE
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTransactionVoucher(@RequestBody TransactionPurposeRequestDTO request) {
        transactionVoucherService.delete(request);
        return ResponseEntity.ok("Transaction Purpose Deleted Successfully");
    }
}
