package com.untucapital.usuite.utg.DTO.loans;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PaymentType {

    private int id;
    private String name;
    private boolean deleted;
}