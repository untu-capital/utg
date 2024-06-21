package com.untucapital.usuite.utg.repository.cms;

import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.model.cms.TransactionVoucher;
import com.untucapital.usuite.utg.model.cms.Vault;
import com.untucapital.usuite.utg.model.enums.cms.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author tjchidanika
 * @created 4/10/2023
 */

@Repository
public interface TransactionVoucherRepository extends JpaRepository<TransactionVoucher, Integer> {

    List<TransactionVoucher> findAllByInitiator(User user);

    List<TransactionVoucher> findAllByFirstApprover(User user);

    List<TransactionVoucher> findAllBySecondApprover(User user);

    List<TransactionVoucher> findAllByBranch(Branches branch);

    List<TransactionVoucher> findAllByFromVaultOrToVault(Vault fromVault, Vault toVault);

    List<TransactionVoucher> findAllByInitiatorOrFirstApprovalStatusAndSecondApprovalStatus(User user, ApprovalStatus approvalStatus, ApprovalStatus approvalStatus1);

    TransactionVoucher findFirstByOrderByCreatedAtDesc();

    Optional<TransactionVoucher> findByAmountAndAmountInWordsAndInitiator_IdAndDenomination100AndDenomination50AndDenomination20AndDenomination10AndDenomination5AndDenomination2AndDenomination1(BigDecimal amount, String amountInWords, String id, Integer denomination100, Integer denomination50, Integer denomination20, Integer denomination10, Integer denomination5, Integer denomination2, Integer denomination1);


}
