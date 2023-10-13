package com.untucapital.usuite.utg.service;
import com.untucapital.usuite.utg.DTO.AllLoans;
import com.untucapital.usuite.utg.DTO.DisbursedLoan;
import com.untucapital.usuite.utg.DTO.DisbursedLoanMonth;
import com.untucapital.usuite.utg.DTO.RepaymentScheduleDTO;
import com.untucapital.usuite.utg.DTO.loanObjects.Loan;
import com.untucapital.usuite.utg.exception.InvalidDateFormatExceptionHandler;
import com.untucapital.usuite.utg.exception.LoanListCannotBeNullExceptionHandler;
import com.untucapital.usuite.utg.model.MusoniClient;
import com.untucapital.usuite.utg.repository.MusoniRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.*;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.Date;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
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

    private static final Logger log = LoggerFactory.getLogger(RoleService.class);

    public HttpHeaders httpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth(musoniUsername,musoniPassword);
        headers.set("X-Fineract-Platform-TenantId",musoniTenantId);
        headers.set("x-api-key",musoniApiKey);
        headers.set("Content-Type", "application/json");
        return headers;
    }

    private HttpEntity<String> setHttpEntity() {
        return new HttpEntity<>(httpHeaders());
    }

    @Autowired
    SmsService smsService;

    @Autowired
    MusoniRepository musoniRepository;

    public void save(MusoniClient musoniClient){
        musoniRepository.save(musoniClient);
    }

    public MusoniClient getMusoniClientById(String clientId){
        return musoniRepository.findMusoniClientById(clientId);
    }

    public List<MusoniClient> getMusoniClientsByStatus(String status){
        return musoniRepository.findMusoniClientsByStatus(status);
    }

    public String getRepaymentSchedule(String loanAccount) {
        String repaymentSchedule = restTemplate.exchange(musoniUrl + "loans/" + loanAccount + "?associations=repaymentSchedule", HttpMethod.GET, setHttpEntity(), String.class).getBody();
        System.out.println(repaymentSchedule);
        return repaymentSchedule;
    }

    public String getClientById(String clientId) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "clients/"+clientId, HttpMethod.GET, entity, String.class).getBody();
    }

    public String getLoanByTimeStamp(@PathVariable Long timeStamp) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "loans?modifiedSinceTimestamp="+timeStamp, HttpMethod.GET, entity, String.class).getBody();
    }

    public String getLoansByDisbursementDate(@PathVariable String fromDate, @PathVariable String toDate) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "loans?disbursementFromDate="+fromDate+"&disbursementToDate="+toDate, HttpMethod.GET, entity, String.class).getBody();
    }

    public String getLoanId(String loanId) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        System.out.println("Called URL => "+musoniUrl + "loans/"+loanId);
        String loanDetails = restTemplate.exchange(musoniUrl + "loans/"+loanId, HttpMethod.GET, entity, String.class).getBody();
        System.out.println(loanDetails);
        return loanDetails;
    }

    //    ToDo: Get Required information from the loan returned
    public String repaymentSchedule(String phone_number, String loanAccount) throws ParseException, JSONException {

        Locale usa = new Locale("en", "US");
        NumberFormat currency = NumberFormat.getCurrencyInstance(usa);
        String jsonLoan  = getRepaymentSchedule(loanAccount);
        RepaymentScheduleDTO repaymentScheduleDTO = new RepaymentScheduleDTO();
        String repaymentSchedulPeriods = new String(String.valueOf(new JSONObject(jsonLoan).getJSONObject("repaymentSchedule").getJSONArray("periods").length()));

        System.out.println("####################  START  #############################");
        System.out.println(repaymentSchedulPeriods);
        System.out.println("#################### END   #############################");

        SimpleDateFormat df = new SimpleDateFormat("yyyy,MM,dd");
        Date date = new Date();
        String current_date = df.format(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy,MM,dd", Locale.ENGLISH);
        LocalDate currentDate = LocalDate.parse(current_date, formatter);

        JSONObject repaymentSchedul = null;
        for (int period = 1; period<Integer.parseInt(repaymentSchedulPeriods); period++ ) {
            repaymentSchedul = new JSONObject(new JSONObject(jsonLoan).getJSONObject("repaymentSchedule").getJSONArray("periods").get(period).toString());
            String due_dates = repaymentSchedul.getJSONArray("dueDate").get(0).toString() + "," + repaymentSchedul.getJSONArray("dueDate").get(1).toString() + "," + repaymentSchedul.getJSONArray("dueDate").get(2).toString();
            DateFormat inputFormat = new SimpleDateFormat("yyyy,MM,dd", Locale.US);
            Date dates = inputFormat.parse(due_dates);
            String due_date = df.format(dates);
            LocalDate dueDate = LocalDate.parse(due_date, formatter);

            if (dueDate.compareTo(currentDate) == 7){
                System.out.println("Next Repayment date is: "+ dueDate);
                smsService.sendSingle(phone_number, "Please be reminded that your next repayment date is: " + dueDate + "\n\nNote: Ignore this message if you\'ve already made your payment.");
                break;
            }
            else {
                System.out.println("You have no repayment date");
            }
        }

//        System.out.println(repaymentScheduleDTO);
        return "<b>Loan Account: "+loanAccount +
                "\nNext Repayment Date: "+repaymentScheduleDTO.getDueDate();

    }
    public String currencyFormatter(BigDecimal amount){
        Locale usa = new Locale("en", "US");
        NumberFormat currency = NumberFormat.getCurrencyInstance(usa);
        return currency.format(amount);
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    List<String> timestampedLoanAccs = new ArrayList<>();
    @Scheduled(fixedRate = 300000)
    public String transactionSmsScheduler() throws JSONException, ParseException, SQLException, IOException, ClassNotFoundException {

//        Class.forName("com.mysql.jdbc.Driver");
        Connection connTrans = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = connTrans.createStatement();
        ResultSet searchTranId = stmt.executeQuery("SELECT trans_id FROM sms_notification ORDER BY trans_id DESC LIMIT 1");
//        int transIds = searchTranId.getInt("trans_id");
        int transIds = 0;
        while (searchTranId.next()) {
            transIds = searchTranId.getInt("trans_id");
        }
        System.out.println("################## print this : "+transIds);

//        int transIds = searchTranId.getInt("trans_id");

        Timestamp timestamp = (new Timestamp(System.currentTimeMillis()));
        long stamps = timestamp.getTime();
        String stampString = String.valueOf(stamps);
        String stamp = stampString.substring(0, stampString.length()-3);

        long timestamps = Long.valueOf(stamp) - 21600L; // 6 hours is 21600
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        String timestampedLoanAcc = restTemplate.exchange(musoniUrl + "loans?modifiedSinceTimestamp="+timestamps, HttpMethod.GET, entity, String.class).getBody();

        timestampedLoanAccs.clear();
        for (int i = 0; i<new JSONObject(timestampedLoanAcc).getJSONArray("pageItems").length(); i++) {
            String loan_id = new JSONObject(new JSONObject(timestampedLoanAcc).getJSONArray("pageItems").get(i).toString()).getString("id");
            String status = new JSONObject(new JSONObject(timestampedLoanAcc).getJSONArray("pageItems").get(i).toString()).getJSONObject("status").getString("active");
            String client_id = new JSONObject(new JSONObject(timestampedLoanAcc).getJSONArray("pageItems").get(i).toString()).getString("clientId");

            String days_in_arrears = null;
            if (new JSONObject(new JSONObject(timestampedLoanAcc).getJSONArray("pageItems").get(i).toString()).has("summary")) {
                if (new JSONObject(new JSONObject(timestampedLoanAcc).getJSONArray("pageItems").get(i).toString()).getJSONObject("summary").has("daysInArrears")) {
                    days_in_arrears = new JSONObject(new JSONObject(timestampedLoanAcc).getJSONArray("pageItems").get(i).toString()).getJSONObject("summary").getString("daysInArrears");
                }
            }

            String phone_number = "0";
            if (new JSONObject(getClientById(client_id)).has("mobileNo")) {
//                phone_number = new JSONObject(getClientById(client_id)).getString("mobileNo");
                phone_number = "0775797299";
            }
            System.out.println(phone_number);

            disbursementSms(loan_id, phone_number, timestamps, transIds);

            repaymentSms(loan_id, phone_number, timestamps, transIds);

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
    public String disbursementSms(String loanId, String phone_number, Long unixTimestamp, int transIds) throws JSONException, SQLException {

        for (int transId = transIds; transId <= transIds+10; transId++) {
            try {

                System.out.println("This is before Musoni Transactions Query- Disbursements!");
                HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
                if (new JSONObject(restTemplate.exchange(musoniUrl + "loans/" + loanId + "/transactions/" + transId, HttpMethod.GET, entity, String.class).getBody()).has("id")) {
                    System.out.println("trans id exists for this trans");

                    String loanTransBody = restTemplate.exchange(musoniUrl + "loans/" + loanId + "/transactions/" + transId, HttpMethod.GET, entity, String.class).getBody();

                    String loanTrans = new JSONObject(loanTransBody).getJSONObject("type").getString("value");
                    String loanTransAmount = new JSONObject(loanTransBody).getString("amount");


                    if (loanTrans.equals("")){
                        System.out.println("Loan with Transaction not found!");
                    }
                    else if (loanTrans != "") {
                        JSONObject json = new JSONObject(loanTransBody);
                        JSONObject type = json.getJSONObject("type");

        //                        GET TRANSACTION TYPE
                        Object transactionType = type.get("value");
                        System.out.println(transactionType);
                        System.out.println("YOUR DIS NUMBER: "+phone_number);

//                        GET TRANSACTION DATE
                JSONArray datetime = json.getJSONArray("date");
                String oldstring = datetime.get(0) + "-" + datetime.get(1) + "-" + datetime.get(2);
                DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                SimpleDateFormat old_format = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat new_format = new SimpleDateFormat("dd-MMM-yyyy");
                Date transDates = old_format.parse(oldstring);
                String transDate = new_format.format(transDates);
                System.out.println(transDate);

//                        GET TRACTIONACTION AMOUNT
                Object amount = json.get("amount");
                System.out.println(amount);

//                        GET TRANSACTION CODE
                JSONObject currency = json.getJSONObject("currency");
                Object currencyCode = currency.get("code");
                System.out.println(currencyCode);
                System.out.println("Loan ID is: " + loanId + "\n And transaction ID is: " + transId);

//                        FOR DISBURSMENT TRANSACTION
                if (transactionType.equals("Disbursement")) {
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
                    }else {

//                        Todo: SET SEND SMS HERE...
                        System.out.println("Disbursement message has been sent..");
                        String sms_disburse = "This serves to confirm that a loan amount of " + currencyFormatter(new BigDecimal(loanTransAmount)) + " has been disbursed to Account: " + loanId + " on " + transDate + " and has been collected.";
                        smsService.sendSingle(phone_number, sms_disburse);

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
                }
                else {
                    System.out.println("else part in disbursement");
                }

            }catch (HttpClientErrorException | ParseException | SQLException e){
                e.getMessage();
            }
        }
        // EXIT WHILE LOOP

    return "";
    }

    public String repaymentSms(String loanId, String phone_number, Long unixTimestamp, int transIds) throws JSONException, SQLException {

        for (int transId = transIds; transId <= transIds+5; transId++) {
            try {

                HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
                System.out.println("This is before Musoni Transactions Query - Repayments!");
                if (new JSONObject(restTemplate.exchange(musoniUrl + "loans/" + loanId + "/transactions/" + transId, HttpMethod.GET, entity, String.class).getBody()).has("id")) {
                    System.out.println("trans id exists for this trans");

                    String loanTransBody = restTemplate.exchange(musoniUrl + "loans/" + loanId + "/transactions/" + transId, HttpMethod.GET, entity, String.class).getBody();

                    String loanTrans = new JSONObject(loanTransBody).getJSONObject("type").getString("value");
                    String loanTransAmount = new JSONObject(loanTransBody).getString("amount");

                    if (loanTrans.equals("")){
                        System.out.println("Loan with Transaction not found!");
                    }
                    else if (loanTrans != "") {
                        JSONObject json = new JSONObject(loanTransBody);
                        JSONObject type = json.getJSONObject("type");

                        //                        GET TRANSACTION TYPE
                        Object transactionType = type.get("value");
                        System.out.println(transactionType);
                        System.out.println("YOUR DIS NUMBER: "+phone_number);

//                        GET TRANSACTION DATE
                        JSONArray datetime = json.getJSONArray("date");
                        String oldstring = datetime.get(0) + "-" + datetime.get(1) + "-" + datetime.get(2);
                        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                        SimpleDateFormat old_format = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat new_format = new SimpleDateFormat("dd-MMM-yyyy");
                        Date transDates = old_format.parse(oldstring);
                        String transDate = new_format.format(transDates);
                        System.out.println(transDate);

//                        GET TRACTIONACTION AMOUNT
                        Object amount = json.get("amount");
                        System.out.println(amount);

//                        GET TRANSACTION CODE
                        JSONObject currency = json.getJSONObject("currency");
                        Object currencyCode = currency.get("code");
                        System.out.println(currencyCode);
                        System.out.println("Loan ID is: " + loanId + "\n And transaction ID is: " + transId);

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
                            }else {

//                        Todo: SET SEND SMS HERE...
//                        System.out.println("Repayment message has been sent..");
                                String sms_repayment = "This serves to confirm that a repayment of " + currencyFormatter(new BigDecimal(loanTransAmount)) + " has been made to Account: " + loanId + " on " + transDate;
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

                }
                else {
                    System.out.println("else part in repayment");
                }

            }catch (HttpClientErrorException | ParseException | SQLException e){
                e.getMessage();
            }


        }
        // EXIT WHILE LOOP


        return "";
    }

    public String disbursedLoans(String fromDate, String toDate) throws JSONException {
        JSONObject json = new JSONObject(getLoansByDisbursementDate(fromDate, toDate));
        JSONArray pageItems = json.getJSONArray("pageItems");
        JSONArray disbursedLoans = new JSONArray();

        // Store the total principals for each month
        Map<String, Double> monthlyTotals = new HashMap<>();

        for (int i = 0; i < pageItems.length(); i++) {
            JSONObject page = pageItems.getJSONObject(i);

            // Your existing code...
            // Check if Loan_id exists
            int loanId = page.getInt("id");
            String accountNo = page.getString("accountNo");
            String clientName = page.getString("clientName");
            String loanProductName = page.getString("loanProductName");
            double principal = page.getDouble("principal");
            int numberOfRepayments = page.getInt("numberOfRepayments");
            double interestRatePerPeriod = page.getDouble("interestRatePerPeriod");
            String expectedMaturityDate = page.getJSONObject("timeline")
                    .getString("expectedMaturityDate");

            String actualDisbursementDate = null;
            if (page.getJSONObject("timeline").has("actualDisbursementDate")) {
                actualDisbursementDate = page.getJSONObject("timeline")
                        .getString("actualDisbursementDate");
            }

            double totalExpectedRepayment = 0.0;
            double totalRepayment = 0.0;
            double totalOutstanding = 0.0;

            if (page.has("summary")) {
                totalExpectedRepayment = page.getJSONObject("summary")
                        .getDouble("totalExpectedRepayment");
            }

            if (page.has("summary")) {
                totalRepayment = page.getJSONObject("summary")
                        .getDouble("totalRepayment");
            }

            if (page.has("summary")) {
                totalOutstanding = page.getJSONObject("summary")
                        .getDouble("totalOutstanding");
            }

            String officeName = page.getString("officeName");
            String loanOfficerName = page.getString("loanOfficerName");

            JSONObject loanData = new JSONObject();

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
            loanData.put("accountNo", accountNo);
            loanData.put("clientName", clientName);
            loanData.put("loanProductName", loanProductName);
            loanData.put("principal", principal);
            loanData.put("numberOfRepayments", numberOfRepayments);
            loanData.put("interestRatePerPeriod", interestRatePerPeriod);
            loanData.put("actualDisbursementDate", actualDisbursementDate);
            loanData.put("expectedMaturityDate", expectedMaturityDate);
            loanData.put("totalExpectedRepayment", totalExpectedRepayment);
            loanData.put("totalRepayment", totalRepayment);
            loanData.put("totalOutstanding", totalOutstanding);
            loanData.put("officeName", officeName);
            loanData.put("loanOfficerName", loanOfficerName);
            loanData.put(month, monthlyTotal);


            disbursedLoans.put(loanData);
        }

        // Convert the monthlyTotals map to an ArrayList of monthly totals
        List<Double> monthlyPrincipalTotals = new ArrayList<>();
        for (Map.Entry<String, Double> entry : monthlyTotals.entrySet()) {
            monthlyPrincipalTotals.add(entry.getValue());
        }

        // Add the monthly totals to the result JSON
        JSONObject result = new JSONObject();
        result.put("disbursedLoans", disbursedLoans);
        result.put("monthlyPrincipalTotals", new JSONArray(monthlyPrincipalTotals));

        return result.toString();
    }


    public List<Integer> disbursedLoansByDate(String fromDate, String toDate) throws JSONException {
        JSONObject json = new JSONObject(getLoansByDisbursementDate(fromDate, toDate));
        JSONArray pageItems = json.getJSONArray("pageItems");
        List<Integer> disbursedLoans = new ArrayList<>();

        for (int i = 0; i < pageItems.length(); i++) {
            JSONObject page = pageItems.getJSONObject(i);
            int loanId = page.getInt("id");
            disbursedLoans.add(loanId);
        }

        return disbursedLoans;
    }

    //A function to get all loans by disbursement date range and group by month
    public List<DisbursedLoanMonth> getLoansDisbursedByDateRange(String fromDate, String toDate) {

        AllLoans allLoans = retrieveAllLoans(fromDate, toDate);

        List<Loan> loans = allLoans.getPageItems();
        List<DisbursedLoan> disbursedLoans = getDisbursedLoansByRange(loans, fromDate, toDate);
        List<DisbursedLoanMonth> disbursedLoanMonths = groupByMonth(disbursedLoans);
        calculateStatistics(disbursedLoanMonths);
        return disbursedLoanMonths;
    }

    /**
     * Bellow is a collection for private function to be used by the public functions
     **/

    //A function to get year string 2023, 1
    private String getYearMonth(String dateString) {

        try {
            // Remove the brackets from the string
            String cleanedString = dateString.replaceAll("[\\[\\]]", "");

            // Parse the cleaned string as a LocalDate object
            LocalDate date = LocalDate.parse(cleanedString, DateTimeFormatter.ofPattern("yyyy, M, d"));

            // Extract the year and month from the parsed LocalDate object
            int year = date.getYear();
            int month = date.getMonthValue();

            return year + "," + month;

        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatExceptionHandler("Invalid date format: " + dateString);
        }
    }

    //A function to get disbursed by loans
    private List<DisbursedLoan> getDisbursedLoans(List<Loan> loans) {
        List<DisbursedLoan> disbursedLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getTimeline().getActualDisbursementDate() != null) {
                DisbursedLoan disbursedLoan = DisbursedLoan.builder()
                        .loanName(loan.getClientName())
                        .expectedDisbursementDate(LocalDate.of(loan.getTimeline().getExpectedDisbursementDate()[0], loan.getTimeline().getExpectedDisbursementDate()[1], loan.getTimeline().getExpectedDisbursementDate()[2]))
                        .disbursedAt(LocalDate.of(loan.getTimeline().getActualDisbursementDate()[0], loan.getTimeline().getActualDisbursementDate()[1], loan.getTimeline().getActualDisbursementDate()[2]))
                        .loanId(loan.getAccountNo())
                        .branch(loan.getOfficeName())
                        .disbursedMonth(getYearMonth(Arrays.toString(loan.getTimeline().getActualDisbursementDate())))
                        .principal(loan.getPrincipal())
                        .build();

                disbursedLoans.add(disbursedLoan);
            }
        }
        return disbursedLoans;
    }

    //A function to get disbursed loans and group by month
    private List<DisbursedLoanMonth> groupByMonth(List<DisbursedLoan> disbursedLoans) {
        List<DisbursedLoanMonth> disbursedLoanMonths = new ArrayList<>();

        Map<String, Map<String, List<DisbursedLoan>>> loansByMonthAndBranch = disbursedLoans.stream()
                .collect(Collectors.groupingBy(DisbursedLoan::getDisbursedMonth,
                        Collectors.groupingBy(DisbursedLoan::getBranch)));

        for (Map.Entry<String, Map<String, List<DisbursedLoan>>> entry : loansByMonthAndBranch.entrySet()) {
            String month = entry.getKey();
            Map<String, List<DisbursedLoan>> loansByBranch = entry.getValue();
            List<DisbursedLoanMonth.BranchDisbursedLoan> branchDisbursedLoans = new ArrayList<>();

            for (Map.Entry<String, List<DisbursedLoan>> branchEntry : loansByBranch.entrySet()) {
                String branch = branchEntry.getKey();
                List<DisbursedLoan> loansOfBranch = branchEntry.getValue();
                BigDecimal totalPrincipal = loansOfBranch.stream()
                        .map(DisbursedLoan::getPrincipal)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                int count = loansOfBranch.size();
                DisbursedLoanMonth.BranchDisbursedLoan branchDisbursedLoan = DisbursedLoanMonth.BranchDisbursedLoan.builder()
                        .branch(branch)
                        .numberOfDisbursedLoans(count)
                        .disbursedLoans(loansOfBranch)
                        .totalPrincipal(totalPrincipal)
                        .build();

                branchDisbursedLoans.add(branchDisbursedLoan);
            }

            DisbursedLoanMonth disbursedLoanMonth = DisbursedLoanMonth.builder()
                    .month(month)
                    .branchDisbursedLoans(branchDisbursedLoans)
                    .build();

            disbursedLoanMonths.add(disbursedLoanMonth);
        }

        disbursedLoanMonths.sort(Comparator.comparing(DisbursedLoanMonth::getMonth));
        return disbursedLoanMonths;
    }

    public void calculateStatistics(List<DisbursedLoanMonth> disbursedLoanMonths) {
        for (DisbursedLoanMonth disbursedLoanMonth : disbursedLoanMonths) {
            int numberOfLoans = 0;
            BigDecimal totalPrincipal = BigDecimal.ZERO;

            List<DisbursedLoanMonth.BranchDisbursedLoan> branchDisbursedLoans = disbursedLoanMonth.getBranchDisbursedLoans();
            for (DisbursedLoanMonth.BranchDisbursedLoan branchDisbursedLoan : branchDisbursedLoans) {
                numberOfLoans += branchDisbursedLoan.getNumberOfDisbursedLoans();
                totalPrincipal = totalPrincipal.add(branchDisbursedLoan.getTotalPrincipal());
            }

            disbursedLoanMonth.setNumberOfDisbursedLoans(numberOfLoans);
            disbursedLoanMonth.setTotalPrincipal(totalPrincipal);
        }
    }

    //A function to get disbursed loans by date range
    private List<DisbursedLoan> getDisbursedLoansByRange(List<Loan> loans, String fromDate, String toDate) {
        List<DisbursedLoan> disbursedLoans = getDisbursedLoans(loans);

        return disbursedLoans.stream()
                .filter(disbursedLoan -> disbursedLoan.getDisbursedAt().isAfter(LocalDate.parse(fromDate)))
                .filter(disbursedLoan -> disbursedLoan.getDisbursedAt().isBefore(LocalDate.parse(toDate)))
                .collect(Collectors.toList());
    }

    //A function to get all loans.
    private AllLoans retrieveAllLoans(String fromDate, String toDate) {

        AllLoans allLoans = restTemplate.exchange(musoniUrl + "loans/?disbursementFromDate=" + fromDate + "&disbursementToDate=" + toDate + "&limit=1000",
                HttpMethod.GET,
                setHttpEntity(),
                new ParameterizedTypeReference<AllLoans>() {
                }).getBody();

        if (allLoans != null) {
            return allLoans;
        }

        throw new LoanListCannotBeNullExceptionHandler("Loan list cannot be null");
    }

}

