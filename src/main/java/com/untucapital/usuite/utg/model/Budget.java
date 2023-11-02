package com.untucapital.usuite.utg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category")
    private String category;

    @Column(name = "year")
    private int year;

    @Column(name = "january")
    private BigDecimal january;

    @Column(name = "february")
    private BigDecimal february;

    @Column(name = "march")
    private BigDecimal march;

    @Column(name = "april")
    private BigDecimal april;

    @Column(name = "may")
    private BigDecimal may;

    @Column(name = "june")
    private BigDecimal june;

    @Column(name = "july")
    private BigDecimal july;

    @Column(name = "august")
    private BigDecimal august;

    @Column(name = "september")
    private BigDecimal september;

    @Column(name = "october")
    private BigDecimal october;

    @Column(name = "november")
    private BigDecimal november;

    @Column(name = "december")
    private BigDecimal december;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
