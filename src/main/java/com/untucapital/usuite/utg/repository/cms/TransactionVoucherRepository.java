package com.untucapital.usuite.utg.repository.cms;

import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.model.cms.TransactionVoucher;
import com.untucapital.usuite.utg.model.cms.Vault;
import com.untucapital.usuite.utg.model.enums.cms.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tjchidanika
 * @created 4/10/2023
 */

@Repository
public interface TransactionVoucherRepository extends JpaRepository<TransactionVoucher, Integer> {

    List<TransactionVoucher> findAllByInitiatorOrFirstApprovalStatusAndSecondApprovalStatus(User user, ApprovalStatus firstApproval, ApprovalStatus secondApproval);

    List<TransactionVoucher> findAllByInitiator(User user);

    List<TransactionVoucher> findAllByFirstApprover(User user);

    List<TransactionVoucher> findAllBySecondApprover(User user);

    List<TransactionVoucher> findAllByBranch(Branches branch);

    List<TransactionVoucher> findAllByFromVaultOrToVault(Vault fromVault, Vault toVault);
}
