package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.model.DatabaseFile;
import com.untucapital.usuite.utg.model.User;

import java.util.List;

public interface ClientLoanApplication {

    ClientLoan saveClientLoan(ClientLoan clientLoan);

    ClientLoan sendLoanSuccess(String recipientName, String recipientEmail);

    List<ClientLoan> getAllClientLoanApplication();

    ClientLoan getClientLoanApplicationById(String id);

    List<ClientLoan> getClientLoanApplicationsByUserId(String userId);

    ClientLoan updateClientLoan(ClientLoan clientLoan, String id);

    void deleteClientLoan(String id);

    List<ClientLoan> getClientLoanApplicationStatusByloanStatus(String loanStatusID);

    List<ClientLoan> getClientLoanApplicationByBranchName(String branchName);
}