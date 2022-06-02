package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.repository.ClientRepository;
import com.untucapital.usuite.utg.service.ClientLoanApplication;
import com.untucapital.usuite.utg.utils.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "credit_application")
public class ClientLoanController {


    @Autowired
    ClientRepository clientRepository;

    private static final Logger log = LoggerFactory.getLogger(ClientLoanController.class);

    private ClientLoanApplication clientLoanApplication;
    private final EmailSender emailSender;

    public ClientLoanController(ClientLoanApplication clientLoanApplication, EmailSender emailSender) {
        this.clientLoanApplication = clientLoanApplication;
        this.emailSender = emailSender;
    }




    //build save loan REST API
    @PostMapping
    public ResponseEntity<ClientLoan> saveClientLoan(@RequestBody ClientLoan clientLoan) {
        log.info(String.valueOf(clientLoan));
        return new ResponseEntity<ClientLoan>(clientLoanApplication.saveClientLoan(clientLoan), HttpStatus.CREATED);
    }

    //build get all loan applications REST API
    @GetMapping
    public List<ClientLoan> getAllClientLoanApplication() {
        return clientLoanApplication.getAllClientLoanApplication();

    }

    //build get clientLoan by ID REST API
    @GetMapping("{id}")
    public ResponseEntity<ClientLoan> getClientLoanApplicationById(@PathVariable("id") String clientloanID) {
        return new ResponseEntity<ClientLoan>(clientLoanApplication.getClientLoanApplicationById(clientloanID), HttpStatus.OK);
    }

    @GetMapping("/loanStatus/{loanStatus}")
    public ResponseEntity<String> getClientLoanApplicationStatusByloanStatus(@PathVariable("loanStatus") String loanStatusID) {
        List <ClientLoan> userClientLoans = clientLoanApplication.getClientLoanApplicationStatusByloanStatus(loanStatusID);
        return ResponseEntity.ok(loanStatusID);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByUserId(@PathVariable("userId") String userId) {
        List<ClientLoan> userClientLoans = clientLoanApplication.getClientLoanApplicationsByUserId(userId);
        return ResponseEntity.ok(userClientLoans);
    }

    // show BM all loans with checked status
    @GetMapping("/loanStatus/{loanStatus}/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByLoanStatusAndBranchName(@PathVariable("loanStatus") String loanStatus, @PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndBranchName(loanStatus, branchName), HttpStatus.OK);
    }

    // show BM all loans with checked status
    @GetMapping("/loanStatus/{loanStatus}/{assignTo}/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByLoanStatusAndAssignToAndBranchName(@PathVariable("loanStatus") String loanStatus, @PathVariable("assignTo") String assignTo, @PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndAssignToAndBranchName(loanStatus,assignTo, branchName), HttpStatus.OK);
    }

    // show BM all loans assigned loans
    @GetMapping("/assigned/{loanStatus}/{assignedStatus}/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByLoanStatusAndAssignedStatusAndBranchName(@PathVariable("loanStatus") String loanStatus, @PathVariable("assignedStatus") String assignedStatus, @PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndAssignedStatusAndBranchName(loanStatus,assignedStatus, branchName), HttpStatus.OK);
    }






    // show BM all loans signed by BOCO
    @GetMapping("/bocoSignature/{bocoSignature}/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByBocoSignatureAndBranchName(@PathVariable("bocoSignature") String bocoSignature, @PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByBocoSignatureAndBranchName(bocoSignature, branchName), HttpStatus.OK);
    }

    // show CA all loans signed by BM
    @GetMapping("/bmSignature/{bmSignature}/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByBMSignatureAndBranchName(@PathVariable("bmSignature") String bmSignature, @PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByBmSignatureAndBranchName(bmSignature, branchName), HttpStatus.OK);
    }

    // show CM all loans signed by CA
    @GetMapping("/caSignature/{caSignature}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByCASignatureAndBranchName(@PathVariable("caSignature") String caSignature) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByCaSignature(caSignature), HttpStatus.OK);
    }
    // show CA all loans signed by BM
    @GetMapping("/cmSignature/{cmSignature}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByCMSignatureAndBranchName(@PathVariable("cmSignature") String cmSignature) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByCmSignature(cmSignature), HttpStatus.OK);
    }
    // show signed tickets for Fin
    @GetMapping("/finSignature/{finSignature}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByFinSignature(@PathVariable("finSignature") String finSignature) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByFinSignature(finSignature), HttpStatus.OK);
    }

    // show BM all loans that have been assessed
//    @GetMapping("/loanStatusAssessed/{loanStatus}/{branchName}/{assessmentStatus}")
//    public ResponseEntity<List<ClientLoan>> getAssessedClientLoanApplicationsByLoanStatusAndBranchName(@PathVariable("loanStatus") String loanStatus, @PathVariable("branchName") String branchName, @PathVariable("assessmentStatus") String assessmentStatus) {
//        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndBranchNameAndProcessLoanStatus(loanStatus, branchName, assessmentStatus), HttpStatus.OK);
//    }

    // show BM all loans that have been assessed
    @GetMapping("/loanStatusAssessed/{loanStatus}/{branchName}/{pipelineStatus}")
    public ResponseEntity<List<ClientLoan>> getAssessedClientLoanApplicationsByLoanStatusAndBranchName(@PathVariable("loanStatus") String loanStatus, @PathVariable("branchName") String branchName, @PathVariable("pipelineStatus") String pipelineStatus) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndBranchNameAndPipelineStatus(loanStatus, branchName, pipelineStatus), HttpStatus.OK);
    }

    // show all loans awaiting for meeting final decision to Credit commit
    @GetMapping("/loanAwaitingDecision/{loanStatus}/{pipelineStatus}/{creditCommit}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByPipelineStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("pipelineStatus") String pipelineStatus, @PathVariable("creditCommit") String creditCommit) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoanByLoanStatusAndPipelineStatusAndCreditCommit(loanStatus, pipelineStatus, creditCommit), HttpStatus.OK);
    }

    // show all loans awaiting for meeting final decision to branch managers
    @GetMapping("/finalizedLoan/{loanStatus}/{branchName}/{pipelineStatus}/{creditCommit}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByPipelineStatustoBms(@PathVariable("loanStatus") String loanStatus, @PathVariable("branchName") String branchName, @PathVariable("pipelineStatus") String pipelineStatus, @PathVariable("creditCommit") String creditCommit) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoanByLoanStatusAndBranchNameAndPipelineStatusAndCreditCommit(loanStatus, branchName, pipelineStatus, creditCommit), HttpStatus.OK);
    }

    // show BOCO all tickets not signed yet.
    @GetMapping("/ticketNotSigned/{loanStatus}/{processLoanStatus}/{bocoSignature}/{pipelineStatus}/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsBySignatureStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("processLoanStatus") String processLoanStatus, @PathVariable("bocoSignature") String bocoSignature, @PathVariable("pipelineStatus") String pipelineStatus, @PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndProcessLoanStatusAndBocoSignatureAndPipelineStatusAndBranchName(loanStatus, processLoanStatus, bocoSignature, pipelineStatus, branchName), HttpStatus.OK);
    }
    // show BM all tickets not signed yet.
    @GetMapping("/bmTicketNotSigned/{loanStatus}/{processLoanStatus}/{bmSignature}/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByforBmSignatureStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("processLoanStatus") String processLoanStatus, @PathVariable("bmSignature") String bmSignature, @PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndProcessLoanStatusAndBmSignatureAndBranchName(loanStatus, processLoanStatus, bmSignature, branchName), HttpStatus.OK);
    }

    // show CA all tickets not signed yet.
    @GetMapping("/caTicketNotSigned/{loanStatus}/{processLoanStatus}/{caSignature}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByforCaSignatureStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("processLoanStatus") String processLoanStatus, @PathVariable("caSignature") String caSignature) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndProcessLoanStatusAndCaSignature(loanStatus, processLoanStatus, caSignature), HttpStatus.OK);
    }

    // show CM all tickets not signed yet.
    @GetMapping("/cmTicketNotSigned/{loanStatus}/{processLoanStatus}/{cmSignature}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByforCmSignatureStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("processLoanStatus") String processLoanStatus, @PathVariable("cmSignature") String cmSignature) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndProcessLoanStatusAndCmSignature(loanStatus, processLoanStatus, cmSignature), HttpStatus.OK);
    }
    // show Fin all tickets not signed yet.
    @GetMapping("/finTicketNotSigned/{loanStatus}/{processLoanStatus}/{finSignature}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByforFinSignatureStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("processLoanStatus") String processLoanStatus, @PathVariable("finSignature") String finSignature) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndProcessLoanStatusAndFinSignature(loanStatus, processLoanStatus, finSignature), HttpStatus.OK);
    }

    // Show loans assigned to a specific loan officer (not yet assessed)
    @GetMapping("/assessmentNotCompleted/{loanStatus}/{assignTo}/{branchName}/{assessmentStatus}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByLoanStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("assignTo") String assignTo, @PathVariable("branchName") String branchName, @PathVariable("assessmentStatus") String assessmentStatus) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndAssignToAndBranchNameAndProcessLoanStatus(loanStatus, assignTo, branchName, assessmentStatus), HttpStatus.OK);
    }

    // Show loans assigned to a specific loan officer that are assessed
    @GetMapping("/assessmentCompleted/{loanStatus}/{assignTo}/{branchName}/{assessmentStatus}")
    public ResponseEntity<List<ClientLoan>> getProcessedClientLoanApplicationsByLoanStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("assignTo") String assignTo, @PathVariable("branchName") String branchName, @PathVariable("assessmentStatus") String assessmentStatus) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndAssignToAndBranchNameAndProcessLoanStatus(loanStatus, assignTo, branchName, assessmentStatus), HttpStatus.OK);
    }

    //build delete client loan application REST api
    @DeleteMapping("deleteloan/{id}")
    public ResponseEntity<String> deleteClientLoan(@PathVariable("id") String id) {
        //delete client loan from DB
        clientLoanApplication.deleteClientLoan(id);
        return new ResponseEntity<String>("Application successfully deleted.", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateLoanStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updatedLoanStatus = clientLoanApplication.getClientLoanApplicationById(id);
        updatedLoanStatus.setLoanStatus(clientLoan.getLoanStatus());
        updatedLoanStatus.setComment(clientLoan.getComment());
        updatedLoanStatus.setLoanStatusAssigner(clientLoan.getLoanStatusAssigner());
        updatedLoanStatus.setBocoDate(clientLoan.getBocoDate());
        updatedLoanStatus.setPipelineStatus(clientLoan.getPipelineStatus());

        clientLoanApplication.saveClientLoan(updatedLoanStatus);
        return new ResponseEntity<String>("Loan Status successfully updated.", HttpStatus.OK);
    }

    // assign each loan to a loan officer
    @PutMapping("/assignTo/{id}")
    public ResponseEntity<String> updateAssignTo(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updatedAssignTo = clientLoanApplication.getClientLoanApplicationById(id);
        updatedAssignTo.setAssignTo(clientLoan.getAssignTo());
        updatedAssignTo.setAssignedBy(clientLoan.getAssignedBy());
        updatedAssignTo.setProcessLoanStatus(clientLoan.getProcessLoanStatus());
        updatedAssignTo.setAdditionalRemarks(clientLoan.getAdditionalRemarks());
        updatedAssignTo.setAssignedStatus("Assigned");
        updatedAssignTo.setBmDateAssignLo(clientLoan.getBmDateAssignLo());
        updatedAssignTo.setPipelineStatus(clientLoan.getPipelineStatus());
        clientLoanApplication.saveClientLoan(updatedAssignTo);
        return new ResponseEntity<String>("Loan Status successfully updated.", HttpStatus.OK);
    }

    // set/Update status that LO has completed processing application
    @PutMapping("/updateLoanAssessmentStatus/{id}")
    public ResponseEntity<String> assessmentCompleteStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updateProcessLoanStatus = clientLoanApplication.getClientLoanApplicationById(id);
        updateProcessLoanStatus.setProcessLoanStatus(clientLoan.getProcessLoanStatus());
        updateProcessLoanStatus.setProcessedBy(clientLoan.getProcessedBy());
        updateProcessLoanStatus.setLoDate(clientLoan.getLoDate());
        updateProcessLoanStatus.setPipelineStatus(clientLoan.getPipelineStatus());
        clientLoanApplication.saveClientLoan(updateProcessLoanStatus);
        return new ResponseEntity<String>("Loan Assessment Status successfully updated.", HttpStatus.OK);
    }

    // update predisbursement ticket for BOCO Signature
    @PutMapping("/updateTicketSignature/{id}")
    public ResponseEntity<String> ticketStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updateSignatureStatus = clientLoanApplication.getClientLoanApplicationById(id);
        updateSignatureStatus.setBocoSignature(clientLoan.getBocoSignature());
//        updateSignatureStatus.setBcUserId(clientLoan.getBcUserId());
        updateSignatureStatus.setBocoName(clientLoan.getBocoName());
        updateSignatureStatus.setBocoSignatureImage(clientLoan.getBocoSignatureImage());
        updateSignatureStatus.setLessFees(clientLoan.getLessFees());
        updateSignatureStatus.setApplicationFee(clientLoan.getApplicationFee());
        clientLoanApplication.saveClientLoan(updateSignatureStatus);
        return new ResponseEntity<String>("Ticket successfully signed.", HttpStatus.OK);
    }

    // update predisbursement ticket for bm Signature
    @PutMapping("/updateBmSignature/{id}")
    public ResponseEntity<String> bmTicketStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updateBmSignatureStatus = clientLoanApplication.getClientLoanApplicationById(id);
//        updateBmSignatureStatus.setBmSignatureImage(clientLoan.getBmSignatureImage());
//        updateBmSignatureStatus.setBmUserId(clientLoan.getBmUserId());
        updateBmSignatureStatus.setBmSignature(clientLoan.getBmSignature());
        updateBmSignatureStatus.setBmName(clientLoan.getBmName());
        clientLoanApplication.saveClientLoan(updateBmSignatureStatus);
        return new ResponseEntity<String>("Ticket successfully signed.", HttpStatus.OK);
    }
    // update predisbursement ticket for CM Signature
    @PutMapping("/updateCmSignature/{id}")
    public ResponseEntity<String> cmTicketStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updateCmSignatureStatus = clientLoanApplication.getClientLoanApplicationById(id);
//        updateCmSignatureStatus.setCmSignatureImage(clientLoan.getCmSignatureImage());
//        updateCmSignatureStatus.setCmUserId(clientLoan.getCmUserId());
        updateCmSignatureStatus.setCmSignature(clientLoan.getCmSignature());
        updateCmSignatureStatus.setCmName(clientLoan.getCmName());
        clientLoanApplication.saveClientLoan(updateCmSignatureStatus);
        return new ResponseEntity<String>("Ticket successfully signed.", HttpStatus.OK);
    }

    // update predisbursement ticket for CA Signature
    @PutMapping("/updateCaSignature/{id}")
    public ResponseEntity<String> caTicketStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updateCaSignatureStatus = clientLoanApplication.getClientLoanApplicationById(id);
//        updateCaSignatureStatus.setCaSignatureImage(clientLoan.getCaSignatureImage());
//        updateCaSignatureStatus.setCaUserId(clientLoan.getCaUserId());
        updateCaSignatureStatus.setCaSignature(clientLoan.getCaSignature());
        updateCaSignatureStatus.setCaName(clientLoan.getCaName());
        clientLoanApplication.saveClientLoan(updateCaSignatureStatus);
        return new ResponseEntity<String>("Ticket successfully signed.", HttpStatus.OK);
    }
    // update predisbursement ticket for Fin Signature
    @PutMapping("/updateFinSignature/{id}")
    public ResponseEntity<String> finTicketStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updateFinSignatureStatus = clientLoanApplication.getClientLoanApplicationById(id);
//        updateFinSignatureStatus.setFinSignatureImage(clientLoan.getFinSignatureImage());
//        updateFinSignatureStatus.setFinUserId(clientLoan.getFinUserId());
        updateFinSignatureStatus.setFinSignature(clientLoan.getFinSignature());
        updateFinSignatureStatus.setFinName(clientLoan.getFinName());
        clientLoanApplication.saveClientLoan(updateFinSignatureStatus);
        return new ResponseEntity<String>("Ticket successfully signed.", HttpStatus.OK);
    }

    @GetMapping("/loanFileId/{loanFileId}")
    public ResponseEntity<ClientLoan> getClientLoanId(@PathVariable("loanFileId") String loanFileId) {
        return new ResponseEntity<ClientLoan>(clientRepository.findByLoanFileId(loanFileId), HttpStatus.OK);
    }

    // email to Bocos
    @PostMapping("newClientloanEmail/{recipientName}/{recipientEmail}")
    public ResponseEntity<ClientLoan> sendLoanSuccess(@PathVariable("recipientName") String recipientName, @PathVariable("recipientEmail") String recipientEmail) {
        String emailText = emailSender.sendLoanSuccessMsg(recipientName, "New loan Application", "");
        emailSender.send(recipientEmail, "New loan Application", emailText);
//        log.info(String.valueOf(clientLoan));
        return new ResponseEntity<ClientLoan>(clientLoanApplication.sendLoanSuccess(recipientName, recipientEmail), HttpStatus.OK);
    }

    //email to Bms
    @PostMapping("bocoCheckLoanStatus/{recipientName}/{recipientEmail}")
    public ResponseEntity<ClientLoan> sendBocoCheck(@PathVariable("recipientName") String recipientName, @PathVariable("recipientEmail") String recipientEmail) {
        String emailText = emailSender.sendBocoCheckMsg(recipientName, "New loan Application", "");
        emailSender.send(recipientEmail, "Checked Loan Application", emailText);
        return new ResponseEntity<ClientLoan>(clientLoanApplication.sendLoanSuccess(recipientName, recipientEmail), HttpStatus.OK);
    }

    //email to LOs
    @PostMapping("bmAssignLoanOfficer/{recipientName}/{recipientEmail}")
    public ResponseEntity<ClientLoan> sendBmAssignLo(@PathVariable("recipientName") String recipientName, @PathVariable("recipientEmail") String recipientEmail) {
        String emailText = emailSender.sendBmAssignLoMsg(recipientName, "New loan Application", "");
        emailSender.send(recipientEmail, "Assigned Loan Application", emailText);
        return new ResponseEntity<ClientLoan>(clientLoanApplication.sendLoanSuccess(recipientName, recipientEmail), HttpStatus.OK);
    }

    //email to schedule meeting with credit commit
//    @PostMapping("bmScheduleMeeting")
//    public ResponseEntity<ClientLoan> sendScheduleMeeting(@RequestParam("recipientName") String recipientName, @RequestParam("recipientEmail") String recipientEmail, @RequestParam("recipientSubject") String recipientSubject, @RequestParam("recipientMessage") String recipientMessage, @RequestParam("senderName") String senderName) {
//        String emailText = emailSender.sendScheduleMeetingMsg(recipientName, recipientSubject, recipientMessage, senderName);
//        emailSender.send(recipientEmail, recipientSubject, emailText);
//        return new ResponseEntity<ClientLoan>(clientLoanApplication.sendMeetingScheduleSuccess(recipientName, recipientEmail, recipientSubject, recipientMessage, senderName), HttpStatus.OK);
//    }

    //email to schedule meeting with credit commit
    @PostMapping("bmScheduleMeeting/{recipientName}/{recipientEmail}/{recipientSubject}/{recipientMessage}/{senderName}")
    public ResponseEntity<ClientLoan> sendScheduleMeeting(@PathVariable("recipientName") String recipientName, @PathVariable("recipientEmail") String recipientEmail, @PathVariable("recipientSubject") String recipientSubject, @PathVariable("recipientMessage") String recipientMessage, @PathVariable("senderName") String senderName) {
        String emailText = emailSender.sendScheduleMeetingMsg(recipientName, recipientSubject, recipientMessage, senderName);
        emailSender.send(recipientEmail, recipientSubject, emailText);
        return new ResponseEntity<ClientLoan>(clientLoanApplication.sendMeetingScheduleSuccess(recipientName, recipientEmail, recipientSubject, recipientMessage, senderName), HttpStatus.OK);
    }

    //email to Clients
    @PostMapping("sendClientConfirmation/{recipientName}/{recipientEmail}")
    public ResponseEntity<ClientLoan> sendClientConfirmation(@PathVariable("recipientName") String recipientName, @PathVariable("recipientEmail") String recipientEmail) {
        String emailText = emailSender.sendClientConfirmationMsg(recipientName, "New loan Application", "");
        emailSender.send(recipientEmail, "Assigned Loan Application", emailText);
        return new ResponseEntity<ClientLoan>(clientLoanApplication.sendLoanSuccess(recipientName, recipientEmail), HttpStatus.OK);
    }

    //get applications by BranchName and loan display loan with status pending for BOCO
    @GetMapping("/byBranch/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationByBranchName(@PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByBranchNameOrderByCreatedAtDesc(branchName), HttpStatus.OK);
    }

    //display unchecked loans  with status pending for BOCO

    @GetMapping("/unchecked/{loanStatus}/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationByBranchNameAndLoanStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndBranchName(loanStatus, branchName), HttpStatus.OK);
    }

    //Update meeting columns
    @PutMapping("/updateMeeting/{id}")
    public ResponseEntity<String> updateLoanMeeting(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updatedLoanMeeting = clientLoanApplication.getClientLoanApplicationById(id);
        updatedLoanMeeting.setMeetingLoanAmount(clientLoan.getMeetingLoanAmount());
        updatedLoanMeeting.setMeetingTenure(clientLoan.getMeetingTenure());
        updatedLoanMeeting.setMeetingInterestRate(clientLoan.getMeetingInterestRate());
        updatedLoanMeeting.setMeetingOnWhichBasis(clientLoan.getMeetingOnWhichBasis());
        updatedLoanMeeting.setMeetingCashHandlingFee(clientLoan.getMeetingCashHandlingFee());
        updatedLoanMeeting.setMeetingRepaymentAmount(clientLoan.getMeetingRepaymentAmount());
        updatedLoanMeeting.setMeetingProduct(clientLoan.getMeetingProduct());
        updatedLoanMeeting.setMeetingRN(clientLoan.getMeetingRN());
        updatedLoanMeeting.setMeetingUpfrontFee(clientLoan.getMeetingUpfrontFee());
        updatedLoanMeeting.setMeetingFinalizedBy(clientLoan.getMeetingFinalizedBy());
        updatedLoanMeeting.setCcDate(clientLoan.getCcDate());
        updatedLoanMeeting.setPipelineStatus(clientLoan.getPipelineStatus());
        clientLoanApplication.saveClientLoan(updatedLoanMeeting);
        return new ResponseEntity<String>("Loan Meeting successfully updated.", HttpStatus.OK);
    }

    //Update meeting columns
    @PutMapping("/updateBmDateMeeting/{id}")
    public ResponseEntity<String> updateBmDateMeeting(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updatedLoanStatus = clientLoanApplication.getClientLoanApplicationById(id);
        updatedLoanStatus.setBmDateMeeting(clientLoan.getBmDateMeeting());
        updatedLoanStatus.setPipelineStatus(clientLoan.getPipelineStatus());
        updatedLoanStatus.setBmSetMeeting(clientLoan.getBmSetMeeting());
        updatedLoanStatus.setCreditCommit(clientLoan.getCreditCommit());
        clientLoanApplication.saveClientLoan(updatedLoanStatus);
        return new ResponseEntity<String>("Meeting status successfully updated.", HttpStatus.OK);
    }

    //Update meeting columns
    @PutMapping("/updateCcFinalMeeting/{id}")
    public ResponseEntity<String> updateCcFinalMeeting(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updatedLoanStatus = clientLoanApplication.getClientLoanApplicationById(id);
        updatedLoanStatus.setCcDate(clientLoan.getCcDate());
        updatedLoanStatus.setPipelineStatus(clientLoan.getPipelineStatus());
        clientLoanApplication.saveClientLoan(updatedLoanStatus);
        return new ResponseEntity<String>("Meeting status successfully updated.", HttpStatus.OK);
    }

    //email to schedule meeting with credit commit
    @PostMapping("sendFinalMeetingMsg/{recipientName}/{recipientEmail}/{recipientSubject}/{recipientMessage}/{senderName}")
    public ResponseEntity<ClientLoan> sendFinalMeeting(@PathVariable("recipientName") String recipientName, @PathVariable("recipientEmail") String recipientEmail, @PathVariable("recipientSubject") String recipientSubject, @PathVariable("recipientMessage") String recipientMessage, @PathVariable("senderName") String senderName) {
        String emailText = emailSender.sendFinalMeetingMsg(recipientName, recipientSubject, recipientMessage, senderName);
        emailSender.send(recipientEmail, recipientSubject, emailText);
        return new ResponseEntity<ClientLoan>(clientLoanApplication.sendFinalMeetingSuccess(recipientName, recipientEmail, recipientSubject, recipientMessage, senderName), HttpStatus.OK);
    }

}




