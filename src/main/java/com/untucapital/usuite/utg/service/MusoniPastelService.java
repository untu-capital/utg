package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.MusoniPastel;
import com.untucapital.usuite.utg.repository.MusoniPastelRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


@Service
@Getter
@Setter
@AllArgsConstructor
@Configuration
public class MusoniPastelService {

    @Autowired
    private static final String DB_URL = "${spring.datasource.url}";
    @Autowired
    private static final String USER = "${spring.datasource.username}";
    @Autowired
    private static final String PASS = "${spring.datasource.password}";

    @Autowired
    private static final String musoniUrl = "${musoni.url}";
    @Autowired
    private static final String musoniUsername = "${musoni.username}";
    @Autowired
    private static final String musoniPassword = "${musoni.password}";
    @Autowired
    private static final String musoniTenantId = "${musoni.X_FINERACT_PLATFORM_TENANTID}";
    @Autowired
    private static final String musoniApiKey = "${musoni.X_API_KEY}";
    private static final Logger log = LoggerFactory.getLogger(RoleService.class);
    @Autowired
    private final RestTemplate restTemplate;
    @Autowired
    MusoniPastelRepository musoniPastelRepository;
    @Autowired
    MusoniService musoniService;

//    public HttpHeaders httpHeader(){
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.setBasicAuth("Admin","");
//        headers.set("Content-Type", "application/json");
//        return headers;
//    }
    HttpHeaders headers;

//    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
//        InputStream is = new URL(url).openStream();
//        try {
//            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//            String jsonText = readAll(rd);
//            JSONObject json = new JSONObject(jsonText);
//            return json;
//        } finally {
//            is.close();
//        }
//    }
    List<String> timestampedLoanAccs = new ArrayList<>();

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setBasicAuth(musoniUsername, musoniPassword);
        headers.set("X-Fineract-Platform-TenantId", musoniTenantId);
        headers.set("x-api-key", musoniApiKey);
        headers.set("Content-Type", "application/json");
        return headers;
    }

    private HttpEntity<String> setHttpEntity() {
        return new HttpEntity<String>(httpHeaders());
    }

//    public String musoniPastelSync(int loanId) throws IOException, JSONException {
//        try(
//                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//                Statement stmt0 = conn.createStatement();
//                Statement stmt = conn.createStatement();
//                Statement stmt1 = conn.createStatement();
//                Statement stmt2 = conn.createStatement();
//
//        ) {
//
////         #############################################################################################################
//
////         COLLECTING LOAN IDS FROM MUSONI TO DATABASE TABLE
//
//                //  Check if Loan_id exists
//                ResultSet res = stmt0.executeQuery("SELECT * FROM musoni_loan_ids");
////                int loanId = (Integer) page.get("id");
//                List<Integer> loansArray = new ArrayList<Integer>();
//                while (res.next()) {
//                    loansArray.add(res.getInt("loan_id"));
//                }
//                List<Integer> array = loansArray;
//                if (array.contains(loanId)) {
//                    System.out.println("Loan ID Already Exists");
//                    System.out.println(loanId);
//                }else {
//                    // INSERT LOAN ID INTO DATABASE
//                    System.out.println("Inserting records into the table...");
//                    String sql = "INSERT INTO `musoni_loan_ids`(`loan_id`) VALUES (" + loanId + ")";
//                    stmt0.executeUpdate(sql);
//                    System.out.println(loanId);
//                }
////            }
//
////         #############################################################################################################
//
////          SELECT LOAN IDS FROM TABLE AND MATCH WITH TRANSACTION IDS
//
//            ResultSet rs = stmt.executeQuery("SELECT * FROM musoni_loan_ids");
////                SELECTED LARGEST TRANS ID IN MUSONI
//            ResultSet transIdrs = stmt.executeQuery("SELECT transaction_id FROM musoni_pastel WHERE transaction_id = (SELECT MAX(transaction_id) FROM musoni_pastel)");
//
//            int transactId = 0; // Initialize with a default value
//
//            if (transIdrs.next()) {
//                transactId = transIdrs.getInt("transaction_id");
//            }
//            while (rs.next()) {
//                loanId = rs.getInt("loan_id");
//                for (int transId = transactId; transId < transactId+15; transId++) {
//                    URL url = new URL("http://localhost:7878/api/utg/musoni/getTransations/loanid/" + loanId +"/transactionId/"+ transId);
//                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.connect();
//                    int code = connection.getResponseCode();
//                    System.out.println(code);
//
//                    if (code == 200) {
//                        JSONObject json = new JSONObject(getMusoniTrans(String.valueOf(loanId), String.valueOf(transId)));
////                        RETURNED JSON WHEN STATUS OK (200)
////                        System.out.println(json.toString());
//                        JSONObject type = json.getJSONObject("type");
//
////                        GET TRANSACTION TYPE
//                        Object transactionType = type.get("value");
//                        System.out.println(transactionType);
//
////                        GET TRANSACTION DATE
//                        JSONArray datetime = json.getJSONArray("date");
//                        String oldstring = datetime.get(0) + "-" + datetime.get(1) + "-" + datetime.get(2);
//                        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
//                        SimpleDateFormat old_format = new SimpleDateFormat("yyyy-MM-dd");
//                        SimpleDateFormat new_format = new SimpleDateFormat("dd-MMM-yyyy");
//                        Date transDates = old_format.parse(oldstring);
//                        String transDate = new_format.format(transDates);
//                        System.out.println(transDate);
//
////                        GET TRACTIONACTION AMOUNT
//                        Object amount = json.get("amount");
//                        System.out.println(amount);
//
////                        GET TRANSACTION CODE
//                        JSONObject currency = json.getJSONObject("currency");
//                        Object currencyCode = currency.get("code");
//                        System.out.println(currencyCode);
//                        System.out.println("Loan ID is: " + loanId + "\n And transaction ID is: " + transId);
//
////                        FOR DISBURSMENT TRANSACTION
//                        if (transactionType.equals("Disbursement")) {
//                            System.out.println(transactionType);
//                            String transType = "LOA-DIS";
//                            String transDescr = "Disbursement transaction";
//                            String fromAccount = "8420/000/FCA";
//                            String toAccount = "8700/000/000";
//                            String referenc = "DS261";
//
//                            System.out.println("############################# CAN NOW READ SCRIPT ##############################");
//
//                            ResultSet searchTransId = stmt1.executeQuery("SELECT * FROM musoni_pastel WHERE transaction_id = " + transId);
//
//                            List<Integer> transIdArray = new ArrayList<Integer>();
//                            while (searchTransId.next()) {
//                                transIdArray.add(searchTransId.getInt("transaction_id"));
//                            }
//                            List<Integer> transArray = transIdArray;
//
//                            if (transArray.contains(transId)) {
//                                System.out.println("Transaction ID Already Exists");
//                                System.out.println(transId);
//                            }else {
//                                //  INSERT TRANSACTION DETAILS INTO DATABASE
//                                System.out.println("Inserting transaction records into the table...");
//                                String sql = "INSERT INTO `musoni_pastel` (`amount`, `currency`, `description`, `exchange_rate`, `from_account`, `loan_id`, `reference`, `to_account`, `transaction_date`, `transaction_id`, `transaction_type`, `synced`) VALUES (" + amount + ", '001' , '" + transDescr + "'  , '1', '" +fromAccount + "', " + loanId + ", '" + referenc + "', '" + toAccount + "', '" + transDate + "', '" +transId + "', '" +transType + "', '0')";
//                                stmt1.executeUpdate(sql);
////                                stmt1.close();
//                                System.out.println(transId);
//
//                            }
//                            searchTransId.close();
//                        }
//
////                        FOR REPAYMENT TRANSACTION
//                        else if (transactionType.equals("Repayment")) {
//                            String transType = "LOA-REP";
//                            String transDescr = "Repayment Transaction";
//                            String fromAccount = "8700/000/000";
//                            String toAccount = "8420/000/FCA";
//                            String referenc = "RP261";
//
//                            ResultSet searchTransId = stmt.executeQuery("SELECT * FROM musoni_pastel WHERE transaction_id = " + transId);
//
//                            List<Integer> transIdArray = new ArrayList<Integer>();
//                            while (searchTransId.next()) {
//                                transIdArray.add(rs.getInt("transaction_id"));
//                            }
//                            List<Integer> transArray = transIdArray;
//
//                            if (transArray.contains(transId)) {
//                                System.out.println("Transaction ID Already Exists");
//                                System.out.println(transId);
//                            }else {
//                                // INSERT TRANSACTION DETAILS INTO DATABASE
//                                System.out.println("Inserting transaction records into the table...");
//                                String sql = "INSERT INTO `musoni_pastel` (`amount`, `currency`, `description`, `exchange_rate`, `from_account`, `loan_id`, `reference`, `to_account`, `transaction_date`, `transaction_id`, `transaction_type`, `synced`) VALUES (" + amount + ", '001' , '" + transDescr + "'  , '1', '" +fromAccount + "', " + loanId + ", '" + referenc + "', '" + toAccount + "', '" + transDate + "', '" +transId + "', '" +transType + "', '0')";
//                                stmt.executeUpdate(sql);
//                                System.out.println(transId);
//                            }
//                        } else {
//                            System.out.println("Non of the conditions met!!!");
//                        }
//                    }
//                }
//                // EXIT WHILE LOOP
//            }
//
////      ##########################################################################################################
//
////      POST TRANSACTION FROM DATABASE TO PASTEL EVOLUTION
//
//            ResultSet non_synced = stmt.executeQuery("SELECT * FROM musoni_pastel WHERE synced = '0'");
//
//            while (non_synced.next()) {
//                int nonSyncedTrans = non_synced.getInt("transaction_id");
//
//                JSONObject jsonObject = new JSONObject();
//                //Inserting key-value pairs into the json object
//                jsonObject.put("APIUsername", "Admin");
//                jsonObject.put("APIPassword", "");
//                jsonObject.put("TransactionType", non_synced.getString("transaction_type"));
//                jsonObject.put("TransactionDate", non_synced.getString("transaction_date"));
//                jsonObject.put("FromAccount", non_synced.getString("from_account"));
//                jsonObject.put("ToAccount", non_synced.getString("to_account"));
//                jsonObject.put("Reference", non_synced.getString("reference"));
//                jsonObject.put("Description", non_synced.getString("description"));
//                jsonObject.put("Currency", non_synced.getString("currency"));
//                jsonObject.put("Amount", non_synced.getString("amount"));
//                jsonObject.put("ExchangeRate", non_synced.getString("exchange_rate"));
//
//                URL url = new URL("http://192.168.2.103:1335/api/PastelTeller/PostJournalTxn");
//                URLConnection con = url.openConnection();
//                HttpURLConnection http = (HttpURLConnection)con;
//                http.setRequestMethod("POST"); // PUT is another valid option
//                http.setDoOutput(true);
//                byte[] out = jsonObject.toString() .getBytes(StandardCharsets.UTF_8);
//                int length = out.length;
//                http.setFixedLengthStreamingMode(length);
//                http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//                http.connect();
//                try(OutputStream os = http.getOutputStream()) {
//                    os.write(out);
//                    int respCode = ((HttpURLConnection) con).getResponseCode();
//                    System.out.println(respCode);
////                    System.out.print(transId);
//                    System.out.println((jsonObject).toString());
//
//                    if (respCode == 200) {
//                        String sql = "UPDATE `musoni_pastel` SET `synced`='1' WHERE `transaction_id` = " + nonSyncedTrans;
//                        stmt2.executeUpdate(sql);
////                        stmt2.close();
//                    }
//                }
//            }
//        } catch (SQLException | ParseException e) {
//            e.printStackTrace();
//        }
//        return "##########################################################################" +
//                "\n\nEXECUTION PROCESS HAS COMPLETED SUCESSFULLY " +
//                "\n\n##########################################################################";
//
//    }

    public String getMusoniTrans(@PathVariable("loanId") String loanId, @PathVariable("transactionId") String transactionId) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "loans/" + loanId + "/transactions/" + transactionId, HttpMethod.GET, entity, String.class).getBody();
    }
//    @Scheduled(fixedRate = 21600000)
//    public String getLoansByTimestamp() throws JSONException, ParseException, SQLException, IOException {
//        Timestamp timestamp = (new Timestamp(System.currentTimeMillis()));
//        long stamps = timestamp.getTime();
//        String stampString = String.valueOf(stamps);
//        String stamp = stampString.substring(0, stampString.length()-3);
//
//        long timestamps = Long.valueOf(stamp) - 345600L;
//        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
//        String timestampedLoanAcc = restTemplate.exchange(musoniUrl + "loans?modifiedSinceTimestamp="+timestamps, HttpMethod.GET, entity, String.class).getBody();
//
//        timestampedLoanAccs.clear();
//        for (int i = 0; i<new JSONObject(timestampedLoanAcc).getJSONArray("pageItems").length(); i++) {
//            String loan_id = new JSONObject(new JSONObject(timestampedLoanAcc).getJSONArray("pageItems").get(i).toString()).getString("id");
//            String status = new JSONObject(new JSONObject(timestampedLoanAcc).getJSONArray("pageItems").get(i).toString()).getJSONObject("status").getString("active");
//            String client_id = new JSONObject(new JSONObject(timestampedLoanAcc).getJSONArray("pageItems").get(i).toString()).getString("clientId");
//            musoniPastelSync(Integer.parseInt(loan_id));
//            StringBuilder menu = new StringBuilder("");
//            String timestampObject = menu.append("{")
//                    .append("\"loanId\"").append(" : ").append(loan_id).append(",")
//                    .append("\"statusActive\"").append(" : ").append(status).append(",")
//                    .append("\"clientId\"").append(" : ").append(client_id).append(",")
//                    .append("}").toString();
//            System.out.println(loan_id);
//            timestampedLoanAccs.add(timestampObject);
//        }
//
//        return "Pastel Integration Cycle completed..";
//    }

    public String currencyFormatter(String amount) {
        Locale usa = new Locale("en", "US");
        NumberFormat currency = NumberFormat.getCurrencyInstance(usa);
        return currency.format(amount);
    }

    public void save(MusoniPastel musoniPastel) {
        musoniPastelRepository.save(musoniPastel);
    }

    public MusoniPastel getMusoniPastelById(String transactionId) {
        return musoniPastelRepository.findByTransactionId(transactionId);
    }

    public List<MusoniPastel> getMusoniPastelBySynced(String synced) {
        return musoniPastelRepository.findBySynced(synced);
    }
}
//}
