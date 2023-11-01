package com.untucapital.usuite.utg.controller.cms;

import com.untucapital.usuite.utg.model.cms.PettyCashPayments;
import com.untucapital.usuite.utg.service.cms.PettyCashPaymentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 * @created 10/9/2023
 */

@RestController
@RequestMapping("/cms/petty-cash-payments")
@RequiredArgsConstructor
public class PettyCashPaymentsController {

    private final PettyCashPaymentsService pettyCashPaymentsService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<PettyCashPayments>> getAllPettyCashPayments(){
        return ResponseEntity.ok(pettyCashPaymentsService.getAllPettyCashPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PettyCashPayments> getPettyCashPaymentsById(@PathVariable String id){
        return ResponseEntity.ok(pettyCashPaymentsService.getPettyCashPaymentsById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PettyCashPayments> updatePettyCashPayments(
            @PathVariable("id") String id, @RequestBody PettyCashPayments pettyCashPayments) {
        PettyCashPayments updatedPettyCashPayments = pettyCashPaymentsService.updatePettyCashPayments(id, pettyCashPayments);
        if (updatedPettyCashPayments != null) {
            return new ResponseEntity<>(updatedPettyCashPayments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
