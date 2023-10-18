package com.untucapital.usuite.utg.model.cms;

import com.untucapital.usuite.utg.model.enums.cms.ApprovalStatus;
import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author tjchidanika
 * @created 4/10/2023
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cms_transaction_voucher")
@Getter
@Setter
public class TransactionVoucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User initiator;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime applicationDate;
    private BigDecimal amount;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Vault fromVault;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Vault toVault;

    private String amountInWords;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User firstApprover;
    private LocalDateTime firstApprovedAt;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus firstApprovalStatus;
    private String firstApprovalComment;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User secondApprover;
    private LocalDateTime secondApprovedAt;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus secondApprovalStatus;
    private String secondApprovalComment;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branches branch;
}
