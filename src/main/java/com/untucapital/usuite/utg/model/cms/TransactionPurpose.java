package com.untucapital.usuite.utg.model.cms;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author tjchidanika
 * @created 5/10/2023
 */

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction_purposes")
@Getter
@Setter
public class TransactionPurpose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String name;
}
