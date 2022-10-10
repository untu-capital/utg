package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.ClientLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientLoan, String> {

    List<ClientLoan> findByUserId(String userId);
    List<ClientLoan> findClientLoansByBranchNameOrderByCreatedAtDesc(String branchName);

//    ClientLoan findClientLoanBy (String loanAndFileId);

    List<ClientLoan> findClientLoansByBranchName(String branchName);

    List<ClientLoan> findClientLoansByLoanStatus(String loanStatus);

    List<ClientLoan> findClientLoansByLoanStatusAndAssignToAndBranchName(String loanStatus, String assignTo, String branchName);

    List<ClientLoan> findClientLoansByLoanStatusAndAssignedStatusAndBranchName(String loanStatus, String assignedStatus, String branchName);

    List<ClientLoan> findClientLoansByLoanStatusAndBranchName(String loanStatus, String branchName);

    List<ClientLoan> findClientLoansByBocoSignatureAndBranchName(String bocoSignature, String branchName);

    List<ClientLoan> findClientLoansByBmSignatureAndBranchName(String bmSignature, String branchName);

    List<ClientLoan> findClientLoansByCaSignature(String caSignature);

    List<ClientLoan> findClientLoansByCmSignature(String cmSignature);

    List<ClientLoan> findClientLoansByFinSignature(String finSignature);

    List<ClientLoan> findClientLoansByLoanStatusAndBranchNameAndPipelineStatus(String loanStatus, String branchName, String pipelineStatus);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndBocoSignatureAndBranchName(String loanStatus, String processLoanStatus, String bocoSignature, String branchName);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndBmSignatureAndBranchName(String loanStatus, String processLoanStatus, String bmSignature, String branchName);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndCaSignature(String loanStatus, String processLoanStatus, String CaSignature);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndCmSignature(String loanStatus, String processLoanStatus, String CmSignature);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndFinSignature(String loanStatus, String processLoanStatus, String FinSignature);

//    List<ClientLoan> findClientLoansByLoanStatusAndAssignToAndBranchNameAndProcessLoanStatusNotContains(String loanStatus, String assignTo, String branchName, String assessmentStatus);

    List<ClientLoan> findClientLoansByLoanStatusAndAssignToAndBranchNameAndProcessLoanStatus(String loanStatus, String assignTo, String branchName, String assessmentStatus);

    ClientLoan findByLoanFileId(String loanAndFileId);

    List<ClientLoan> findClientLoanByLoanStatusAndPipelineStatusAndCreditCommit(String loanStatus, String pipelineStatus, String creditCommit);

    List<ClientLoan> findClientLoanByLoanStatusAndBranchNameAndPipelineStatusAndCreditCommit(String loanStatus, String branchName, String pipelineStatus, String creditCommit);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndBocoSignatureAndPipelineStatusAndBranchName(String loanStatus, String processLoanStatus, String bocoSignature, String pipelineStatus, String branchName);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndBmSignatureAndPipelineStatusAndBranchName(String loanStatus, String processLoanStatus, String bmSignature, String pipelineStatus, String branchName);

    List<ClientLoan> findClientLoansByBocoSignatureAndCompletelyDoneAndBranchName(String bocoSignature, String completelyDone, String branchName);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndBmSignatureAndBocoSignatureAndPipelineStatusAndBranchName(String loanStatus, String processLoanStatus, String bmSignature, String bocoSignature, String pipelineStatus, String branchName);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndBmSignatureAndCaSignature(String loanStatus, String processLoanStatus, String bmSignature, String caSignature);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndCaSignatureAndCmSignature(String loanStatus, String processLoanStatus, String caSignature, String cmSignature);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndCmSignatureAndFinSignature(String loanStatus, String processLoanStatus, String cmSignature, String finSignature);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndFinSignatureAndBoardSignature(String loanStatus, String processLoanStatus, String finSignature, String boardSignature);

    List<ClientLoan> findClientLoansByBoardSignature(String boardSignature);
}
