package com.untucapital.usuite.utg.model.cms;

import com.untucapital.usuite.utg.model.Branches;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Profile;

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
    @Column(name = "id")
    private Integer id;

    @Column(name = "account")
    private String account;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "max_amount")
    private BigDecimal maxAmount;

    @Column(name = "current_amount")
    private BigDecimal currentAmount;

    @Column(name = "account_link")
    private Integer accountLink;

    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "branch")
    private Branches branch;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    public Integer getAccountLink() {
        return accountLink;
    }

    public void setAccountLink(Integer accountLink) {
        this.accountLink = accountLink;
    }

//    public Branches getBranch() {
//        return branch;
//    }
//
//    public void setBranch(Branches branch) {
//        this.branch = branch;
//    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
