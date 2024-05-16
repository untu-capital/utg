package com.untucapital.usuite.utg.model.transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Currency {

    private String code;
    private String name;
    private int decimalPlaces;
    private int inMultiplesOf;
    private String displaySymbol;
    private String nameCode;
    private String displayLabel;


}
