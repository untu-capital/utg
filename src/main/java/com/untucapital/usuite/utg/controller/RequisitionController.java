package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.DTO.response.RequisitionResponseDTO;
import com.untucapital.usuite.utg.model.po.Requisitions;
import com.untucapital.usuite.utg.service.RequisitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/requisitions")
public class RequisitionController {

    @Autowired
    RequisitionService requisitionService;

    private static final Logger log = LoggerFactory.getLogger(ClientLoanController.class);

    @GetMapping
    public List<RequisitionResponseDTO> list() {
        return requisitionService.getAllRequistions();
    }

    @PostMapping
    public void saveRequisitions(@RequestBody RequisitionResponseDTO requisitions) {
        log.info(String.valueOf(requisitions));
        requisitionService.saveRequisition(requisitions);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        requisitionService.deleteRequisition(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequisitionResponseDTO> getRequisitionById(@PathVariable("id") String id) {
        Optional<RequisitionResponseDTO> requisition = requisitionService.getRequisitionById(id);

        if (requisition.isPresent()) {
            return new ResponseEntity<>(requisition.get(), HttpStatus.OK);
        } else {
            // Handle the case when the Requisitions object is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("userId/{id}")
    public ResponseEntity<List<RequisitionResponseDTO>> getRequisitionByUserId(@PathVariable("id") String id) {
        List<RequisitionResponseDTO> requisition = requisitionService.getRequisitionByUserId(id);

        return new ResponseEntity<>(requisition, HttpStatus.OK);
    }

    @GetMapping("approverId/{id}")
    public ResponseEntity<List<RequisitionResponseDTO>> getRequisitionsByApproverId(@PathVariable("id") String id) {
        List<RequisitionResponseDTO> requisition = requisitionService.findRequisitionsByApproverId(id);

        return new ResponseEntity<>(requisition, HttpStatus.OK);
    }

    @GetMapping("getByApproverIdNull")
    public ResponseEntity<List<RequisitionResponseDTO>> getRequisitionsByApproverId() {
        List<RequisitionResponseDTO> requisition = requisitionService.findRequisitionsWithPoApprover();

        return new ResponseEntity<>(requisition, HttpStatus.OK);
    }



    @GetMapping("getByPoNumber/{poNumber}")
    public ResponseEntity<Requisitions> getRequisitionByPoNumber(@PathVariable("poNumber") String poNumber) {
        Optional<Requisitions> requisitions = requisitionService.getRequisitionByPoNumber(poNumber);

        if (requisitions.isPresent()) {
            return new ResponseEntity<>(requisitions.get(), HttpStatus.OK);
        } else {
            // Handle the case when no Requisitions objects are found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRequisition(@PathVariable("id") String id, @RequestBody Requisitions updatedRequisition) {

        // Check if the requisition with the given ID exists
        Optional<RequisitionResponseDTO> existingRequisitionOptional = requisitionService.getRequisitionById(id);

        if (!existingRequisitionOptional.isPresent()) {
            return ResponseEntity.notFound().build(); // Return a 404 response if not found
        }

        RequisitionResponseDTO existingRequisition = existingRequisitionOptional.get(); // Extract the actual object

        // Update the existing requisition with the new data
        existingRequisition.setNotes(updatedRequisition.getNotes());


        // Update the existing approvers with the new approvers
        existingRequisition.setApprovers(updatedRequisition.getApprovers());

        existingRequisition.setPoStatus(updatedRequisition.getPoStatus());

        // Append the new attachments to the existing ones
        List<String> existingAttachments = existingRequisition.getAttachments();
        List<String> updatedAttachments = updatedRequisition.getAttachments();
        if (existingAttachments != null && updatedAttachments != null) {
            existingAttachments.addAll(updatedAttachments);
        } else if (updatedAttachments != null) {
            existingAttachments = updatedAttachments;
        }
        existingRequisition.setAttachments(existingAttachments);

        // Save the updated requisition
        requisitionService.saveRequisition(existingRequisition);

        return ResponseEntity.ok("Requisition updated successfully"); // Return a success response
    }

    @PutMapping("/poApproveRequest/{id}")
    public ResponseEntity<String> poApproveRequest(@PathVariable("id") String id, @RequestBody Requisitions updatedRequisition) {

        // Check if the requisition with the given ID exists
        Optional<RequisitionResponseDTO> existingRequisitionOptional = requisitionService.getRequisitionById(id);

        if (!existingRequisitionOptional.isPresent()) {
            return ResponseEntity.notFound().build(); // Return a 404 response if not found
        }

        RequisitionResponseDTO existingRequisition = existingRequisitionOptional.get(); // Extract the actual object

        existingRequisition.setPoStatus(updatedRequisition.getPoStatus());

        existingRequisition.setPoApprover(updatedRequisition.getPoApprover());

        // Save the updated requisition
        requisitionService.saveRequisition(existingRequisition);

        return ResponseEntity.ok("Requisition submitted for approval"); // Return a success response
    }

    @PutMapping("cmsApproveRequest/{id}")
    public ResponseEntity<String> cmApproveRequest(@PathVariable("id") String id, @RequestBody Requisitions updatedRequisition) {

        // Check if the requisition with the given ID exists
        Optional<RequisitionResponseDTO> existingRequisitionOptional = requisitionService.getRequisitionById(id);

        if (!existingRequisitionOptional.isPresent()) {
            return ResponseEntity.notFound().build(); // Return a 404 response if not found
        }

        RequisitionResponseDTO existingRequisition = existingRequisitionOptional.get(); // Extract the actual object

        existingRequisition.setPoStatus(updatedRequisition.getPoStatus());

        existingRequisition.setCmsApprover(updatedRequisition.getCmsApprover());

        // Save the updated requisition
        requisitionService.saveRequisition(existingRequisition);

        return ResponseEntity.ok("Requisition submitted for approval"); // Return a success response
    }


    @DeleteMapping("/attachments/{requisitionId}/{attachmentIndex}")
    public ResponseEntity<String> deleteAttachment(
            @PathVariable String requisitionId,
            @PathVariable int attachmentIndex) {

        // Find the Requisitions entity by ID
        Optional<RequisitionResponseDTO> requisitionOptional = requisitionService.getRequisitionById(requisitionId);

        if (!requisitionOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        RequisitionResponseDTO requisition = requisitionOptional.get();

        // Check if the attachment index is valid
        List<String> attachments = requisition.getAttachments();
        if (attachmentIndex >= 0 && attachmentIndex < attachments.size()) {
            // Remove the attachment from the list
            attachments.remove(attachmentIndex);

            // Update the Requisitions entity
            requisitionService.saveRequisition(requisition);

            return ResponseEntity.ok("Attachment deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid attachment index");
        }
    }


}
