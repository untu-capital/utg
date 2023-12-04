package com.untucapital.usuite.utg.controller.cms;

import com.untucapital.usuite.utg.model.cms.CmsAuditTrail;
import com.untucapital.usuite.utg.service.cms.CmsAuditTrailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author tjchidanika
 * @created 28/9/2023
 */

@RestController
@RequestMapping("/cms/audit_trail")
@RequiredArgsConstructor
public class AuditTrailController {

    private final CmsAuditTrailService cmsAuditTrailService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<CmsAuditTrail>> getAllAuditTrails() {
        return ResponseEntity.ok(cmsAuditTrailService.getAllAuditTrails());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<CmsAuditTrail> getAuditTrailById(@PathVariable String id) {
//        return ResponseEntity.ok(cmsAuditTrailService.getAuditTrailById(id));
//    }

    @PostMapping("/save")
    public ResponseEntity<CmsAuditTrail>saveCmsAuditTrail(@RequestBody CmsAuditTrail cmsAuditTrail) {
        return ResponseEntity.ok(cmsAuditTrailService.saveCmsAuditTrail(cmsAuditTrail));
    }

    //Add Initiator
//    @PostMapping("/add")
//    public ResponseEntity<AuditTrail> addInitiator(@RequestBody AuditTrailInitiatorRequest request) {
//        return ResponseEntity.ok(auditTrailService.addInitiator(request));
//    }

    //Add First Approver
//    @PutMapping("/add-first-approver")
//    public ResponseEntity<AuditTrail> addFirstApprover(@RequestBody ApproverRequest request) {
//        return ResponseEntity.ok(auditTrailService.addFirstApprover(request));
//    }
//
//    //Add Second Approver
//    @PutMapping("/add-second-approver")
//    public ResponseEntity<AuditTrail> addSecondApprover(@RequestBody ApproverRequest request) {
//        return ResponseEntity.ok(auditTrailService.addSecondApprover(request));
//    }
//
//    //Delete Audit Trail
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteAuditTrail(@PathVariable Integer id) {
//
//        return ResponseEntity.ok(auditTrailService.deleteAuditTrail(id));
//    }
//
//    //Update Audit Trail
//    @PutMapping("/update")
//    public ResponseEntity<AuditTrail> updateAuditTrail(@RequestBody ChangeAmountRequest request) {
//        return ResponseEntity.ok(auditTrailService.updateAmount(request));
//    }

}
