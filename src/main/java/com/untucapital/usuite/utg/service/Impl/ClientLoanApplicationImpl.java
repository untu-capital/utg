package com.untucapital.usuite.utg.service.Impl;

import com.untucapital.usuite.utg.exception.ResourceNotFoundException;
import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.repository.ClientRepository;
import com.untucapital.usuite.utg.service.ClientLoanApplication;
import com.untucapital.usuite.utg.service.CreditCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientLoanApplicationImpl implements ClientLoanApplication {

    private final ClientRepository clientRepository;
    private final CreditCheckService creditCheckService;

    @Autowired
    public ClientLoanApplicationImpl(ClientRepository clientRepository, CreditCheckService creditCheckService) {
        this.clientRepository = clientRepository;
        this.creditCheckService = creditCheckService;
    }

    @Override
    public ClientLoan saveClientLoan(ClientLoan clientLoan) {
        ClientLoan creditCheckedLoan = creditCheckService.fetchFCBCreditStatus(clientLoan);
        return clientRepository.save(creditCheckedLoan);
    }

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

    @Override
    public ClientLoan updateClientLoan(ClientLoan clientLoan, String id) {
        //check weather loan with id exist or not
        ClientLoan existingClientLoan = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("ClientLoan", "Id", id));
        existingClientLoan.setFirstname(clientLoan.getFirstname());
        existingClientLoan.setLastname(clientLoan.getLastname());
        existingClientLoan.setMiddlename(clientLoan.getMiddlename());
        existingClientLoan.setId_number(clientLoan.getId_number());
        existingClientLoan.setMarital(clientLoan.getMarital());
        existingClientLoan.setDob(clientLoan.getDob());
        existingClientLoan.setGender(clientLoan.getGender());
        existingClientLoan.setPhonenumber(clientLoan.getPhonenumber());
        existingClientLoan.setPob(clientLoan.getPob());
        existingClientLoan.setTob(clientLoan.getTob());
        existingClientLoan.setStreet_no(clientLoan.getStreet_no());
        existingClientLoan.setStreet_name(clientLoan.getStreet_name());
        existingClientLoan.setSurbub(clientLoan.getSurbub());
        existingClientLoan.setCity(clientLoan.getCity());
        existingClientLoan.setLoanAmount(clientLoan.getLoanAmount());
        existingClientLoan.setTenure(clientLoan.getTenure());
        existingClientLoan.setStatus(clientLoan.getStatus());

        //save existing client to DB
        clientRepository.save(existingClientLoan);
        return clientLoan;
    }

    @Override
    public void deleteClientLoan(String id) {
        //check whether a client loan exist in the database;
        clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ClientLoan", "Id", id));
        clientRepository.deleteById(id);

    }

}
