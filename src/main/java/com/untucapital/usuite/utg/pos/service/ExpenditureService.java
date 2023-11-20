package com.untucapital.usuite.utg.pos.service;


import com.untucapital.usuite.utg.dto.response.BudgetResponseDTO;
import com.untucapital.usuite.utg.pos.dto.ExpenditureDto;
import com.untucapital.usuite.utg.pos.dto.ExpenditureResponseDto;
import com.untucapital.usuite.utg.pos.dto.POSBalanceSheetDto;
import com.untucapital.usuite.utg.pos.model.Budget;
import com.untucapital.usuite.utg.pos.model.Expenditure;
import com.untucapital.usuite.utg.pos.model.POSBalanceSheet;
import com.untucapital.usuite.utg.pos.repository.ExpenditureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenditureService {

    private final ExpenditureRepository expenditureRepository;
    private final BudgetService budgetService;

    @Transactional(value = "transactionManager")
    public ExpenditureDto getExpenditureById(Integer id) {

        ExpenditureDto response = new ExpenditureDto();
        Optional<Expenditure> expenditure = expenditureRepository.findById(id);

        if (expenditure.isPresent()) {

            BeanUtils.copyProperties(expenditure.get(), response);

        } else {

            throw new RuntimeException("Expenditure not found");
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public Float getExpenditureByCategoryAndPeriod(String category, LocalDateTime dateFrom, LocalDateTime dateTor) {

        List<ExpenditureDto> response = new ArrayList<>();
        float total = 0f;

        List<Expenditure> expenditureList = expenditureRepository.findByCategoryAndCreatedAtAndCreatedAt(category, dateFrom, dateTor);

        for (Expenditure expenditure : expenditureList) {

            total = total + Float.parseFloat(String.valueOf(expenditure.getAmount()));

        }

        return total;
    }

    @Transactional(value = "transactionManager")
    public Float getTotalExpenditureByYearAndMonth(String month, int year) {

        List<ExpenditureDto> response = new ArrayList<>();
        float total = 0f;

        List<Expenditure> expenditureList = expenditureRepository.findByMonthAndYear(month, year);

        for (Expenditure expenditure : expenditureList) {

            total = total + Float.parseFloat(String.valueOf(expenditure.getAmount()));

        }

        return total;
    }

    @Transactional(value = "transactionManager")
    public List<ExpenditureDto> getExpenditureByYear( int year) {

        List<ExpenditureDto> response = new ArrayList<>();
        Float total = 0f;

        List<Expenditure> expenditureList = expenditureRepository.findByYear( year);

        for (Expenditure expenditure : expenditureList) {
            ExpenditureDto expenditureDto = new ExpenditureDto();
            BeanUtils.copyProperties(expenditure, expenditureDto);
            response.add(expenditureDto);
            total = total + Float.parseFloat(String.valueOf(expenditure.getAmount()));

        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public ExpenditureResponseDto getExpenditureByCategoryAndMonthAndYear(String category, String month,int year) {

        float total = 0f;

        ExpenditureResponseDto response = new ExpenditureResponseDto();
        List<Expenditure> expenditureList = expenditureRepository.findByCategoryAndMonthAndYear(category,month, year);

        for (Expenditure expenditure : expenditureList) {
            response.setCategory(expenditure.getCategory());
            response.setMonth(expenditure.getMonth());
            response.setYear(expenditure.getYear());

            total = total + Float.parseFloat(String.valueOf(expenditure.getAmount()));
            response.setAmount(total);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public ExpenditureResponseDto getExpenditureByPeriod(LocalDateTime dateFrom, LocalDateTime dateTo) {

        ExpenditureResponseDto response = new ExpenditureResponseDto();
        float total = 0f;

        List<Expenditure> expenditureList = expenditureRepository.findByCreatedAtBetween(dateFrom, dateTo);
        for (Expenditure expenditure : expenditureList) {

            total = total + Float.parseFloat(String.valueOf(expenditure.getAmount()));

        }
        response.setAmount(total);
        response.setYear(0);
        response.setMonth("Period");
        response.setCategory("Period");

        return response;
    }

    @Transactional(value = "transactionManager")
    public List<Object[]> getExpenditureSumsByMonth(int year) {
        return expenditureRepository.sumExpenditureByMonth(year);
    }
    @Transactional(value = "transactionManager")
    public List<POSBalanceSheetDto> compareBudgetWithExpenditure(int year) {

        List<POSBalanceSheetDto> posBalanceSheetList = new ArrayList<>();

        List<Object[]> expenditureDtoList = getExpenditureSumsByMonth(year);
        List<Budget> budgetList = budgetService.getBudgetByYear(year);

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
