package com.untucapital.usuite.utg.service;
import com.untucapital.usuite.utg.DTO.RepaymentScheduleDTO;
import com.untucapital.usuite.utg.model.MusoniClient;
import com.untucapital.usuite.utg.repository.MusoniRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
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
import java.util.*;
import java.util.Date;


@Service
@Getter
@Setter
@AllArgsConstructor
public class MusoniService {
    static final String DB_URL = "jdbc:mysql://localhost:3306/u-tran-gateway-db";
    static final String USER = "tranGatewayAdmin";
    static final String PASS = "u92uCuwte9@ta";

    @Autowired
    private final RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(RoleService.class);

    public HttpHeaders httpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth("kelvinr","Team@123");
        headers.set("X-Fineract-Platform-TenantId","untucapital");
        headers.set("x-api-key","4WEowWNz169UbYC052Lgsagd8U32t7As2lPYzEZl");
        headers.set("Content-Type", "application/json");

        return headers;
    }

    private HttpEntity<String> setHttpEntity() {
        return new HttpEntity<String>(httpHeaders());
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
        String repaymentSchedule = restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/loans/" + loanAccount + "?associations=repaymentSchedule", HttpMethod.GET, setHttpEntity(), String.class).getBody();
        System.out.println(repaymentSchedule);
        return repaymentSchedule;
    }

    public String getClientById(@PathVariable String clientId) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/clients/"+clientId, HttpMethod.GET, entity, String.class).getBody();
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
    @Scheduled(fixedRate = 50000)
    public String transactionSmsScheduler() throws JSONException, ParseException, SQLException, IOException {

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

        long timestamps = Long.valueOf(stamp) - 1209600L;
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        String timestampedLoanAcc = restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/loans?modifiedSinceTimestamp="+timestamps, HttpMethod.GET, entity, String.class).getBody();

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
                phone_number = "0784315526";
            }

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

        for (int transId = transIds; transId <= transIds+2; transId++) {
            try {
//            URL url = new URL("http://localhost:7878/api/utg/musoni/getTransations/loanid/" + loanId +"/transactionId/"+ transId);

                System.out.println("This is before Musoni Transactions Query!");
                HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
                if (new JSONObject(restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/loans/" + loanId + "/transactions/" + transId, HttpMethod.GET, entity, String.class).getBody()).has("id")) {
                    System.out.println("trans id exists for this trans");

                    String loanTransBody = restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/loans/" + loanId + "/transactions/" + transId, HttpMethod.GET, entity, String.class).getBody();

                    String loanTrans = new JSONObject(loanTransBody).getJSONObject("type").getString("value");

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
//                        System.out.println("Disbursement message has been sent..");
                        smsService.sendSingle(phone_number, "You have disbursed from us..");

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

            }catch (HttpClientErrorException | ParseException | SQLException e){
                e.getMessage();
            }


        }
        // EXIT WHILE LOOP


    return "";
    }

    public String repaymentSms(String loanId, String phone_number, Long unixTimestamp, int transIds) throws JSONException, SQLException {

        for (int transId = transIds; transId <= transIds+2; transId++) {
            try {
//            URL url = new URL("http://localhost:7878/api/utg/musoni/getTransations/loanid/" + loanId +"/transactionId/"+ transId);

                System.out.println("This is before Musoni Transactions Query!");
                HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
                if (new JSONObject(restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/loans/" + loanId + "/transactions/" + transId, HttpMethod.GET, entity, String.class).getBody()).has("id")) {
                    System.out.println("trans id exists for this trans");

                    String loanTransBody = restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/loans/" + loanId + "/transactions/" + transId, HttpMethod.GET, entity, String.class).getBody();

                    String loanTrans = new JSONObject(loanTransBody).getJSONObject("type").getString("value");

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
//                        System.out.println("Disbursement message has been sent..");
                                smsService.sendSingle(phone_number, "You have repaid to us..");

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

            }catch (HttpClientErrorException | ParseException | SQLException e){
                e.getMessage();
            }


        }
        // EXIT WHILE LOOP


        return "";
    }


}
