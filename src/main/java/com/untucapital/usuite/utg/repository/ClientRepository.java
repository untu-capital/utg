package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.ClientLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientLoan, String> {

    List<ClientLoan> findByUserId(String userId);

    List<ClientLoan> findClientLoansByBranchName(String branchName);

    List<ClientLoan> findClientLoansByLoanStatusAndBranchName(String loanStatus, String branchName);

    List<ClientLoan> findClientLoansByLoanStatusAndAssignToAndBranchName(String loanStatus, String assignTo, String branchName);

    ClientLoan findByLoanFileId(String loanAndFileId);
}
