package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.ClientLoan;

import java.util.List;

public interface ClientLoanApplication {
    ClientLoan saveClientLoan(ClientLoan clientLoan);

    List<ClientLoan> getAllClientLoanApplication();

    ClientLoan getClientLoanApplicationById(long id);

    ClientLoan updateClientLoan(ClientLoan clientLoan, long id);

    void deleteClientLoan(long id);
}
