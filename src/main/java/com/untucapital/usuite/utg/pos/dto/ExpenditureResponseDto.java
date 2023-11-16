package com.untucapital.usuite.utg.pos.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ExpenditureResponseDto {


    private String category;

    private int year;

    private String month;

    private Float amount;

}
