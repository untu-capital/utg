package com.untucapital.usuite.utg.model.cms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author tjchidanika
 * @created 28/9/2023
 */


@Entity
@Table(name = "audit_trails")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuditTrail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String initiator;
    private BigDecimal amount;
    private String fromVault;
    private String toVault;
    private LocalDateTime initiatedAt;

    private String firstApprover;
    private LocalDateTime firstApprovedAt;

    private String secondApprover;
    private LocalDateTime secondApprovedAt;

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime createdAt;

    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
