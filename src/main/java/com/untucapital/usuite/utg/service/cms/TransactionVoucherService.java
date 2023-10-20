package com.untucapital.usuite.utg.service.cms;

import com.untucapital.usuite.utg.DTO.cms.*;
import com.untucapital.usuite.utg.exception.ResourceNotFoundException;
import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.model.cms.*;
import com.untucapital.usuite.utg.model.enums.cms.ApprovalStatus;
import com.untucapital.usuite.utg.repository.BranchRepository;
import com.untucapital.usuite.utg.repository.cms.AuthorisationRepository;
import com.untucapital.usuite.utg.repository.cms.CmsVaultPermissionRepository;
import com.untucapital.usuite.utg.repository.cms.TransactionVoucherRepository;
import com.untucapital.usuite.utg.service.UserService;
import com.untucapital.usuite.utg.utils.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private final TransactionPurposeService transactionPurposeService;
    private final EmailSender emailSender;

    //Initiate Transaction
    public TransactionVoucherResponse initiateTransaction(TransactionVoucherInitiatorRequest request) {

        User user = userService.find(request.getInitiator()).orElseThrow();
        User firstApprover = userService.find(request.getFirstApprover()).orElseThrow();
        User secondApprover = userService.find(request.getSecondApprover()).orElseThrow();

        Vault fromVault = vaultService.getVault(Integer.valueOf(request.getFromVault()));
        Vault toVault = vaultService.getVault(Integer.valueOf(request.getToVault()));

        TransactionPurpose transactionPurpose = transactionPurposeService.getById(Integer.valueOf(request.getWithdrawalPurpose()));

        Branches branch = fromVault.getBranch();

        TransactionVoucher transactionVoucher = TransactionVoucher.builder()
                .initiator(user)
                .applicationDate(LocalDateTime.now())
                .amount(request.getAmount())
                .fromVault(fromVault)
                .toVault(toVault)
                .amountInWords(request.getAmountInWords())
                .withdrawalPurpose(transactionPurpose)
                .currency(request.getCurrency())
                .denomination100(request.getDenomination100())
                .denomination50(request.getDenomination50())
                .denomination20(request.getDenomination20())
                .denomination10(request.getDenomination10())
                .denomination5(request.getDenomination5())
                .denomination2(request.getDenomination2())
                .denomination1(request.getDenomination1())
                .denominationCents(request.getDenominationCents())
                .branch(branch)
                .firstApprover(firstApprover)
                .firstApprovalStatus(ApprovalStatus.PENDING)
                .secondApprover(secondApprover)
                .secondApprovalStatus(ApprovalStatus.PENDING)
                .build();

        TransactionVoucher transactionVoucher1 = transactionVoucherRepository.save(transactionVoucher);

        sendEmail(
                firstApprover.getFirstName() + " " + firstApprover.getLastName(),
                "tjchidanika@gmail.com",
                "Transaction Approval",
                "You have a new transaction to approve ("+ createApplicationId(transactionVoucher1.getApplicationDate(), transactionVoucher1.getId()) +"). The transactional purpose is "+transactionVoucher1.getWithdrawalPurpose().getName()+".",
                user.getFirstName() + " " + user.getLastName()
        );

        return transactionVoucherResponseSerializer(transactionVoucher1);
    }

    //First Approver Transaction
    public TransactionVoucherResponse firstApproveTransaction(ApproverRequest request) {
        TransactionVoucher transactionVoucher = transactionVoucherRepository.findById(request.getId()).orElseThrow();

        if (request.getApprovalStatus().equalsIgnoreCase("APPROVED")) {
            transactionVoucher.setFirstApprovalStatus(ApprovalStatus.APPROVED);

            sendEmail(
                    transactionVoucher.getSecondApprover().getFirstName() + " " + transactionVoucher.getSecondApprover().getLastName(),
                    "tjchidanika@gmail.com",
                    "Transaction Approval",
                    "You have a new transaction to approve ("+ createApplicationId(transactionVoucher.getApplicationDate(), transactionVoucher.getId()) +"). The transactional purpose is "+transactionVoucher.getWithdrawalPurpose().getName()+".",
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
                    "Revise transaction ("+ createApplicationId(transactionVoucher.getApplicationDate(), transactionVoucher.getId()) +"). The transactional purpose is "+transactionVoucher.getWithdrawalPurpose().getName()+" ."+transactionVoucher.getFirstApprovalComment(),
                    transactionVoucher.getFirstApprover().getFirstName() + " " + transactionVoucher.getFirstApprover().getLastName()
            );
        }

        transactionVoucher.setFirstApprovedAt(LocalDateTime.now());

        TransactionVoucher transactionVoucher1 = transactionVoucherRepository.save(transactionVoucher);

        return transactionVoucherResponseSerializer(transactionVoucher1);
    }

    //Second Approver Transaction
    public TransactionVoucherResponse secondApproveTransaction(ApproverRequest request) {
        TransactionVoucher transactionVoucher = transactionVoucherRepository.findById(request.getId()).orElseThrow();

        if (request.getApprovalStatus().equalsIgnoreCase("APPROVED")) {
            transactionVoucher.setSecondApprovalStatus(ApprovalStatus.APPROVED);
            sendEmail(
                    transactionVoucher.getInitiator().getFirstName() + " " + transactionVoucher.getInitiator().getLastName(),
                    "tjchidanika@gmail.com",
                    "Transaction Approved Successfully",
                    "Revise transaction ("+ createApplicationId(transactionVoucher.getApplicationDate(), transactionVoucher.getId()) +"). The transactional purpose is "+transactionVoucher.getWithdrawalPurpose().getName()+" ."+transactionVoucher.getFirstApprovalComment(),
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
                    "Revise Transaction ("+ createApplicationId(transactionVoucher.getApplicationDate(), transactionVoucher.getId()) +").",
                    transactionVoucher.getSecondApprover().getFirstName() + " " + transactionVoucher.getSecondApprover().getLastName()
            );
        }

        transactionVoucher.setSecondApprovedAt(LocalDateTime.now());

        TransactionVoucher transactionVoucher1 = transactionVoucherRepository.save(transactionVoucher);
        return transactionVoucherResponseSerializer(transactionVoucher1);
    }

    //Get All Transactions
    public List<TransactionVoucherResponse> getAllTransactions() {
        List<TransactionVoucher> transactions = transactionVoucherRepository.findAll();
        return transactionVoucherResponseSerializerList(transactions);
    }

    //Get Transaction By id
    public TransactionVoucherResponse getTransactionById(Integer id) {
        TransactionVoucher transactionVoucher = transactionVoucherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        return transactionVoucherResponseSerializer(transactionVoucher);
    }

    //Get All Transactions By Branch
    public List<TransactionVoucherResponse> getAllTransactionsByBranch(String branchId) {
        Branches branch = branchRepository.findById(branchId).orElseThrow();
        List<TransactionVoucher> transactionVouchers = transactionVoucherRepository.findAllByBranch(branch);
        return transactionVoucherResponseSerializerList(transactionVouchers);
    }

    //Get All Transactions By Vault
    public List<TransactionVoucherResponse> getAllTransactionsByVault(Integer vaultId) {
        Vault vault = vaultService.getVault(vaultId);
        List<TransactionVoucher> transactionVouchers = transactionVoucherRepository.findAllByFromVaultOrToVault(vault, vault);
        return transactionVoucherResponseSerializerList(transactionVouchers);
    }

    //Get All Transactions By Initiator
    public List<TransactionVoucherResponse> getAllTransactionsByInitiator(String initiator) {
        User user = userService.find(initiator).orElseThrow(
                () -> new ResourceNotFoundException("Initiator", "id", initiator)
        );
        List<TransactionVoucher> transactionVouchers = transactionVoucherRepository.findAllByInitiator(user);
        return transactionVoucherResponseSerializerList(transactionVouchers);
    }

    //Get All Transactions By First Approver
    public List<TransactionVoucherResponse> getAllTransactionsByFirstApprover(String firstApprover) {
        User user = userService.find(firstApprover).orElseThrow(
                () -> new ResourceNotFoundException("First Approver", "id", firstApprover)
        );
        List<TransactionVoucher> transactionVouchers = transactionVoucherRepository.findAllByFirstApprover(user);
        return transactionVoucherResponseSerializerList(transactionVouchers);
    }

    //Get All Transactions By First Approver @Head Office or Second Approver @Branches
    public List<TransactionVoucherResponse> getAllTransactionsBySecondApprover(String approver) {

        User user = userService.find(approver).orElseThrow();
        List<TransactionVoucher> transactionVouchers = transactionVoucherRepository.findAllBySecondApprover(user);

        return transactionVoucherResponseSerializerList(transactionVouchers);
    }

    //Initiator Update the Transaction If it is not yet approved or got rejected by the first approver
    public TransactionVoucherResponse updateTransactionVoucher(TransactionVoucherUpdateRequest request) {

        TransactionVoucher transactionVoucher = transactionVoucherRepository.findById(request.getId()).orElseThrow();
        Integer fromVaultOldId = transactionVoucher.getFromVault().getId();
        Integer toVaultOldId = transactionVoucher.getToVault().getId();
        Integer oldTransactionId = transactionVoucher.getWithdrawalPurpose().getId();

        if (request.getFromVault() != null) {
            fromVaultOldId = request.getFromVault();
        }

        if (request.getToVault() != null) {
            toVaultOldId = request.getToVault();
        }

        if (request.getWithdrawalPurpose() != null) {
            oldTransactionId = request.getWithdrawalPurpose();
        }

        Vault fromVault = vaultService.getVault(fromVaultOldId);
        Vault toVault = vaultService.getVault(toVaultOldId);
        TransactionPurpose transactionPurpose = transactionPurposeService.getById(oldTransactionId);

        if (transactionVoucher.getFirstApprovalStatus() == ApprovalStatus.APPROVED && transactionVoucher.getSecondApprovalStatus() == ApprovalStatus.APPROVED) {
            throw new RuntimeException("Transaction already Approved.");
        }

        if (request.getAmount() != null && !request.getAmount().equals(transactionVoucher.getAmount())) {
            transactionVoucher.setAmount(request.getAmount());
        }

        if (request.getFromVault() != null && fromVault != transactionVoucher.getFromVault()) {
            transactionVoucher.setFromVault(fromVault);
        }

        if (request.getToVault() != null && toVault != transactionVoucher.getToVault()) {
            transactionVoucher.setToVault(toVault);
        }

        if (request.getAmountInWords() != null && !request.getAmountInWords().equalsIgnoreCase(transactionVoucher.getAmountInWords())) {
            transactionVoucher.setAmountInWords(request.getAmountInWords());
        }

        if (request.getWithdrawalPurpose() != null && transactionPurpose != transactionVoucher.getWithdrawalPurpose()) {
            transactionVoucher.setWithdrawalPurpose(transactionPurpose);
        }

        if (request.getCurrency() != null && !request.getCurrency().equalsIgnoreCase(transactionVoucher.getCurrency())) {
            transactionVoucher.setCurrency(request.getCurrency());
        }

        if (request.getDenomination100() != null && !request.getDenomination100().equals(transactionVoucher.getDenomination100())) {
            transactionVoucher.setDenomination100(request.getDenomination100());
        }

        if (request.getDenomination50() != null && !request.getDenomination50().equals(transactionVoucher.getDenomination50())) {
            transactionVoucher.setDenomination50(request.getDenomination50());
        }

        if (request.getDenomination20() != null && !request.getDenomination20().equals(transactionVoucher.getDenomination20())) {
            transactionVoucher.setDenomination20(request.getDenomination20());
        }

        if (request.getDenomination10() != null && !request.getDenomination10().equals(transactionVoucher.getDenomination10())) {
            transactionVoucher.setDenomination10(request.getDenomination10());
        }

        if (request.getDenomination5() != null && !request.getDenomination5().equals(transactionVoucher.getDenomination5())) {
            transactionVoucher.setDenomination5(request.getDenomination5());
        }

        if (request.getDenomination2() != null && !request.getDenomination2().equals(transactionVoucher.getDenomination2())) {
            transactionVoucher.setDenomination2(request.getDenomination2());
        }

        if (request.getDenomination1() != null && !request.getDenomination1().equals(transactionVoucher.getDenomination1())) {
            transactionVoucher.setDenomination1(request.getDenomination1());
        }

        if (request.getDenominationCents() != null && !request.getDenominationCents().equals(transactionVoucher.getDenominationCents())) {
            transactionVoucher.setDenomination1(request.getDenominationCents());
        }

        transactionVoucher.setFirstApprovalStatus(ApprovalStatus.PENDING);
        transactionVoucher.setSecondApprovalStatus(ApprovalStatus.PENDING);
        TransactionVoucher transactionVoucher1 = transactionVoucherRepository.save(transactionVoucher);
        return transactionVoucherResponseSerializer(transactionVoucher1);
    }

    //Delete Transaction
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
    public List<Authorisation> getAuthorisationByUserId(String userId) {
        return authorisationRepository.findAllByUserId(userId);
    }

    //get cms permission by user id
    public List<CmsVaultPermission> getCmsVaultPermissionByUserId(String userId) {
        return cmsVaultPermissionRepository.findAllByUserid(userId);
    }

    //get first approvers by branchId
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
            TransactionVoucherResponse transactionVoucherResponse = transactionVoucherResponseSerializer(transaction);
            transactionVoucherResponses.add(transactionVoucherResponse);
        }

        return transactionVoucherResponses;
    }

    //function to serialize the transaction voucher to transaction voucher response
    private TransactionVoucherResponse transactionVoucherResponseSerializer(TransactionVoucher transaction) {

        UserDTO initiator = UserDTO.builder()
                .id(transaction.getInitiator().getId())
                .firstName(transaction.getInitiator().getFirstName())
                .lastName(transaction.getInitiator().getLastName())
                .build();

        UserDTO firstApprover = UserDTO.builder()
                .id(transaction.getFirstApprover().getId())
                .firstName(transaction.getFirstApprover().getFirstName())
                .lastName(transaction.getFirstApprover().getLastName())
                .build();

        UserDTO secondApprover = UserDTO.builder()
                .id(transaction.getSecondApprover().getId())
                .firstName(transaction.getSecondApprover().getFirstName())
                .lastName(transaction.getSecondApprover().getLastName())
                .build();

        BranchDTO branch = BranchDTO.builder()
                .id(transaction.getBranch().getId())
                .name(transaction.getBranch().getBranchName())
                .build();

        VaultDTO fromVault = VaultDTO.builder()
                .id(transaction.getFromVault().getId())
                .account(transaction.getFromVault().getAccount())
                .type(transaction.getFromVault().getType())
                .name(transaction.getFromVault().getName())
                .build();

        VaultDTO toVault = VaultDTO.builder()
                .id(transaction.getToVault().getId())
                .account(transaction.getToVault().getAccount())
                .type(transaction.getToVault().getType())
                .name(transaction.getToVault().getName())
                .build();

        return TransactionVoucherResponse.builder()
                .id(transaction.getId())
                .applicationNo(createApplicationId(transaction.getApplicationDate(), transaction.getId()))
                .initiator(initiator)
                .applicationDate(dateFormatter(transaction.getApplicationDate()))
                .firstApprover(firstApprover)
                .firstApprovedAt(dateFormatter(transaction.getFirstApprovedAt()))
                .firstApprovalStatus(transaction.getFirstApprovalStatus())
                .firstApprovalComment(transaction.getFirstApprovalComment())
                .secondApprover(secondApprover)
                .secondApprovedAt(dateFormatter(transaction.getSecondApprovedAt()))
                .secondApprovalStatus(transaction.getSecondApprovalStatus())
                .secondApprovalComment(transaction.getSecondApprovalComment())
                .amountInWords(transaction.getAmountInWords())
                .currency(transaction.getCurrency())
                .amount(transaction.getAmount())
                .denomination1(transaction.getDenomination1())
                .denomination2(transaction.getDenomination2())
                .denomination5(transaction.getDenomination5())
                .denomination10(transaction.getDenomination10())
                .denomination20(transaction.getDenomination20())
                .denomination50(transaction.getDenomination50())
                .denomination100(transaction.getDenomination100())
                .denominationCents(transaction.getDenominationCents())
                .withdrawalPurpose(transaction.getWithdrawalPurpose().getName())
                .branch(branch)
                .fromVault(fromVault)
                .toVault(toVault)
                .build();
    }

    //Function To Format date
    private String dateFormatter(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd (HH:mm)");

        if (date == null) {
            return null;
        }

        return date.format(formatter);
    }

    //Function To Format ApplicationId
    private String createApplicationId(LocalDateTime date,Integer id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        return date.format(formatter)+'-'+id;
    }

    //Function To Send Email
    private void sendEmail(String recipientName, String recipientEmail, String recipientSubject, String recipientMessage, String senderName) {
        String emailText = emailSender.approvalMessage(recipientName, recipientMessage, senderName);
        emailSender.send(recipientEmail, recipientSubject, emailText);
    }

}
