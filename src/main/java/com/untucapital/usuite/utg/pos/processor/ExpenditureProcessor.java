package com.untucapital.usuite.utg.pos.processor;

import com.untucapital.usuite.utg.pos.dto.ExpenditureResponseDto;
import com.untucapital.usuite.utg.pos.dto.POSBalanceSheetDto;
import com.untucapital.usuite.utg.pos.model.Budget;
import com.untucapital.usuite.utg.pos.model.Expenditure;
import com.untucapital.usuite.utg.pos.model.POSBalanceSheet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ExpenditureProcessor {


    public List<ExpenditureResponseDto> setResponse(List<Expenditure> expenditureList){

        List<ExpenditureResponseDto> result = new ArrayList<>();

        Float total = 0f;

        for (Expenditure expenditure : expenditureList) {

            ExpenditureResponseDto response = new ExpenditureResponseDto();
            response.setCategory(expenditure.getCategory());
            response.setMonth(expenditure.getMonth());
            response.setYear(expenditure.getYear());

            total = total + Float.parseFloat(String.valueOf(expenditure.getAmount()));
            response.setTotalAmount(total);

            result.add(response);
        }

        return result;
    }

    public List<POSBalanceSheetDto> setBalanceSheetResponse(List<Budget> budgetList, List<Object[]> expenditureDtoList, int year){

        List<POSBalanceSheetDto> posBalanceSheetList = new ArrayList<>();

        for (Object[] result : expenditureDtoList) {

            POSBalanceSheetDto posBalanceSheetDto = new POSBalanceSheetDto();

            log.info("result: {}", expenditureDtoList);
            POSBalanceSheet posBalanceSheet = new POSBalanceSheet();


            String monthString = (String) result[0];
            int month = Integer.parseInt(monthString);
            BigDecimal sumAmountBig = (BigDecimal) result[1];
            float sumAmount = sumAmountBig.floatValue();


            Budget budget = budgetList.stream()
                    .filter(b -> b.getMonth() == month)
                    .findFirst()
                    .orElse(null);

            log.info("Month: {}", month);

            if (budget != null) {
                switch (month) {
                    case 1:
                        posBalanceSheet.setExpenditure(sumAmount);
                        posBalanceSheet.setBudget(budget.getAmount());
                        posBalanceSheet.setBalance(budget.getAmount() - sumAmount);
                        posBalanceSheet.setMonth("January");

                        break;
                    case 2:
                        posBalanceSheet.setExpenditure(sumAmount);
                        posBalanceSheet.setBudget(budget.getAmount());
                        posBalanceSheet.setBalance(budget.getAmount() - sumAmount);
                        posBalanceSheet.setMonth("February");

                        break;
                    case 3:
                        posBalanceSheet.setExpenditure(sumAmount);
                        posBalanceSheet.setBudget(budget.getAmount());
                        posBalanceSheet.setBalance(budget.getAmount() - sumAmount);
                        posBalanceSheet.setMonth("March");


                        break;
                    case 4:
                        posBalanceSheet.setExpenditure(sumAmount);
                        posBalanceSheet.setBudget(budget.getAmount());
                        posBalanceSheet.setBalance(budget.getAmount() - sumAmount);
                        posBalanceSheet.setMonth("April");

                        break;
                    case 5:
                        posBalanceSheet.setExpenditure(sumAmount);
                        posBalanceSheet.setBudget(budget.getAmount());
                        posBalanceSheet.setBalance(budget.getAmount() - sumAmount);
                        posBalanceSheet.setMonth("May");

                        break;
                    case 6:
                        posBalanceSheet.setExpenditure(sumAmount);
                        posBalanceSheet.setBudget(budget.getAmount());
                        posBalanceSheet.setBalance(budget.getAmount() - sumAmount);
                        posBalanceSheet.setMonth("June");

                        break;
                    case 7:
                        posBalanceSheet.setExpenditure(sumAmount);
                        posBalanceSheet.setBudget(budget.getAmount());
                        posBalanceSheet.setBalance(budget.getAmount() - sumAmount);
                        posBalanceSheet.setMonth("July");

                        break;
                    case 8:
                        posBalanceSheet.setExpenditure(sumAmount);
                        posBalanceSheet.setBudget(budget.getAmount());
                        posBalanceSheet.setBalance(budget.getAmount() - sumAmount);
                        posBalanceSheet.setMonth("August");

                        break;
                    case 9:
                        posBalanceSheet.setExpenditure(sumAmount);
                        posBalanceSheet.setBudget(budget.getAmount());
                        posBalanceSheet.setBalance(budget.getAmount() - sumAmount);
                        posBalanceSheet.setMonth("September");

                        break;
                    case 10:
                        posBalanceSheet.setExpenditure(sumAmount);
                        posBalanceSheet.setBudget(budget.getAmount());
                        posBalanceSheet.setBalance(budget.getAmount() - sumAmount);
                        posBalanceSheet.setMonth("October");

                        break;
                    case 11:
                        posBalanceSheet.setExpenditure(sumAmount);
                        posBalanceSheet.setBudget(budget.getAmount());
                        posBalanceSheet.setBalance(budget.getAmount() - sumAmount);
                        posBalanceSheet.setMonth("November");

                        break;
                    case 12:
                        posBalanceSheet.setExpenditure(sumAmount);
                        posBalanceSheet.setBudget(budget.getAmount());
                        posBalanceSheet.setBalance(budget.getAmount() - sumAmount);
                        posBalanceSheet.setMonth("December");

                        break;
                }
            }else {
                throw  new RuntimeException("Budget not found");
            }
            posBalanceSheet.setYear(year);
            BeanUtils.copyProperties(posBalanceSheet, posBalanceSheetDto);
            posBalanceSheetList.add(posBalanceSheetDto);
            log.info("PosBalanceSheetList: {}", posBalanceSheetList);
        }


            return posBalanceSheetList;
    }
}
