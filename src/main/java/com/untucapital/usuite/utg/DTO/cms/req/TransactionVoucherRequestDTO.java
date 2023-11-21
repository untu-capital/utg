package com.untucapital.usuite.utg.dto.cms.req;

import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.model.cms.TransactionPurpose;
import com.untucapital.usuite.utg.model.cms.Vault;
import com.untucapital.usuite.utg.model.enums.cms.ApprovalStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author panashe rutimhu
 * @created 6/11/2023
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionVoucherRequestDTO {

    private Integer id;
    private User initiator;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime applicationDate;
    private BigDecimal amount;
    private Vault fromVault;
    private Vault toVault;
    private String amountInWords;
    private TransactionPurpose withdrawalPurpose;
    private String currency;
    private Integer denomination100;
    private Integer denomination50;
    private Integer denomination20;
    private Integer denomination10;
    private Integer denomination5;
    private Integer denomination2;
    private Integer denomination1;
    private Integer denominationCents;
    private User firstApprover;
    private LocalDateTime firstApprovedAt;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus firstApprovalStatus;
    private String firstApprovalComment;
    private User secondApprover;
    private LocalDateTime secondApprovedAt;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus secondApprovalStatus;
    private String secondApprovalComment;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private Branches branch;
}
