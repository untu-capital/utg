package com.untucapital.usuite.utg.pos.model;

import com.untucapital.usuite.utg.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Budget extends AbstractEntity {

    @Column(name = "category")
    private String category;


    @Column(name = "year")
    private int year;

    @Column(name = "month")
    private int month;

    @Column(name = "amount")
    private Float amount;

}
