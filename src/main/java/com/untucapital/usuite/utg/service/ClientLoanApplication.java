package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.model.DatabaseFile;

import java.util.List;

public interface ClientLoanApplication {

    ClientLoan saveClientLoan(ClientLoan clientLoan);

    List<ClientLoan> getAllClientLoanApplication();

    ClientLoan getClientLoanApplicationById(String id);

    List<ClientLoan> getClientLoanApplicationsByUserId(String userId);

    ClientLoan updateClientLoan(ClientLoan clientLoan, String id);

    void deleteClientLoan(String id);
}
