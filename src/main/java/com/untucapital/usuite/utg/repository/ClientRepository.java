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

    List<ClientLoan> findClientLoansByBocoSignatureAndBranchName(String bocoSignature, String branchName);

    List<ClientLoan> findClientLoansByBmSignatureAndBranchName(String bmSignature, String branchName);

    List<ClientLoan> findClientLoansByCaSignatureAndBranchName(String caSignature, String branchName);

    List<ClientLoan> findClientLoansByCmSignatureAndBranchName(String cmSignature, String branchName);

    List<ClientLoan> findClientLoansByLoanStatusAndBranchNameAndProcessLoanStatus(String loanStatus, String branchName, String assessmentStatus);

//    List<ClientLoan> findClientLoansByLoanStatusAndBranchNameAndProcessLoanStatusAndBocoSignature(String status, String loanStatus, String branchName, String assessmentStatus, String bocoSignature);

//    List<ClientLoan> findClientLoansByLoanStatusAndAssignToAndBranchNameAndProcessLoanStatusNotContains(String loanStatus, String assignTo, String branchName, String assessmentStatus);

    List<ClientLoan> findClientLoansByLoanStatusAndAssignToAndBranchNameAndProcessLoanStatus(String loanStatus, String assignTo, String branchName, String assessmentStatus);

    ClientLoan findByLoanFileId(String loanAndFileId);
}
