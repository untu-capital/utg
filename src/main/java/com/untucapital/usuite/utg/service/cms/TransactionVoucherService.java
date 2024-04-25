package com.untucapital.usuite.utg.service.cms;

import com.untucapital.usuite.utg.DTO.cms.res.VaultResponseDTO;
import com.untucapital.usuite.utg.dto.cms.ApproverRequest;
import com.untucapital.usuite.utg.dto.cms.TransactionVoucherInitiatorRequest;
import com.untucapital.usuite.utg.dto.cms.TransactionVoucherResponse;
import com.untucapital.usuite.utg.dto.cms.TransactionVoucherUpdateRequest;
import com.untucapital.usuite.utg.exception.ResourceNotFoundException;
import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.model.cms.Authorisation;
import com.untucapital.usuite.utg.model.cms.CmsVaultPermission;
import com.untucapital.usuite.utg.model.cms.TransactionVoucher;
import com.untucapital.usuite.utg.model.cms.Vault;
import com.untucapital.usuite.utg.model.enums.cms.ApprovalStatus;
import com.untucapital.usuite.utg.processor.TransactionVoucherProcessor;
import com.untucapital.usuite.utg.repository.BranchRepository;
import com.untucapital.usuite.utg.repository.cms.AuthorisationRepository;
import com.untucapital.usuite.utg.repository.cms.CmsVaultPermissionRepository;
import com.untucapital.usuite.utg.repository.cms.TransactionVoucherRepository;
import com.untucapital.usuite.utg.service.UserService;
import com.untucapital.usuite.utg.utils.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tjchidanika
 * @created 4/10/2023
 */

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionVoucherService {
    private final TransactionVoucherRepository transactionVoucherRepository;
    private final AuthorisationRepository authorisationRepository;
    private final CmsVaultPermissionRepository cmsVaultPermissionRepository;
    private final VaultService vaultService;
    private final BranchRepository branchRepository;
    private final UserService userService;
    private final EmailSender emailSender;
    private final TransactionVoucherProcessor transactionVoucherProcessor;

    //Initiate Transaction
    @Transactional(value = "transactionManager")
    public TransactionVoucherResponse initiateTransaction(TransactionVoucherInitiatorRequest request) {

        System.out.println("Transaction Voucher => "+ request.toString());
        User firstApprover = userService.find(request.getFirstApprover()).orElseThrow();

        User user = userService.find(request.getInitiator()).orElseThrow();

        TransactionVoucher transactionVoucher = transactionVoucherProcessor.processTransactionVoucher(request);

        TransactionVoucher transactionVoucher1 = transactionVoucherRepository.save(transactionVoucher);

        sendEmail(
                firstApprover.getFirstName() + " " + firstApprover.getLastName(),
                "tjchidanika@gmail.com",
                "Transaction Approval",
                "You have a new transaction to approve - " + transactionVoucher1.getReferenceNumber() + ". The transactional purpose is " + transactionVoucher1.getWithdrawalPurpose().getName() + ".",
                user.getFirstName() + " " + user.getLastName()
        );

        return transactionVoucherProcessor.transactionVoucherResponseSerializer(transactionVoucher1);
    }

    //First Approver Transaction
    @Transactional(value = "transactionManager")
    public TransactionVoucherResponse firstApproveTransaction(ApproverRequest request) {
        TransactionVoucher transactionVoucher = transactionVoucherRepository.findById(request.getId()).orElseThrow();

        if (request.getApprovalStatus().equalsIgnoreCase("APPROVED")) {
            transactionVoucher.setFirstApprovalStatus(ApprovalStatus.APPROVED);

            sendEmail(
                    transactionVoucher.getSecondApprover().getFirstName() + " " + transactionVoucher.getSecondApprover().getLastName(),
                    "tjchidanika@gmail.com",
                    "Transaction Approval",
                    "You have a new transaction to approve - " + transactionVoucher.getReferenceNumber() + ". The transactional purpose is " + transactionVoucher.getWithdrawalPurpose().getName() + ".",
                    transactionVoucher.getFirstApprover().getFirstName() + " " + transactionVoucher.getFirstApprover().getLastName()
            );
        }

        if (request.getApprovalStatus().equalsIgnoreCase("REVISE")) {
            transactionVoucher.setFirstApprovalStatus(ApprovalStatus.REVISE);
            transactionVoucher.setFirstApprovalComment(request.getComment());

            sendEmail(
                    transactionVoucher.getInitiator().getFirstName() + " " + transactionVoucher.getInitiator().getLastName(),
                    "tjchidanika@gmail.com",
                    "Revise Transaction",
                    "Revise transaction - " + transactionVoucher.getReferenceNumber() + " The transactional purpose is " + transactionVoucher.getWithdrawalPurpose().getName() + " ." + transactionVoucher.getFirstApprovalComment(),
                    transactionVoucher.getFirstApprover().getFirstName() + " " + transactionVoucher.getFirstApprover().getLastName()
            );
        }

        transactionVoucher.setFirstApprovedAt(LocalDateTime.now());

        TransactionVoucher transactionVoucher1 = transactionVoucherRepository.save(transactionVoucher);

        return transactionVoucherProcessor.transactionVoucherResponseSerializer(transactionVoucher1);
    }

    //Second Approver Transaction
    @Transactional(value = "transactionManager")
    public TransactionVoucherResponse secondApproveTransaction(ApproverRequest request) {
        TransactionVoucher transactionVoucher = transactionVoucherRepository.findById(request.getId()).orElseThrow();

        if (request.getApprovalStatus().equalsIgnoreCase("APPROVED")) {
            transactionVoucher.setSecondApprovalStatus(ApprovalStatus.APPROVED);
            sendEmail(
                    transactionVoucher.getInitiator().getFirstName() + " " + transactionVoucher.getInitiator().getLastName(),
                    "tjchidanika@gmail.com",
                    "Transaction Approved Successfully",
                    "Revise transaction - " + transactionVoucher.getReferenceNumber() + ". The transactional purpose is " + transactionVoucher.getWithdrawalPurpose().getName() + " ." + transactionVoucher.getFirstApprovalComment(),
                    transactionVoucher.getSecondApprover().getFirstName() + " " + transactionVoucher.getSecondApprover().getLastName()
            );
        }

        if (request.getApprovalStatus().equalsIgnoreCase("REVISE")) {
            transactionVoucher.setSecondApprovalStatus(ApprovalStatus.REVISE);
            transactionVoucher.setSecondApprovalComment(request.getComment());
            sendEmail(
                    transactionVoucher.getInitiator().getFirstName() + " " + transactionVoucher.getInitiator().getLastName(),
                    "tjchidanika@gmail.com",
                    "Revise Transaction",
                    "Revise Transaction - " + transactionVoucher.getReferenceNumber() + ".",
                    transactionVoucher.getSecondApprover().getFirstName() + " " + transactionVoucher.getSecondApprover().getLastName()
            );
        }

        transactionVoucher.setSecondApprovedAt(LocalDateTime.now());

        TransactionVoucher transactionVoucher1 = transactionVoucherRepository.save(transactionVoucher);
        return transactionVoucherProcessor.transactionVoucherResponseSerializer(transactionVoucher1);
    }

    //Get All Transactions
    @Transactional(value = "transactionManager")
    public List<TransactionVoucherResponse> getAllTransactions() {
        List<TransactionVoucher> transactions = transactionVoucherRepository.findAll();
        return transactionVoucherResponseSerializerList(transactions);
    }

    //Get Transaction By id
    @Transactional(value = "transactionManager")
    public TransactionVoucherResponse getTransactionById(Integer id) {
        TransactionVoucher transactionVoucher = transactionVoucherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        return transactionVoucherProcessor.transactionVoucherResponseSerializer(transactionVoucher);
    }

    //Get All Transactions By Branch
    @Transactional(value = "transactionManager")
    public List<TransactionVoucherResponse> getAllTransactionsByBranch(String branchId) {
        Branches branch = branchRepository.findById(branchId).orElseThrow();
        List<TransactionVoucher> transactionVouchers = transactionVoucherRepository.findAllByBranch(branch);
        return transactionVoucherResponseSerializerList(transactionVouchers);
    }

    //Get All Transactions By Vault
    @Transactional(value = "transactionManager")
    public List<TransactionVoucherResponse> getAllTransactionsByVault(Integer vaultId) {
        Vault vault = new Vault();
        VaultResponseDTO vaultResponse = vaultService.getVault(vaultId);
        BeanUtils.copyProperties(vaultResponse, vault);
        List<TransactionVoucher> transactionVouchers = transactionVoucherRepository.findAllByFromVaultOrToVault(vault, vault);
        return transactionVoucherResponseSerializerList(transactionVouchers);
    }

    //Get All Transactions By Initiator
    @Transactional(value = "transactionManager")
    public List<TransactionVoucherResponse> getAllTransactionsByInitiator(String initiator) {
        List<TransactionVoucher> transactionVouchers;
        User user = userService.find(initiator).orElseThrow(
                () -> new ResourceNotFoundException("Initiator", "id", initiator)
        );

        if (user.getBranch().equalsIgnoreCase("Head Office")) {

            transactionVouchers = transactionVoucherRepository.
                    findAllByInitiatorOrFirstApprovalStatusAndSecondApprovalStatus(user, ApprovalStatus.APPROVED, ApprovalStatus.APPROVED);
        } else {
            transactionVouchers = transactionVoucherRepository.findAllByInitiator(user);
        }
        return transactionVoucherResponseSerializerList(transactionVouchers);
    }

    //Get All Transactions By First Approver
    @Transactional(value = "transactionManager")
    public List<TransactionVoucherResponse> getAllTransactionsByFirstApprover(String firstApprover) {
        User user = userService.find(firstApprover).orElseThrow(
                () -> new ResourceNotFoundException("First Approver", "id", firstApprover)
        );
        List<TransactionVoucher> transactionVouchers = transactionVoucherRepository.findAllByFirstApprover(user);
        return transactionVoucherResponseSerializerList(transactionVouchers);
    }

    //Get All Transactions By First Approver @Head Office or Second Approver @Branches
    @Transactional(value = "transactionManager")
    public List<TransactionVoucherResponse> getAllTransactionsBySecondApprover(String approver) {

        User user = userService.find(approver).orElseThrow();
        List<TransactionVoucher> transactionVouchers = transactionVoucherRepository.findAllBySecondApprover(user);

        return transactionVoucherResponseSerializerList(transactionVouchers);
    }

    //Initiator Update the Transaction If it is not yet approved or got rejected by the first approver
    @Transactional(value = "transactionManager")
    public TransactionVoucherResponse updateTransactionVoucher(TransactionVoucherUpdateRequest request) {
        System.out.println("Transaction Voucher Update "+ request.toString());

        TransactionVoucher transactionVoucher = transactionVoucherRepository.findById(request.getId()).orElseThrow();

        transactionVoucher = transactionVoucherProcessor.processUpdatedTransactionVoucher(transactionVoucher, request);

        TransactionVoucher transactionVoucher1 = transactionVoucherRepository.save(transactionVoucher);

        return transactionVoucherProcessor.transactionVoucherResponseSerializer(transactionVoucher1);
    }

    //Delete Transaction
    @Transactional(value = "transactionManager")
    public String deleteTransaction(Integer id) {
        TransactionVoucher transactionVoucher = transactionVoucherRepository.findById(id).orElseThrow();

        if (transactionVoucher.getFirstApprovalStatus() == ApprovalStatus.APPROVED || transactionVoucher.getSecondApprovalStatus() == ApprovalStatus.APPROVED) {
            throw new RuntimeException("Transaction already approved by the first approver");
        }

        transactionVoucher.setBranch(null);
        transactionVoucher.setFirstApprover(null);
        transactionVoucher.setSecondApprover(null);
        transactionVoucher.setInitiator(null);
        transactionVoucher.setFromVault(null);
        transactionVoucher.setToVault(null);
        transactionVoucher.setWithdrawalPurpose(null);
        transactionVoucherRepository.delete(transactionVoucher);

        return String.format("Transaction with id %d deleted successfully", id);
    }

    //Get Authorisation by user id
    @Transactional(value = "transactionManager")
    public List<Authorisation> getAuthorisationByUserId(String userId) {
        return authorisationRepository.findAllByUserId(userId);
    }

    //get cms permission by user id
    @Transactional(value = "transactionManager")
    public List<CmsVaultPermission> getCmsVaultPermissionByUserId(String userId) {
        return cmsVaultPermissionRepository.findAllByUserid(userId);
    }

    //get first approvers by branchId
    @Transactional(value = "transactionManager")
    public List<User> findAllByBranchIdAndAuthLevel(String branchId, String authLevel) {
        List<String> authUserIds = new ArrayList<>();
        List<Authorisation> authorisations = authorisationRepository.findAllByBranchIdAndAuthLevel(branchId, authLevel);
        for (Authorisation authorisation :
                authorisations) {
            authUserIds.add(authorisation.getUserId());
        }

        return userService.findAllById(authUserIds);
    }

    //function to serialize the transaction voucher to transaction voucher response
    private List<TransactionVoucherResponse> transactionVoucherResponseSerializerList(List<TransactionVoucher> transactions) {
        List<TransactionVoucherResponse> transactionVoucherResponses = new ArrayList<>();

        for (TransactionVoucher transaction :
                transactions) {
            TransactionVoucherResponse transactionVoucherResponse = transactionVoucherProcessor.transactionVoucherResponseSerializer(transaction);
            transactionVoucherResponses.add(transactionVoucherResponse);
        }

        return transactionVoucherResponses;
    }


    //Function To Send Email
    private void sendEmail(String recipientName, String recipientEmail, String recipientSubject, String recipientMessage, String senderName) {
        String emailText = emailSender.approvalMessage(recipientName, recipientMessage, senderName);
        emailSender.send(recipientEmail, recipientSubject, emailText);
    }

}
