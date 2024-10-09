package com.untucapital.usuite.utg.dto;

<<<<<<< HEAD
import lombok.*;
=======
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
>>>>>>> c3f5c93fa4cbfa09dbf6c7128a44118c29771bc2

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MaturedInterestReportDTO {
    // Getters and Setters
    private LocalDate transactionDate;         // Date of the transaction
    private String transactionDescription;     // Description of the transaction (e.g., interest applied, repayment)
    private double debit;                      // Debit amount for the transaction
    private double credit;                     // Credit amount for the transaction
    private double balance;                    // Running balance after the transaction

}
