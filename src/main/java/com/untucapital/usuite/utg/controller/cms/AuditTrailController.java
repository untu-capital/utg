package com.untucapital.usuite.utg.controller.cms;

import com.untucapital.usuite.utg.dto.ApproverRequest;
import com.untucapital.usuite.utg.dto.AuditTrailInitiatorRequest;
import com.untucapital.usuite.utg.dto.ChangeAmountRequest;
import com.untucapital.usuite.utg.model.cms.AuditTrail;
import com.untucapital.usuite.utg.service.cms.AuditTrailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author tjchidanika
 * @created 28/9/2023
 */

@RestController
@RequestMapping("/cms/audit-trails")
@RequiredArgsConstructor
public class AuditTrailController {

    private final AuditTrailService auditTrailService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<AuditTrail>> getAllAuditTrails() {
        return ResponseEntity.ok(auditTrailService.getAllAuditTrails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditTrail> getAuditTrailById(@PathVariable Integer id) {
        return ResponseEntity.ok(auditTrailService.getAuditTrailById(id));
    }

    //Add Initiator
    @PostMapping("/add-initiator")
    public ResponseEntity<AuditTrail> addInitiator(@RequestBody AuditTrailInitiatorRequest request) {
        return ResponseEntity.ok(auditTrailService.addInitiator(request));
    }

    //Add First Approver
    @PutMapping("/add-first-approver")
    public ResponseEntity<AuditTrail> addFirstApprover(@RequestBody ApproverRequest request) {
        return ResponseEntity.ok(auditTrailService.addFirstApprover(request));
    }

    //Add Second Approver
    @PutMapping("/add-second-approver")
    public ResponseEntity<AuditTrail> addSecondApprover(@RequestBody ApproverRequest request) {
        return ResponseEntity.ok(auditTrailService.addSecondApprover(request));
    }

    //Delete Audit Trail
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAuditTrail(@PathVariable Integer id) {

        return ResponseEntity.ok(auditTrailService.deleteAuditTrail(id));
    }

    //Update Audit Trail
    @PutMapping("/update")
    public ResponseEntity<AuditTrail> updateAuditTrail(@RequestBody ChangeAmountRequest request) {
        return ResponseEntity.ok(auditTrailService.updateAmount(request));
    }

}
