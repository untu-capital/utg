package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.ClientLoan;

import java.util.List;

public interface ClientLoanApplication {

    ClientLoan saveClientLoan(ClientLoan clientLoan);

    List<ClientLoan> getAllClientLoanApplication();

    ClientLoan getClientLoanApplicationById(String id);

    ClientLoan updateClientLoan(ClientLoan clientLoan, String id);

    void deleteClientLoan(String id);
}
