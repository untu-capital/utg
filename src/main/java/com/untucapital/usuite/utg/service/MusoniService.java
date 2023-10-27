package com.untucapital.usuite.utg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.untucapital.usuite.utg.DTO.Currency;
import com.untucapital.usuite.utg.DTO.DisbursedLoans;
import com.untucapital.usuite.utg.DTO.*;
import com.untucapital.usuite.utg.DTO.client.Client;
import com.untucapital.usuite.utg.DTO.loans.RepaymentSchedule;
import com.untucapital.usuite.utg.DTO.loans.*;
import com.untucapital.usuite.utg.DTO.loans.Result;
import com.untucapital.usuite.utg.client.RestClient;
import com.untucapital.usuite.utg.entity.PostGl;
import com.untucapital.usuite.utg.model.MusoniClient;
import com.untucapital.usuite.utg.model.transactions.Loans;
import com.untucapital.usuite.utg.model.transactions.PageItem;
import com.untucapital.usuite.utg.model.transactions.Transactions;
import com.untucapital.usuite.utg.processor.MusoniProcessor;
import com.untucapital.usuite.utg.repository.MusoniRepository;
import com.untucapital.usuite.utg.utils.MusoniUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
@Data
@RequiredArgsConstructor
@Configuration
public class MusoniService {
    @Autowired
    private static final String DB_URL = "jdbc:mysql://localhost:3306/u-tran-gateway-db?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false";
    @Autowired
    private static final String USER = "tranGatewayAdmin";
    @Autowired
    private static final String PASS = "u92uCuwte9@ta";

    @Value("${musoni.url}")
    private String musoniUrl;
    @Value("${musoni.username}")
    private String musoniUsername;
    @Value("${musoni.password}")
    private String musoniPassword;
    @Value("${musoni.X_FINERACT_PLATFORM_TENANTID}")
    private String musoniTenantId;
    @Value("${musoni.X_API_KEY}")
    private String musoniApiKey;

    @Autowired
    private final RestTemplate restTemplate;

    private final RestClient restClient;

    private final MusoniProcessor musoniProcessor;

    private final PostGlService postGlService;

    private static final Logger log = LoggerFactory.getLogger(RoleService.class);


    @Autowired
    SmsService smsService;

    @Autowired
    MusoniRepository musoniRepository;

    @Transactional(value = "transactionManager")
    public void save(MusoniClient musoniClient) {
        musoniRepository.save(musoniClient);
    }

    @Transactional(value = "transactionManager")
    public MusoniClient getMusoniClientById(String clientId) {
        return musoniRepository.findMusoniClientById(clientId);
    }

    @Transactional(value = "transactionManager")
    public List<MusoniClient> getMusoniClientsByStatus(String status) {
        return musoniRepository.findMusoniClientsByStatus(status);
    }


//    @Scheduled(cron = "0 0 5 * * ?")
    public void getLoansByTimestamp() throws ParseException, JsonProcessingException, AccountNotFoundException {

        Long timestamp = MusoniUtils.getUnixTimeMinus24Hours();
        Loans loans = restClient.getLoans(timestamp);
        log.info("Loans from Musoni : {}", loans.toString());

        List<Transactions> transactions = new ArrayList<Transactions>();
        List<PostGl> postGlList = new ArrayList<>();
        List<PostGl> postGlListLB = new ArrayList<>();


        List<PageItem> pageItemList = loans.getPageItems();

        for (PageItem pageItem : pageItemList) {
            int loanId = pageItem.getId();

            //Get all transactions for the pageItem
            transactions = restClient.getTransactions(loanId);

            if (transactions == null) {
                return;
            }

            log.info("Transactions with Repayment or Disbursement: {}", transactions.toString());

//            transactionInfoList = musoniProcessor.createPastelTransaction(transactions);
            postGlList = musoniProcessor.setPostGlFields(transactions);
            postGlListLB = musoniProcessor.setPostGlClientLoanBook(transactions);

            int maxIterations = Math.max(postGlList.size(), postGlListLB.size());

            for (int i = 0; i < maxIterations; i++) {
                if (i < postGlList.size()) {

                    postGlService.savePostGl(postGlList.get(i));
                    log.info("PostGl transaction saved in the database: {}", postGlList.get(i).toString());

                }

                if (i < postGlListLB.size()) {

                    postGlService.savePostGl(postGlListLB.get(i));
                    log.info(" PostGl LB transaction saved in the database: {}", postGlListLB.get(i).toString());
                }
            }


        }
    }

    //    ToDo: Get Required information from the loan returned
    public String repaymentSchedule(String phone_number, String loanAccount) throws ParseException {

        RepaymentScheduleLoan repaymentScheduleLoan = restClient.getRepaymentSchedule(loanAccount);

        RepaymentScheduleDTO repaymentScheduleDTO = new RepaymentScheduleDTO();
        RepaymentSchedule repaymentSchedule = repaymentScheduleLoan.getRepaymentSchedule();
        List<Period> periods = repaymentSchedule.getPeriods();

        int totalPeriods = periods.size();

        log.info("#################### START #############################");
        log.info("Total Repayment Periods: " + totalPeriods);
        log.info("#################### END   #############################");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy,MM,dd", Locale.ENGLISH);
        LocalDate currentDate = LocalDate.now();

        for (int period = 1; period < totalPeriods; period++) {

            Period periodData = periods.get(period);
            int[] dueDateArray = periodData.getDueDate();

            LocalDate dueDate = LocalDate.of(dueDateArray[0], dueDateArray[1], dueDateArray[2]);

            if (dueDate.minusDays(7).isEqual(currentDate)) {
                log.info("Next Repayment date is: " + dueDate);
                smsService.sendSingle(phone_number, "Please be reminded that your next repayment date is: " + dueDate + "\n\nNote: Ignore this message if you've already made your payment.");
                break;
            }
        }

        // If no matching repayment date found, inform the user.
        log.info("You have no repayment date.");

        // System.out.println(repaymentScheduleDTO);
        return "<b>Loan Account: " + loanAccount +
                "\nNext Repayment Date: " + repaymentScheduleDTO.getDueDate();

    }


    List<String> timestampedLoanAccs = new ArrayList<>();

//    @Scheduled(fixedRate = 300000)
    public String transactionSmsScheduler() throws SQLException {

//        Class.forName("com.mysql.jdbc.Driver");
        Connection connTrans = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = connTrans.createStatement();
        ResultSet searchTranId = stmt.executeQuery("SELECT trans_id FROM sms_notification ORDER BY trans_id DESC LIMIT 1");
//        int transIds = searchTranId.getInt("trans_id");
        int transIds = 0;
        while (searchTranId.next()) {
            transIds = searchTranId.getInt("trans_id");
        }

        log.info("################## print this : " + transIds);

        Loans loans = restClient.getTimestampedLoanAcc();

        timestampedLoanAccs.clear();

        for (int i = 0; i < loans.getPageItems().size(); i++) {
            String loan_id = String.valueOf(loans.getPageItems().get(i).getId());
            String status = String.valueOf(loans.getPageItems().get(i).getStatus().isActive());
            String client_id = String.valueOf(loans.getPageItems().get(i).getClientId());

            String days_in_arrears = null;
            if (loans.getPageItems().get(i).getSummary() != null) {
                if (loans.getPageItems().get(i).getSummary().getDaysInArrears() != null) {
                    days_in_arrears = String.valueOf(loans.getPageItems().get(i).getSummary().getDaysInArrears());
                }
            }


            Client client = restClient.getClientById(client_id);
            String phone_number = "0";
            if (client.getMobileNo() != null) {
                phone_number = client.getMobileNo();
                phone_number = "0775797299";
            }

            System.out.println(phone_number);

            disbursementSms(loan_id, phone_number, MusoniUtils.getTimestamp(), transIds);

            repaymentSms(loan_id, phone_number, MusoniUtils.getTimestamp(), transIds);

//            repayment

            StringBuilder menu = new StringBuilder("");
            String timestampObject = menu.append("{")
                    .append("\"loanId\"").append(" : ").append(loan_id).append(",")
                    .append("\"statusActive\"").append(" : ").append(status).append(",")
                    .append("\"daysInArrears\"").append(" : ").append(days_in_arrears).append(",")
                    .append("\"clientId\"").append(" : ").append(client_id).append(",")
                    .append("\"phoneNumber\"").append(" : ").append(phone_number)
                    .append("}").toString();
            System.out.println(loan_id);

            timestampedLoanAccs.add(timestampObject);
        }
        return timestampedLoanAccs.toString();
    }


    //          SELECT LOAN IDS FROM TABLE AND MATCH WITH TRANSACTION IDS
    @Transactional(value = "transactionManager")
    public String disbursementSms(String loanId, String phone_number, Long unixTimestamp, int transIds) {

        for (int transId = transIds; transId <= transIds + 10; transId++) {
            try {

                log.info("This is before Musoni Transactions Query- Disbursements!");

                LoanTransaction loanTransaction = restClient.getByLoanIdAndTransactionId(loanId, transId);


                if (loanTransaction != null) {

                    log.info("trans id exists for this trans");

                    String loanTrans = loanTransaction.getType().getValue();
                    String loanTransAmount = String.valueOf(loanTransaction.getAmount());

                    if (loanTrans.isEmpty()) {
                        log.info("Loan with Transaction not found!");
                    } else if (loanTrans != "") {

                        //                        GET TRANSACTION TYPE
                        String transactionType = loanTransaction.getType().getValue();
                        log.info("GET TRANSACTION TYPE:{}", transactionType);
                        log.info("YOUR DIS NUMBER: " + phone_number);

//                        GET TRANSACTION DATE
                        String transDate = MusoniUtils.getTransDate(loanTransaction);
                        log.info("YOUR TRANSACTION DATE IS: " + transDate);


//                        GET TRACTIONACTION AMOUNT
                        Double amount = loanTransaction.getAmount();
                        log.info("AMOUNT: " + amount);

//                        GET TRANSACTION CODE
                        Currency currency = loanTransaction.getCurrency();
                        String currencyCode = currency.getCode();
                        log.info("CURRENCY: " + currencyCode);
                        log.info("Loan ID is: " + loanId + "\n And transaction ID is: " + transId);


//                        FOR DISBURSMENT TRANSACTION
                        if (transactionType.equals("Disbursement")) {
                            log.info("Transaction type: " + transactionType);

                            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                            Statement stmt1 = conn.createStatement();

                            ResultSet searchTransId = stmt1.executeQuery("SELECT * FROM sms_notification WHERE trans_id = " + transId);

                            List<Integer> transIdArray = new ArrayList<Integer>();
                            while (searchTransId.next()) {
                                transIdArray.add(searchTransId.getInt("trans_id"));
                            }
                            List<Integer> transArray = transIdArray;

                            if (transArray.contains(transId)) {
                                log.info("Sms Already Send" + transId);
                            } else {

//                        Todo: SET SEND SMS HERE...
                                log.info("Disbursement message has been sent..");
                                String sms_disburse = "This serves to confirm that a loan amount of " + MusoniUtils.currencyFormatter(new BigDecimal(loanTransAmount)) + " has been disbursed to Account: " + loanId + " on " + transDate + " and has been collected.";
                                smsService.sendSingle(phone_number, sms_disburse);

                                //  INSERT TRANSACTION DETAILS INTO DATABASE
                                log.info("Inserting SMS records into the table...");
                                String sql = "INSERT INTO `sms_notification` (`trans_id`, `loan_id`, `description`, `phone_number`, `unix_timestamp`) VALUES (" + transId + ", '" + loanId + "', '" + transactionType + "', '" + phone_number + "', '" + unixTimestamp + "')";
                                stmt1.executeUpdate(sql);
                                log.info("" + transId);
                            }
                            searchTransId.close();
                        }
                    }
                } else {
                    log.info("else part in disbursement");
                }

            } catch (HttpClientErrorException | ParseException | SQLException e) {
                e.getMessage();
            }


        }
        // EXIT WHILE LOOP

        return "";
    }

    @Transactional(value = "transactionManager")
    public String repaymentSms(String loanId, String phone_number, Long unixTimestamp, int transIds) {

        for (int transId = transIds; transId <= transIds + 5; transId++) {
            try {
//            URL url = new URL("http://localhost:7878/api/utg/musoni/getTransations/loanid/" + loanId +"/transactionId/"+ transId);
                LoanTransaction transaction = restClient.getByLoanIdAndTransactionId(loanId, transId);

                log.info("This is before Musoni Transactions Query - Repayments!");
                if (transaction.getId() != 0) {

                    log.info("trans id exists for this trans");

                    LoanTransaction loanTransaction = restClient.getByLoanIdAndTransactionId(loanId, transId);

                    String loanTrans = loanTransaction.getType().getValue();
                    String loanTransAmount = String.valueOf(loanTransaction.getAmount());

                    if (loanTrans.equals("")) {
                        log.info("Loan with Transaction not found!");
                    } else if (loanTrans != "") {

                        String transactionType = loanTransaction.getType().getValue();
                        log.info("GET TRANSACTION TYPE:{}", transactionType);
                        log.info("YOUR REP NUMBER: " + phone_number);

//                        GET TRANSACTION DATE
                        String transDate = MusoniUtils.getTransDate(loanTransaction);
                        log.info("YOUR TRANSACTION DATE IS: " + transDate);

//                        GET TRACTIONACTION AMOUNT
                        Double amount = loanTransaction.getAmount();
                        log.info("AMOUNT: " + amount);

//                        GET TRANSACTION CODE
                        Currency currency = loanTransaction.getCurrency();
                        String currencyCode = currency.getCode();
                        log.info("CURRENCY: " + currencyCode);
                        log.info("Loan ID is: " + loanId + "\n And transaction ID is: " + transId);

//                        FOR DISBURSMENT TRANSACTION
                        if (transactionType.equals("Repayment")) {
                            System.out.println(transactionType);

                            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                            Statement stmt1 = conn.createStatement();

                            ResultSet searchTransId = stmt1.executeQuery("SELECT * FROM sms_notification WHERE trans_id = " + transId);

                            List<Integer> transIdArray = new ArrayList<Integer>();
                            while (searchTransId.next()) {
                                transIdArray.add(searchTransId.getInt("trans_id"));
                            }
                            List<Integer> transArray = transIdArray;

                            if (transArray.contains(transId)) {
                                System.out.println("Sms Already Send");
                                System.out.println(transId);
                            } else {

//                        Todo: SET SEND SMS HERE...
//                        System.out.println("Repayment message has been sent..");
                                String sms_repayment = "This serves to confirm that a repayment of " + MusoniUtils.currencyFormatter(new BigDecimal(loanTransAmount)) + " has been made to Account: " + loanId + " on " + transDate;
                                smsService.sendSingle(phone_number, sms_repayment);

                                //  INSERT TRANSACTION DETAILS INTO DATABASE
                                System.out.println("Inserting SMS records into the table...");
                                String sql = "INSERT INTO `sms_notification` (`trans_id`, `loan_id`, `description`, `phone_number`, `unix_timestamp`) VALUES (" + transId + ", '" + loanId + "', '" + transactionType + "', '" + phone_number + "', '" + unixTimestamp + "')";
                                stmt1.executeUpdate(sql);
//                                stmt1.close();
                                System.out.println(transId);
                            }
                            searchTransId.close();
                        }
                    }

                } else {
                    System.out.println("else part in repayment");
                }

            } catch (HttpClientErrorException | ParseException | SQLException e) {
                e.getMessage();
            }


        }
        // EXIT WHILE LOOP


        return "";
    }

//    public String disbursedLoans(String fromDate, String toDate) throws JSONException {
//        JSONObject json = new JSONObject(getLoansByDisbursementDate(fromDate, toDate));
//        JSONArray pageItems = json.getJSONArray("pageItems");
//        JSONArray disbursedLoans = new JSONArray();
//
//        for (int i = 0; i < pageItems.length(); i++) {
//            JSONObject page = pageItems.getJSONObject(i);
//
//            // Check if Loan_id exists
//            int loanId = page.getInt("id");
//            String accountNo = page.getString("accountNo");
//            String clientName = page.getString("clientName");
//            String loanProductName = page.getString("loanProductName");
//            double principal = page.getDouble("principal");
//            int numberOfRepayments = page.getInt("numberOfRepayments");
//            double interestRatePerPeriod = page.getDouble("interestRatePerPeriod");
//            String expectedMaturityDate = page.getJSONObject("timeline")
//                    .getString("expectedMaturityDate");
//
//            String actualDisbursementDate = null;
//            if (page.getJSONObject("timeline").has("actualDisbursementDate")) {
//                actualDisbursementDate = page.getJSONObject("timeline")
//                        .getString("actualDisbursementDate");
//            }
//
//            double totalExpectedRepayment = 0.0;
//            double totalRepayment = 0.0;
//            double totalOutstanding = 0.0;
//
//            if (page.has("summary")) {
//                totalExpectedRepayment = page.getJSONObject("summary")
//                        .getDouble("totalExpectedRepayment");
//            }
//
//            if (page.has("summary")) {
//                totalRepayment = page.getJSONObject("summary")
//                        .getDouble("totalRepayment");
//            }
//
//            if (page.has("summary")) {
//                totalOutstanding = page.getJSONObject("summary")
//                        .getDouble("totalOutstanding");
//            }
//
//            String officeName = page.getString("officeName");
//            String loanOfficerName = page.getString("loanOfficerName");
//
//            JSONObject loanData = new JSONObject();
//            if (actualDisbursementDate != null) {
//                loanData.put("actualDisbursementDate", actualDisbursementDate);
//            }
////            loanData.put("actualDisbursementDate", page.getJSONObject("timeline").getString("actualDisbursementDate"));
//            loanData.put("accountNo", accountNo);
//            loanData.put("clientName", clientName);
//            loanData.put("loanProductName", loanProductName);
//            loanData.put("principal", principal);
//            loanData.put("numberOfRepayments", numberOfRepayments);
//            loanData.put("interestRatePerPeriod", interestRatePerPeriod);
//            loanData.put("expectedMaturityDate", expectedMaturityDate);
//            loanData.put("totalExpectedRepayment", totalExpectedRepayment);
//            loanData.put("totalRepayment", totalRepayment);
//            loanData.put("totalOutstanding", totalOutstanding);
//            loanData.put("officeName", officeName);
//            loanData.put("loanOfficerName", loanOfficerName);
//
//            disbursedLoans.put(loanData);
//        }
//
//        // Convert the disbursedLoans JSONArray to a JSON object
//        JSONObject result = new JSONObject();
//        result.put("disbursedLoans", disbursedLoans);
//
//        return result.toString();
//    }
//

    public Result disbursedLoans(String fromDate, String toDate) {
        Loans loans = restClient.getLoansByDisbursementDate(fromDate, toDate);
        List<PageItem> pageItems = loans.getPageItems();

        List<com.untucapital.usuite.utg.DTO.loans.DisbursedLoans> disbursedLoansList =
                new ArrayList<com.untucapital.usuite.utg.DTO.loans.DisbursedLoans>();

        // Store the total principals for each month
        Map<String, Double> monthlyTotals = new HashMap<>();

        for (int i = 0; i < pageItems.size(); i++) {
            PageItem page = pageItems.get(i);

            com.untucapital.usuite.utg.DTO.loans.DisbursedLoans disbursedLoans = new com.untucapital.usuite.utg.DTO.loans.DisbursedLoans();

            // Your existing code...
            // Check if Loan_id exists
            int loanId = page.getId();
            double principal = page.getPrincipal();


            String actualDisbursementDate = null;
            if (page.getTimeline().getActualDisbursementDate() != null) {
                actualDisbursementDate = page.getTimeline().getActualDisbursementDate().toString();

            }

            double totalExpectedRepayment = 0.0;
            double totalRepayment = 0.0;
            double totalOutstanding = 0.0;

            if (page.getSummary() != null) {
                totalExpectedRepayment = Double.parseDouble(String.valueOf(page.getSummary().getTotalExpectedRepayment()));
                totalRepayment = Double.parseDouble(String.valueOf(page.getSummary().getTotalRepayment()));
                totalOutstanding = Double.parseDouble(String.valueOf(page.getSummary().getTotalOutstanding()));
            }


            String officeName = page.getOfficeName();
            String loanOfficerName = page.getLoanOfficerName();

            LoanData loanData = new LoanData();

            // Calculate the month from the actual disbursement date
            double monthlyTotal = 0.0;
            String month = "";
            if (actualDisbursementDate != null) {
                month = actualDisbursementDate.substring(0, 7); // Extract "yyyy-MM"
                monthlyTotal = monthlyTotals.getOrDefault(month, 0.0);
                monthlyTotal += principal;
                monthlyTotals.put(month, monthlyTotal);
            }

            // Your existing code...
            loanData.setLoanOfficerName(loanOfficerName);
            loanData.setAccountNo(page.getAccountNo());
            loanData.setClientName(page.getClientName());
            loanData.setLoanProductName(page.getLoanProductName());
            loanData.setActualDisbursementDate(actualDisbursementDate);
            loanData.setPrincipal(principal);
            loanData.setOfficeName(officeName);
            loanData.setExpectedMaturityDate(String.valueOf(page.getTimeline().getExpectedMaturityDate()));
            loanData.setNumberOfRepayments(page.getNumberOfRepayments());
            loanData.setTotalOutstanding(totalOutstanding);
            loanData.setInterestRatePerPeriod(page.getInterestRatePerPeriod());
            loanData.setTotalExpectedRepayment(totalExpectedRepayment);
            loanData.setTotalRepayment(totalRepayment);
            loanData.setMonthlyTotal(monthlyTotal);

            disbursedLoans.setLoanData(loanData);
            disbursedLoansList.add(disbursedLoans);
        }

        // Convert the monthlyTotals map to an ArrayList of monthly totals
        List<Double> monthlyPrincipalTotals = new ArrayList<>();
        for (Map.Entry<String, Double> entry : monthlyTotals.entrySet()) {
            monthlyPrincipalTotals.add(entry.getValue());
        }

        // Add the monthly totals to the result JSON
        Result result = new Result();
        result.setDisbursedLoans(disbursedLoansList);
        result.setMonthlyPrincipalTotals(monthlyPrincipalTotals);

        return result;
    }


    public List<Integer> disbursedLoansByDate(String fromDate, String toDate) {
        Loans loans = restClient.getLoansByDisbursementDate(fromDate, toDate);
        log.info("Loans:{}", loans);
        List<PageItem> pageItems = loans.getPageItems();
        List<Integer> disbursedLoans = new ArrayList<>();

        for (int i = 0; i < pageItems.size(); i++) {
            PageItem page = pageItems.get(i);
            int loanId = page.getId();
            disbursedLoans.add(loanId);
        }

        return disbursedLoans;
    }

    public DisbursedLoans findDisbursedLoansByRangeAndBranch(String branchName, String fromDate, String toDate) {

        AllLoans allLoans = restClient.getAllLoans(branchName, fromDate, toDate);
        assert allLoans != null;

        List<Loan> loans = allLoans.getPageItems();
        System.out.println("Returned Loans " + loans.size());

        List<DisbursedLoan> disbursedLoans = musoniProcessor.getDisbursedLoansByRange(loans, fromDate, toDate);
        List<DisbursedLoanMonth> disbursedLoanMonths = musoniProcessor.groupByMonth(disbursedLoans);

        return musoniProcessor.disbursedLoans(disbursedLoanMonths);
    }

    //A function to get all loans by disbursement date range and group by month
    public DisbursedLoans getLoansDisbursedByDateRange(String fromDate, String toDate) {

        AllLoans allLoans = restClient.retrieveAllLoans(fromDate, toDate);

        List<Loan> loans = allLoans.getPageItems();
        List<DisbursedLoan> disbursedLoans = musoniProcessor.getDisbursedLoansByRange(loans, fromDate, toDate);
        List<DisbursedLoanMonth> disbursedLoanMonths = musoniProcessor.groupByMonth(disbursedLoans);

        return musoniProcessor.disbursedLoans(disbursedLoanMonths);
    }
}

