package com.untucapital.usuite.utg.DTO.request;


import com.untucapital.usuite.utg.DTO.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AccountsReceivableRequestDTO extends AbstractEntityDTO {

    private String loanId;
    private String customerName;
    private String dueSince;
    private String expectedPayment;
    private String paymentProbability;
    private String debtValue;
}