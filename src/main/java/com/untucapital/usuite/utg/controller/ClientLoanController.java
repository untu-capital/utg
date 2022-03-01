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

    // show all all loans with checked status
    @GetMapping("/loanStatus/{loanStatus}/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByLoanStatusAndBranchName(@PathVariable("loanStatus") String loanStatus, @PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndBranchName(loanStatus, branchName), HttpStatus.OK);
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
        clientLoanApplication.saveClientLoan(updatedLoanStatus);
        return new ResponseEntity<String>("Loan Status successfully updated.", HttpStatus.OK);
    }

    @PutMapping("/assignTo/{id}")
    public ResponseEntity<String> updateAssignTo(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updatedAssignTo = clientLoanApplication.getClientLoanApplicationById(id);
        updatedAssignTo.setAssignTo(clientLoan.getAssignTo());
        updatedAssignTo.setAssignedBy(clientLoan.getAssignedBy());
        updatedAssignTo.setAdditionalRemarks(clientLoan.getAdditionalRemarks());
        clientLoanApplication.saveClientLoan(updatedAssignTo);
        return new ResponseEntity<String>("Loan Status successfully updated.", HttpStatus.OK);
    }

    @GetMapping("/loanFileId/{loanFileId}")
    public ResponseEntity<ClientLoan> getClientLoanId(@PathVariable("loanFileId") String loanFileId) {
        return new ResponseEntity<ClientLoan>(clientRepository.findByLoanFileId(loanFileId), HttpStatus.OK);
    }

    // Show loans assigned to a specific loan officer
    @GetMapping("/{loanStatus}/{assignTo}/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByLoanStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("assignTo") String assignTo, @PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndAssignToAndBranchName(loanStatus, assignTo, branchName), HttpStatus.OK);
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

    //email to Los
    @PostMapping("bmAssignLoanOfficer/{recipientName}/{recipientEmail}")
    public ResponseEntity<ClientLoan> sendBmAssignLo(@PathVariable("recipientName") String recipientName, @PathVariable("recipientEmail") String recipientEmail) {
        String emailText = emailSender.sendBmAssignLoMsg(recipientName, "New loan Application", "");
        emailSender.send(recipientEmail, "Assigned Loan Application", emailText);
        return new ResponseEntity<ClientLoan>(clientLoanApplication.sendLoanSuccess(recipientName, recipientEmail), HttpStatus.OK);
    }

    //email to Clients
    @PostMapping("sendClientConfirmation/{recipientName}/{recipientEmail}")
    public ResponseEntity<ClientLoan> sendClientConfirmation(@PathVariable("recipientName") String recipientName, @PathVariable("recipientEmail") String recipientEmail) {
        String emailText = emailSender.sendClientConfirmationMsg(recipientName, "New loan Application", "");
        emailSender.send(recipientEmail, "Assigned Loan Application", emailText);
        return new ResponseEntity<ClientLoan>(clientLoanApplication.sendLoanSuccess(recipientName, recipientEmail), HttpStatus.OK);
    }

    //get applications by BranchName
    @GetMapping("/byBranch/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationByBranchName(@PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByBranchName(branchName), HttpStatus.OK);
    }

}




