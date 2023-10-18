package com.untucapital.usuite.utg.model.cms;

import com.untucapital.usuite.utg.model.Branches;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author tjchidanika
 * @created 27/9/2023
 */

@Entity
@Table(name = "vaults")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vault {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String account;
    private String type;
    private String name;
    private BigDecimal maxAmount;
    private BigDecimal currentAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    private Branches branch;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
