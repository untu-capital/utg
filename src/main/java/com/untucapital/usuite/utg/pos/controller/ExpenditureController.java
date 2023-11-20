package com.untucapital.usuite.utg.pos.controller;

import com.untucapital.usuite.utg.dto.response.BudgetResponseDTO;
import com.untucapital.usuite.utg.pos.dto.ExpenditureDto;
import com.untucapital.usuite.utg.pos.dto.ExpenditureResponseDto;
import com.untucapital.usuite.utg.pos.dto.POSBalanceSheetDto;
import com.untucapital.usuite.utg.pos.model.POSBalanceSheet;
import com.untucapital.usuite.utg.pos.service.ExpenditureService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(value = "pos/expenditure")
@RequiredArgsConstructor
public class ExpenditureController {

    private final ExpenditureService expenditureService;

    //Get Budget By Id
    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExpenditureDto getExpenditureById(@PathVariable Integer id) {

        return expenditureService.getExpenditureById(id);
    }

    @GetMapping("getByMonthAndYear/{month}/{year}")
    @ResponseStatus(HttpStatus.OK)
    public List<ExpenditureResponseDto> getExpenditureByYear( @PathVariable int year) {

        return expenditureService.getExpenditureByYear(year);
    }

    @GetMapping("/getByCategoryMonthAndYear/{category}/{month}/{year}")
    @ResponseStatus(HttpStatus.OK)
    public List<ExpenditureResponseDto> getExpenditureByCategoryAndMonthAndYear(@PathVariable String category, @PathVariable String month, @PathVariable int year) {

        return expenditureService.getExpenditureByCategoryAndMonthAndYear(category, month, year);
    }

    @GetMapping("/getByPeriod/{dateFrom}/{dateTo}")
    @ResponseStatus(HttpStatus.OK)
    public List<ExpenditureResponseDto> getExpenditureByPeriod(@PathVariable String dateFrom, @PathVariable String dateTo) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(dateFrom, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(dateTo, formatter);

        return expenditureService.getExpenditureByPeriod(startDateTime, endDateTime);
    }

    @GetMapping("/getBalanceSheet/balanceSheet/{year}")
    @ResponseStatus(HttpStatus.OK)
    public List<POSBalanceSheetDto> getBalanceSheet(@PathVariable int year) {

        return expenditureService.compareBudgetWithExpenditure(year);
    }

}
