package com.untucapital.usuite.utg.service.Impl;

import com.untucapital.usuite.utg.client.RestClient;
import com.untucapital.usuite.utg.dto.client.Client;
import com.untucapital.usuite.utg.dto.musoni.savingsaccounts.PageItems;
import com.untucapital.usuite.utg.dto.musoni.savingsaccounts.SettlementAccountResponse;
import com.untucapital.usuite.utg.exception.ResourceNotFoundException;
import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.repository.ClientRepository;
import com.untucapital.usuite.utg.service.ClientLoanApplication;
import com.untucapital.usuite.utg.service.CreditCheckService;
import com.untucapital.usuite.utg.service.UserService;
import com.untucapital.usuite.utg.utils.EmailSender;
import com.untucapital.usuite.utg.utils.FormatterUtil;
import com.untucapital.usuite.utg.utils.MusoniUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientLoanApplicationImpl implements ClientLoanApplication {

    private static final Logger log = LoggerFactory.getLogger(ClientLoanApplication.class);

    private final ClientRepository clientRepository;
    private final CreditCheckService creditCheckService;
    private ClientLoanApplication clientLoanApplication;
    private final UserService userService;
    private final EmailSender emailSender;

    private final RestClient restClient;


    @Autowired
    public ClientLoanApplicationImpl(ClientRepository clientRepository, CreditCheckService creditCheckService, UserService userService, EmailSender emailSender, RestClient restClient) {
        this.clientRepository = clientRepository;
        this.creditCheckService = creditCheckService;
        this.userService = userService;
        this.emailSender = emailSender;
        this.restClient = restClient;
        this.clientLoanApplication = clientLoanApplication;
    }

    @Override
    public ClientLoan saveClientLoan(ClientLoan clientLoan) throws ParseException {
        log.info("Loan Application Request - {}", FormatterUtil.toJson(clientLoan));

        List<ClientLoan> loans = getClientLoanApplicationsByUserId(clientLoan.getUserId());

        if (loans.isEmpty()) {

            PageItems settlementAccount = restClient.getSavingsLoanAccountById(clientLoan.getUsername());

            Client musoniClient = restClient.getClientById(String.valueOf(settlementAccount.getClientId()));

            clientLoan.setFirstName(musoniClient.getFirstname());
            clientLoan.setLastName(musoniClient.getLastname());
            clientLoan.setGender(musoniClient.getGender().getName());
            clientLoan.setDateOfBirth(MusoniUtils.formatDate(musoniClient.getDateOfBirth()));
            clientLoan.setPhoneNumber(musoniClient.getMobileNo());
            clientLoan.setBranchName(musoniClient.getOfficeName());
        }




        ClientLoan creditCheckedLoan = creditCheckService.fetchFCBCreditStatus(clientLoan);

        log.info("Updated Loan Application - {}", FormatterUtil.toJson(clientLoan));
        return clientRepository.save(creditCheckedLoan);
    }

    @Override
    public ClientLoan updateClientLoan(ClientLoan clientLoan) {
        return null;
    }


    @Override
    public ClientLoan sendLoanSuccess(String recipientName, String recipientEmail) {
        return null;
    }

    @Override
    public ClientLoan sendMeetingScheduleSuccess(String recipientName, String recipientEmail, String recipientSubject, String recipientMessage, String senderName) {
        return null;
    }

    @Override
    public ClientLoan sendFinalMeetingSuccess(String recipientName, String recipientEmail, String recipientSubject, String recipientMessage, String senderName) {
        return null;
    }

    @Override
    public List<ClientLoan> getAllClientLoanApplication() {
        return clientRepository.findAll();
    }

    @Override
    public ClientLoan getClientLoanApplicationById(String id) {

        return clientRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("ClientLoan", "Id", id));
    }


    @Override
    public List<ClientLoan> getClientLoanApplicationsByUserId(String userId) {
        userService.find(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        List<ClientLoan> clientLoans = clientRepository.findByUserIdOrderByCreatedAtAsc(userId);
        log.info("Client loans: {}", clientLoans);
        return clientLoans;
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
    public List<ClientLoan> getClientLoanApplicationsByLoanStatus(String loanStatus) {
        userService.find(loanStatus).orElseThrow(() ->
                new ResourceNotFoundException("Loan", "loan status", loanStatus));
        return clientRepository.findClientLoansByLoanStatus(loanStatus);
    }

    @Override
    public List<ClientLoan> getClientLoanApplicationByBranchName(String branchName) {
        userService.find(branchName).orElseThrow(() ->
                new ResourceNotFoundException("Loan Applications", "BranchName", branchName));

        return clientRepository.findClientLoansByBranchName(branchName);
    }

    public List<ClientLoan> getAllClientLoansData() {

//        System.out.println(clientRepository.findAll());
//        List<ClientLoan> clientLoanList = clientRepository.findAll();
//        for (ClientLoan clientLoan : clientLoanList) {
//            System.out.println(clientLoan);
//        }


//        List jsonStr = getAllClientLoanApplication();

        // Create a Gson instance
//        Gson gson = new GsonBuilder().create();
//
//        JSONArray jsonArray = new JSONArray(jsonStr);
//        // Create a list to store the result
//        List<List<Object>> result = new ArrayList<>();
//        System.out.println("\n###################################");
//        System.out.println(jsonStr);
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject jsonObject = jsonArray.getInt(i);

//
//            // Extract the "createdAt" field
//            String createdAt = jsonObject.getString("createdAt");
//
//            // Extract the date and time components from "createdAt"
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
//            Date date = dateFormat.parse(createdAt);
//            long timestamp = date.getTime();
//
//            // Extract other fields as needed
//            // ...
//
//            // Create a list to store the extracted data for this object
//            List<Object> data = new ArrayList<>();
//
//            // Add the extracted fields to the list
//            data.add(timestamp);
//            // Add other fields as needed
//            // ...
//
//            // Add the list to the result
//            result.add(data);
//        }

        // Print the result
//        System.out.println(jsonStr);

        return clientRepository.findAll();
    }


    public List<ClientLoan> getRecentClientLoans() {
        Pageable pageable = PageRequest.of(0, 100); // Page number 0 (first page), page size 100
        return clientRepository.findAllByOrderByCreatedAtDesc(pageable).getContent();
    }

    @Override
    public List<ClientLoan> getActiveLoans(String userId) {

        List<ClientLoan> clientLoans= clientRepository.findClientLoansByUserId(userId);

        List<ClientLoan> clientLoanList = new ArrayList<>();
        for (ClientLoan clientLoan: clientLoans){
            if (clientLoan.getLoanStatus().equalsIgnoreCase("ACCEPTED") && !(clientLoan.getPipelineStatus().equalsIgnoreCase("cc_final_meeting"))){
                clientLoanList.add(clientLoan);
            }

            else if (clientLoan.getLoanStatus().equalsIgnoreCase("PENDING")){
                clientLoanList.add(clientLoan);
            }
        }

        return clientLoanList;
    }

}
