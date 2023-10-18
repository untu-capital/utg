package com.untucapital.usuite.utg.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.untucapital.usuite.utg.controller.MusoniController;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
@AllArgsConstructor
public class MusoniSyncClientsService {
    @Autowired
    private static final String DB_URL = "${spring.datasource.url}";
    @Autowired
    private static final String USER = "${spring.datasource.username}";
    @Autowired
    private static final String PASS = "${spring.datasource.password}";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MusoniController musoniController;

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
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static JSONArray CMSreadJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            JSONArray CMSjson = new JSONArray(jsonText);

            return CMSjson;
        } finally {
            is.close();
        }
    }

    public static JSONObject MobilereadJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            JSONObject Mobjson = new JSONObject(jsonText);

            return Mobjson;
        } finally {
            is.close();
        }
    }


    public static void main(String[] args) throws IOException, JSONException {

        List<String> PhoneArray = new ArrayList<String>();
        List<Integer> CMSPhoneArray = new ArrayList<Integer>();

        JSONArray CMSjson = CMSreadJsonFromUrl("http://localhost:7878/api/utg/users");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonArray = objectMapper.readTree(String.valueOf(CMSjson));


        for (JsonNode jsonObject : jsonArray) {
//            int id = jsonObject.get("id").asInt();
            JsonNode ContactDetail = jsonObject.get("contactDetail");

            String CMSphone = ContactDetail.get("mobileNumber").asText();
            // do something with the id and name values

            CMSPhoneArray.add(Integer.valueOf(CMSphone));

            System.out.println("From CMS  " + CMSphone);
        }


        JSONObject json = readJsonFromUrl("http://localhost:7878/api/utg/musoni/clients");
        JSONArray pageItems = json.getJSONArray("pageItems");

        for (int i = 0; i < pageItems.length(); i++) {
            JSONObject page = pageItems.getJSONObject(i);

            int ClientID = page.getInt("id");
            String PhoneNumber = page.getString("mobileNo");
//                System.out.println("Client ID "+ClientID+" corresponds to "+PhoneNumber.toString());

            PhoneArray.add(PhoneNumber);


            List<Integer> arr = CMSPhoneArray;
            int searchValue = Integer.parseInt(PhoneNumber);
            boolean found = false;

            for (int a = 0; a < arr.size(); a++) {
                if (arr.get(a) == searchValue) {
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println("The value " + searchValue + " was found in the array.");
//                    http://localhost:7878/api/utg/users/getUserByMobileNumber/0783229685

                JSONObject Mobjson = MobilereadJsonFromUrl("http://localhost:7878/api/utg/users/getUserByMobileNumber/" + searchValue);
                String CMSUserID = Mobjson.getString("id");
                System.out.println("The UserID of the found Mobile Number is " + CMSUserID);

                // Set the URL of the server endpoint that you want to send the PUT request to
                URL url = new URL("http://localhost:7878/api/utg/users/updateMusoniClientID/" + CMSUserID);

                // Create a HttpURLConnection object and set its request method to "PUT"
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("PUT");

                // Set the request headers, if needed
                conn.setRequestProperty("Content-Type", "application/json");

                // Set the JSON data that you want to send as the request body
                String jsonInputString = "{\"musoniClientId\": " + ClientID + "}";

                // Write the JSON data to the request body
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);

                // Send the PUT request and read the response
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder responseBuilder = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    responseBuilder.append(responseLine.trim());
                }
                String responseString = responseBuilder.toString();

                // Print the response
                System.out.println(responseString);

            } else {
                System.out.println("The value " + searchValue + " was not found in the array.");
            }
        }
    }
}
