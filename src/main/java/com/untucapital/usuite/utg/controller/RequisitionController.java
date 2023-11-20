package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.RequisitionsRequestDTO;
import com.untucapital.usuite.utg.dto.response.RequisitionsResponseDTO;
import com.untucapital.usuite.utg.model.Requisitions;
import com.untucapital.usuite.utg.service.RequisitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requisitions")
public class RequisitionController {

    @Autowired
    RequisitionService requisitionService;

    private static final Logger log = LoggerFactory.getLogger(ClientLoanController.class);

    @GetMapping
    public List<RequisitionsResponseDTO> list() {
        return requisitionService.getAllRequistions();
    }

    @PostMapping
    public void saveRequisitions(@RequestBody RequisitionsRequestDTO requisitions) {
        log.info(String.valueOf(requisitions));
        requisitionService.saveRequisition(requisitions);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        requisitionService.deleteRequisition(id);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<RequisitionsResponseDTO> getRequisitionById(@PathVariable("id") String id) {
//
//        RequisitionsResponseDTO requisition = requisitionService.getRequisitionById(id);
//
//        log.info("Requisition:{}", requisition);
//
//        if (requisition!=null) {
//            return new ResponseEntity<>(requisition, HttpStatus.OK);
//        } else {
//            // Handle the case when the Requisitions object is not found
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity<RequisitionsResponseDTO> getRequisitionById(@PathVariable("id") String id) {

        RequisitionsResponseDTO requisition = requisitionService.getRequisitionById(id);

        log.info("Requisition:{}", requisition);

        if (requisition!=null) {
            return new ResponseEntity<>(requisition, HttpStatus.OK);
        } else {
            // Handle the case when the Requisitions object is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("getByPoNumber/{poNumber}")
    public ResponseEntity<RequisitionsResponseDTO> getRequisitionByPoNumber(@PathVariable("poNumber") String poNumber) {
        RequisitionsResponseDTO requisitions = requisitionService.getRequisitionByPoNumber(poNumber);

        if (requisitions !=null) {
            return new ResponseEntity<>(requisitions, HttpStatus.OK);
        } else {
            // Handle the case when no Requisitions objects are found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRequisition(@PathVariable("id") String id, @RequestBody RequisitionsRequestDTO updatedRequisition) {

        RequisitionsRequestDTO requisitionsRequest = new RequisitionsRequestDTO();

        // Check if the requisition with the given ID exists
        RequisitionsResponseDTO existingRequisitionOptional = requisitionService.getRequisitionById(id);

        if (existingRequisitionOptional==null) {
            return ResponseEntity.notFound().build(); // Return a 404 response if not found
        }

        RequisitionsResponseDTO existingRequisition = existingRequisitionOptional; // Extract the actual object



        // Update the existing requisition with the new data
        existingRequisition.setNotes(updatedRequisition.getNotes());


        // Update the existing approvers with the new approvers
        existingRequisition.setApprovers(updatedRequisition.getApprovers());

//        // Update the existing approvers with the new approvers
//        existingRequisition.setAttachments(updatedRequisition.getAttachments());

        // Append the new attachments to the existing ones
        List<String> existingAttachments = existingRequisition.getAttachments();
        List<String> updatedAttachments = updatedRequisition.getAttachments();
        if (existingAttachments != null && updatedAttachments != null) {
            existingAttachments.addAll(updatedAttachments);
        } else if (updatedAttachments != null) {
            existingAttachments = updatedAttachments;
        }
        existingRequisition.setAttachments(existingAttachments);

        BeanUtils.copyProperties(existingRequisition, requisitionsRequest);

        // Save the updated requisition
        requisitionService.saveRequisition(requisitionsRequest);

        return ResponseEntity.ok("Requisition updated successfully"); // Return a success response
    }


    @DeleteMapping("/attachments/{requisitionId}/{attachmentIndex}")
    public ResponseEntity<String> deleteAttachment(
            @PathVariable String requisitionId,
            @PathVariable int attachmentIndex) {

        // Find the Requisitions entity by ID
        RequisitionsResponseDTO requisitionOptional = requisitionService.getRequisitionById(requisitionId);

        if (requisitionOptional ==null) {
            return ResponseEntity.notFound().build();
        }



        // Check if the attachment index is valid
        List<String> attachments = requisitionOptional.getAttachments();
        if (attachmentIndex >= 0 && attachmentIndex < attachments.size()) {
            // Remove the attachment from the list
            attachments.remove(attachmentIndex);

            RequisitionsRequestDTO request = new RequisitionsRequestDTO();
            BeanUtils.copyProperties(requisitionOptional, request);
            // Update the Requisitions entity
            requisitionService.saveRequisition(request);

            return ResponseEntity.ok("Attachment deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid attachment index");
        }
    }

//



}
