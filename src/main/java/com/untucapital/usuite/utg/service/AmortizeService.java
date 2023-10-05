package com.untucapital.usuite.utg.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Transactional
@Service
public class AmortizeService {

    private final MusoniService musoniService;

    public AmortizeService(MusoniService musoniService) {
        this.musoniService = musoniService;
    }

    public String getTable(String loanId, String periodRange) throws JSONException {

        System.out.println("LOAN ID IS: "+loanId);

        String loan = musoniService.getLoanId(loanId);

        JSONObject jsonObject = new JSONObject(loan);

        String accountNo = jsonObject.getString("accountNo");
        int clientId = jsonObject.getInt("clientId");
        String clientName = jsonObject.getString("clientName");
        String loanProductName = jsonObject.getString("loanProductName");
        String loanOfficerName = jsonObject.getString("loanOfficerName");
        double principal = jsonObject.getDouble("principal");
        int numberOfRepayments = jsonObject.getInt("numberOfRepayments");
        double interestRatePerPeriod = jsonObject.getDouble("interestRatePerPeriod");

        JSONArray actualDisbursementDateArray = jsonObject.getJSONObject("timeline").getJSONArray("actualDisbursementDate");
        int actualDisbursementYear = actualDisbursementDateArray.getInt(0);
        int actualDisbursementMonth = actualDisbursementDateArray.getInt(1);
        int actualDisbursementDay = actualDisbursementDateArray.getInt(2);
        LocalDate actualDisbursementDate = LocalDate.of(actualDisbursementYear, actualDisbursementMonth, actualDisbursementDay);

        JSONArray expectedMaturityDateArray = jsonObject.getJSONObject("timeline").getJSONArray("expectedMaturityDate");
        int expectedMaturityYear = expectedMaturityDateArray.getInt(0);
        int expectedMaturityMonth = expectedMaturityDateArray.getInt(1);
        int expectedMaturityDay = expectedMaturityDateArray.getInt(2);
        LocalDate expectedMaturityDate = LocalDate.of(expectedMaturityYear, expectedMaturityMonth, expectedMaturityDay);

        System.out.println("Account Number: " + accountNo);
        System.out.println("Client ID: " + clientId);
        System.out.println("Client Name: " + clientName);
        System.out.println("Loan Product Name: " + loanProductName);
        System.out.println("Loan Officer Name: " + loanOfficerName);
        System.out.println("Principal: " + principal);
        System.out.println("Number of Repayments: " + numberOfRepayments);
        System.out.println("Interest Rate per Period: " + interestRatePerPeriod);
        System.out.println("Actual Disbursement Date: " + actualDisbursementDate);
        System.out.println("Expected Maturity Date: " + expectedMaturityDate);

//        COLLECT VALUES FROM LOAN..

        int n = numberOfRepayments; // Number of periods
        double i = interestRatePerPeriod; // Interest per year
        double pv = principal; // Present Value
        double fv = 0; // Future Value

        double pmt = pv * (i / 100) / (1 - Math.pow(1 + (i / 100), -n));
        double remainingBalance = pv;
        double totalInterest = 0;

        JsonArray tableArray = new JsonArray();


        LocalDate nextMonthDate = actualDisbursementDate.plusMonths(1);
        int qw = 24;
        double interest = 0.0;
        for (int period = 1; period <= n; period++) {
            interest = remainingBalance * (i / 100);
            principal = pmt - interest;
            remainingBalance -= principal;
            totalInterest += interest;


            JsonObject periodObject = new JsonObject();
            periodObject.addProperty("Period", period);
            periodObject.addProperty("month", nextMonthDate.toString());
            periodObject.addProperty("month_int", qw);
            periodObject.addProperty("PV", pv);
            periodObject.addProperty("PMT", pmt);
            periodObject.addProperty("Interest", interest);
            periodObject.addProperty("FV", fv);

            tableArray.add(periodObject);

            pv = remainingBalance;
            nextMonthDate = nextMonthDate.plusMonths(1);
            qw++;
            System.out.println(nextMonthDate);
        }

        JsonObject resultObject = new JsonObject();
        resultObject.add("AmortizationTable", tableArray);
        resultObject.addProperty("TotalInterest", totalInterest);
        resultObject.addProperty("PeriodInterest1", installmentCalc(String.valueOf(nextMonthDate), periodRange, principal, interest));

        Gson gson = new Gson();
        String json = gson.toJson(resultObject);

        System.out.println(installmentCalc(String.valueOf(nextMonthDate), periodRange, principal, interest));

        return json;
    }


    public double installmentCalc(String nextMonthDate, String periodRange, double principal, double interest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate disbursementDate = LocalDate.parse(nextMonthDate, formatter);
        LocalDate currentDate = LocalDate.now();

        double balanceToBeCleared = 0.0;
        if (LocalDate.parse(periodRange, formatter).isAfter(disbursementDate) ||
                LocalDate.parse(periodRange, formatter).isEqual(disbursementDate)) {
            long numOfDaysDifference = ChronoUnit.DAYS.between(disbursementDate, currentDate);

            double interestForNextInstalment = interest;
            double numOfDaysFromSystem = 30.417;
            double interestPerDay = interestForNextInstalment / numOfDaysFromSystem;
            double accruedInterest = numOfDaysDifference * interestPerDay;
            double principalBalance = principal;
            double totalDueAsOfToday = accruedInterest + principalBalance;
            double settlementAccountBalance = 0.0; // Assuming no value is available
            balanceToBeCleared = totalDueAsOfToday;

            System.out.println("Abel");
            System.out.println("A\tInterest for Next Instalment\t" + interestForNextInstalment);
            System.out.println("B\t# of Days (from System)\t" + numOfDaysFromSystem);
            System.out.println("C\tInterest per Day\t" + interestPerDay);
            System.out.println("D\t# of Days to TODAY\t" + numOfDaysDifference);
            System.out.println("E\tAccrued Interest\t" + accruedInterest);
            System.out.println("F\tPrincipal Balance\t" + principalBalance);
            System.out.println("G\tTotal Due as at today\t" + totalDueAsOfToday);
            System.out.println("H\tSettlement Account Balance\t" + settlementAccountBalance);
            System.out.println("I\tBALANCE TO BE CLEARED\t" + balanceToBeCleared);

            return balanceToBeCleared;
        }  else {
            return balanceToBeCleared;
        }

    }

    public String getLoanInterest(String rangeStart, String rangeEnd, String periodRange) {
        JsonArray resultArray = new JsonArray();

        for (int i = Integer.parseInt(rangeStart); i <= Integer.parseInt(rangeEnd); i++) {
            com.google.gson.JsonObject loanObject = new com.google.gson.JsonObject();

            try {
                String loan = musoniService.getLoanId(String.valueOf(i));

                org.springframework.boot.configurationprocessor.json.JSONObject jsonObject = new org.springframework.boot.configurationprocessor.json.JSONObject(loan);

                org.springframework.boot.configurationprocessor.json.JSONArray actualDisbursementDateArray = jsonObject.getJSONObject("timeline").getJSONArray("actualDisbursementDate");
                int actualDisbursementYear = actualDisbursementDateArray.getInt(0);
                int actualDisbursementMonth = actualDisbursementDateArray.getInt(1);
                int actualDisbursementDay = actualDisbursementDateArray.getInt(2);
                LocalDate actualDisbursementDate = LocalDate.of(actualDisbursementYear, actualDisbursementMonth, actualDisbursementDay);

                org.springframework.boot.configurationprocessor.json.JSONArray expectedMaturityDateArray = jsonObject.getJSONObject("timeline").getJSONArray("expectedMaturityDate");
                int expectedMaturityYear = expectedMaturityDateArray.getInt(0);
                int expectedMaturityMonth = expectedMaturityDateArray.getInt(1);
                int expectedMaturityDay = expectedMaturityDateArray.getInt(2);
                LocalDate expectedMaturityDate = LocalDate.of(expectedMaturityYear, expectedMaturityMonth, expectedMaturityDay);



                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                if (LocalDate.parse(periodRange, formatter).isAfter(actualDisbursementDate.plusMonths(1)) ||
                        LocalDate.parse(periodRange, formatter).isEqual(actualDisbursementDate.plusMonths(1))) {
                    // Extracting the required values
                    loanObject.addProperty("accountNo", jsonObject.getString("accountNo"));
                    loanObject.addProperty("status", jsonObject.getJSONObject("status").getString("active"));
                    loanObject.addProperty("clientId", jsonObject.getInt("clientId"));
                    loanObject.addProperty("clientName", jsonObject.getString("clientName"));
                    loanObject.addProperty("loanProductName", jsonObject.getString("loanProductName"));
                    loanObject.addProperty("loanOfficerName", jsonObject.getString("loanOfficerName"));
                    loanObject.addProperty("principal", jsonObject.getDouble("principal"));
                    loanObject.addProperty("numberOfRepayments", jsonObject.getInt("numberOfRepayments"));
                    loanObject.addProperty("interestRatePerPeriod", jsonObject.getDouble("interestRatePerPeriod"));
                    loanObject.addProperty("actualDisbursementDate", actualDisbursementDate.toString());
                    loanObject.addProperty("expectedMaturityDate", expectedMaturityDate.toString());

                    int n = jsonObject.getInt("numberOfRepayments"); // Number of periods
                    double in = jsonObject.getDouble("interestRatePerPeriod"); // Interest per year
                    double pv = jsonObject.getDouble("principal"); // Present Value
                    double fv = 0; // Future Value
                    double pmt = pv * (in / 100) / (1 - Math.pow(1 + (in / 100), -n));
                    double remainingBalance = pv;
                    double totalInterest = 0;
                    JsonArray tableArray = new JsonArray();

                    LocalDate nextMonthDate = actualDisbursementDate.plusMonths(1);
                    double interest = 0.0;
                    double principal = 0.0;
                    for (int period = 1; period <= n; period++) {
                        interest = remainingBalance * (in / 100);
                        principal = pmt - interest;
                        remainingBalance -= principal;
                        totalInterest += interest;

                        JsonObject periodObject = new JsonObject();
                        periodObject.addProperty("Period", period);
                        periodObject.addProperty("month", nextMonthDate.toString());
                        periodObject.addProperty("PV", pv);
                        periodObject.addProperty("PMT", pmt);
                        periodObject.addProperty("Interest", interest);
                        periodObject.addProperty("FV", fv);
                        tableArray.add(periodObject);
                        pv = remainingBalance;

                        nextMonthDate = nextMonthDate.plusMonths(1);
                        System.out.println(nextMonthDate);
                    }
                    JsonObject resultObject = new JsonObject();
                    loanObject.add("AmortizationTable", tableArray);
                    loanObject.addProperty("TotalInterest", totalInterest);
                    loanObject.addProperty("PeriodInterest2", installmentCalc(String.valueOf(nextMonthDate), periodRange, principal, interest));

                    System.out.println(installmentCalc(String.valueOf(nextMonthDate), periodRange, principal, interest));

                    resultArray.add(loanObject);

                }






            } catch (org.springframework.boot.configurationprocessor.json.JSONException e) {
                // Handle exception if needed
            }
        }
        System.out.println("\n##################################################################\n"+resultArray);

        JsonObject resultObject = new JsonObject();
        resultObject.add("InterestsTable", resultArray);

        Gson gson = new Gson();
        String json = gson.toJson(resultObject);

        return json;
    }

    public String getLoansDisbursedByDate(String rangeStart, String rangeEnd, String periodRange) throws JSONException {
        JsonArray resultArray = new JsonArray();

        List<Integer> range = musoniService.disbursedLoansByDate(rangeStart, rangeEnd);

        for (int i : range) {
            com.google.gson.JsonObject loanObject = new com.google.gson.JsonObject();

            try {
                String loan = musoniService.getLoanId(String.valueOf(i));

                org.springframework.boot.configurationprocessor.json.JSONObject jsonObject = new org.springframework.boot.configurationprocessor.json.JSONObject(loan);

                org.springframework.boot.configurationprocessor.json.JSONArray actualDisbursementDateArray = jsonObject.getJSONObject("timeline").getJSONArray("actualDisbursementDate");
                int actualDisbursementYear = actualDisbursementDateArray.getInt(0);
                int actualDisbursementMonth = actualDisbursementDateArray.getInt(1);
                int actualDisbursementDay = actualDisbursementDateArray.getInt(2);
                LocalDate actualDisbursementDate = LocalDate.of(actualDisbursementYear, actualDisbursementMonth, actualDisbursementDay);

                org.springframework.boot.configurationprocessor.json.JSONArray expectedMaturityDateArray = jsonObject.getJSONObject("timeline").getJSONArray("expectedMaturityDate");
                int expectedMaturityYear = expectedMaturityDateArray.getInt(0);
                int expectedMaturityMonth = expectedMaturityDateArray.getInt(1);
                int expectedMaturityDay = expectedMaturityDateArray.getInt(2);
                LocalDate expectedMaturityDate = LocalDate.of(expectedMaturityYear, expectedMaturityMonth, expectedMaturityDay);

                // Extracting the required values
                double periodInter = 0.0;
                if (jsonObject.getJSONObject("status").getString("active") == "true") {
                        loanObject.addProperty("accountNo", jsonObject.getString("accountNo"));
                        loanObject.addProperty("status", jsonObject.getJSONObject("status").getString("active"));
                        loanObject.addProperty("clientId", jsonObject.getInt("clientId"));
                        loanObject.addProperty("clientName", jsonObject.getString("clientName"));
                        loanObject.addProperty("loanProductName", jsonObject.getString("loanProductName"));
                        loanObject.addProperty("loanOfficerName", jsonObject.getString("loanOfficerName"));
                        loanObject.addProperty("principal", jsonObject.getDouble("principal"));
                        loanObject.addProperty("numberOfRepayments", jsonObject.getInt("numberOfRepayments"));
                        loanObject.addProperty("interestRatePerPeriod", jsonObject.getDouble("interestRatePerPeriod"));
                        loanObject.addProperty("actualDisbursementDate", actualDisbursementDate.toString());
                        loanObject.addProperty("expectedMaturityDate", expectedMaturityDate.toString());

                        int n = jsonObject.getInt("numberOfRepayments"); // Number of periods
                        double in = jsonObject.getDouble("interestRatePerPeriod"); // Interest per year
                        double pv = jsonObject.getDouble("principal"); // Present Value
                        double fv = 0; // Future Value
                        double pmt = pv * (in / 100) / (1 - Math.pow(1 + (in / 100), -n));
                        double remainingBalance = pv;
                        double totalInterest = 0;
                        JsonArray tableArray = new JsonArray();

                        LocalDate nextMonthDate = actualDisbursementDate.plusMonths(1);
                        double interest = 0.0;
                        double principal = 0.0;

                        double lastNonZeroPeriodInterest = 0.0;
                        for (int period = 1; period <= n; period++) {
                            interest = remainingBalance * (in / 100);
                            principal = pmt - interest;
                            remainingBalance -= principal;
                            totalInterest += interest;

                            JsonObject periodObject = new JsonObject();
                            periodObject.addProperty("Period", period);
                            periodObject.addProperty("month", nextMonthDate.toString());
                            periodObject.addProperty("PV", pv);
                            periodObject.addProperty("PMT", pmt);
                            periodObject.addProperty("Interest", interest);
                            periodObject.addProperty("FV", fv);
                            double periodInterest3 = installmentCalc(String.valueOf(nextMonthDate), periodRange, principal, interest);
                            periodObject.addProperty("PeriodInterest3", periodInterest3);
                            tableArray.add(periodObject);
                            pv = remainingBalance;

                            if (periodInterest3 != 0.0) {
                                lastNonZeroPeriodInterest = periodInterest3;
                            }

                            nextMonthDate = nextMonthDate.plusMonths(1);
                        }

                        loanObject.add("AmortizationTable", tableArray);
                        loanObject.addProperty("TotalInterest", totalInterest);

                        loanObject.add("AmortizationTable", tableArray);
                        loanObject.addProperty("TotalInterest", totalInterest);
                        loanObject.addProperty("PeriodInterest4", lastNonZeroPeriodInterest);

                        System.out.println(lastNonZeroPeriodInterest);

                        System.out.println("####### ************** ############### ************* $$$$$$$$$$$$$$$");
                        System.out.println(installmentCalc(String.valueOf(nextMonthDate), periodRange, principal, interest));
                        resultArray.add(loanObject);
                    }

            } catch (org.springframework.boot.configurationprocessor.json.JSONException e) {
                // Handle exception if needed
            }
        }

        System.out.println("\n##################################################################\n"+range);

        JsonObject resultObject = new JsonObject();
        resultObject.add("InterestsTable", resultArray);

        Gson gson = new Gson();
        String json = gson.toJson(resultObject);





        return json;
    }

}

