package com.untucapital.usuite.utg.service.Impl;

import com.untucapital.usuite.utg.exception.ResourceNotFoundException;
import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.repository.ClientRepository;
import com.untucapital.usuite.utg.service.ClientLoanApplication;
import com.untucapital.usuite.utg.service.CreditCheckService;
import com.untucapital.usuite.utg.service.UserService;
import com.untucapital.usuite.utg.utils.EmailSender;
import com.untucapital.usuite.utg.utils.FormatterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientLoanApplicationImpl implements ClientLoanApplication {

    private static final Logger log = LoggerFactory.getLogger(ClientLoanApplication.class);

    private final ClientRepository clientRepository;
    private final CreditCheckService creditCheckService;
    private ClientLoanApplication clientLoanApplication;
    private final UserService userService;
    private final EmailSender emailSender;


    @Autowired
    public ClientLoanApplicationImpl(ClientRepository clientRepository, CreditCheckService creditCheckService, UserService userService, EmailSender emailSender) {
        this.clientRepository = clientRepository;
        this.creditCheckService = creditCheckService;
        this.userService = userService;
        this.emailSender = emailSender;
        this.clientLoanApplication = clientLoanApplication;
    }

    @Override
    public ClientLoan saveClientLoan(ClientLoan clientLoan) {
        log.info("Loan Application Request - {}", FormatterUtil.toJson(clientLoan));
        // 1. Check if user nationality is Zimbabwean
        // 2. Check if age is legal > 18
        // 3.
        ClientLoan creditCheckedLoan = creditCheckService.fetchFCBCreditStatus(clientLoan);

        log.info("Updated Loan Application - {}", FormatterUtil.toJson(clientLoan));
        return clientRepository.save(creditCheckedLoan);
    }

    @Override
    public ClientLoan sendLoanSuccess(String recipientName, String recipientEmail) {
        return null;
    }

    @Override
    public ClientLoan sendMeetingScheduleSuccess(String recipientName, String recipientEmail, String recipientSubject, String recipientMessage, String senderName) {
        return null;
    }

//    @Override
//    public ClientLoan sendLoanSuccess(ClientLoan clientLoan) {
//        String emailText = emailSender.sendLoanSuccessMsg(clientLoan.getFirstName()+" "+clientLoan.getLastName(), "New loan Application", "");
//        emailSender.send("randakelvin@gmail.com", "Untu Credit Application New loan Application", emailText);
//
//        log.info(String.valueOf(clientLoan));
//        return new <ClientLoan>(clientLoanApplication.sendLoanSuccess(clientLoan), HttpStatus.OK);
//    }

    @Override
    public List<ClientLoan> getAllClientLoanApplication() {
        return clientRepository.findAll();
    }

    @Override
    public ClientLoan getClientLoanApplicationById(String id) {
//        Optional <ClientLoan> clientLoan = clientRepository.findById(id);
//        if(clientLoan.isPresent()){
//            return clientLoan.get();
//        } else {
//            throw new ResourceNotFoundException("ClientLoan", "Id", id);
//        }
        return clientRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("ClientLoan", "Id", id));
    }

//    @Override
//    public ClientLoan getClientLoanApplicationByStatus(String loanStatus) {
//        return clientRepository.findById(loanStatus).orElseThrow(() ->
//                new ResourceNotFoundException("ClientLoan", "Id", loanStatus));
//    }

    @Override
    public List<ClientLoan> getClientLoanApplicationsByUserId(String userId) {
        userService.find(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));

        return clientRepository.findByUserId(userId);
    }

    @Override
    public ClientLoan updateClientLoan(ClientLoan clientLoan, String id) {
        //check weather loan with id exist or not
        ClientLoan existingClientLoan = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("ClientLoan", "Id", id));
        clientLoan.setId(existingClientLoan.getId());
        /*existingClientLoan.setFirstName(clientLoan.getFirstName());

        existingClientLoan.setStatus(clientLoan.getStatus());
*/
        //save existing client to DB
        clientRepository.save(clientLoan);
        return clientLoan;
    }

    @Override
    public void deleteClientLoan(String id) {
        //check whether a client loan exist in the database;
        clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ClientLoan", "Id", id));
        clientRepository.deleteById(id);

    }

    @Override
    public List<ClientLoan> getClientLoanApplicationStatusByloanStatus(String loanStatusID) {
        userService.find(loanStatusID).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", loanStatusID));

        return clientRepository.findByUserId(loanStatusID);
    }

    @Override
    public List<ClientLoan> getClientLoanApplicationByBranchName(String branchName) {
        userService.find(branchName).orElseThrow(() ->
                new ResourceNotFoundException("Loan Applications", "BranchName", branchName));

        return clientRepository.findClientLoansByBranchName(branchName);
    }

}
