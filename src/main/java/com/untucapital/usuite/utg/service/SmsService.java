package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.DTO.BulkSMSDto;
import com.untucapital.usuite.utg.DTO.PhoneNumbers;
import com.untucapital.usuite.utg.DTO.SMSDto;
import com.untucapital.usuite.utg.model.Sms;
import com.untucapital.usuite.utg.repository.SMSRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Service
@Slf4j
public class SmsService {
    private final SMSRepo smsRepo;
    private final RestTemplate restTemplate;

    @Value("${esolutions.url}")
    private String eSolutionsBaseURL;
    @Value("${esolutions.username}")
    private String username;
    @Value("${esolutions.password}")
    private String password;

    public SmsService(SMSRepo smsRepo, RestTemplate restTemplate) {
        this.smsRepo = smsRepo;
        this.restTemplate = restTemplate;
    }


    public HttpHeaders setESolutionsHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setBasicAuth(username, password);
        headers.set("Content-Type", "application/json");

        return headers;
    }

    public String sendSingle(String destination, String messageText) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String messageDate = dateFormat.format(new Date()) + 5;
        String messageReference = UUID.randomUUID().toString();
//        Sms sms = new Sms("UNTU",destination,messageText,messageReference,messageDate,"","");
        SMSDto smsDto = new SMSDto("UNTU", destination, messageText, messageReference, messageDate, "", "");
        HttpEntity<SMSDto> entity = new HttpEntity<>(smsDto, setESolutionsHeaders());
        return restTemplate.exchange(eSolutionsBaseURL + "single", HttpMethod.POST, entity, String.class).getBody();
    }

    public String sendBulk(){
        List<Sms> listSMS = smsRepo.findAll();
        BulkSMSDto bulkSMSDto = new BulkSMSDto();
        List<Map> listSMS1 = new ArrayList<>();
        for (Sms sms : listSMS
        ) {
            bulkSMSDto.setOriginator(sms.getOriginator());
            bulkSMSDto.setDestination(sms.getDestination());
            bulkSMSDto.setMessageText(sms.getMessageText());
            bulkSMSDto.setMessageReference(sms.getMessageReference());

            setMessageTextObject(bulkSMSDto);


        }

        return sendSMS(listSMS1);
    }

    public String sendBulkTest(PhoneNumbers phoneNumbers) {

        List<String> mobileNumbers = phoneNumbers.getNumber();
        BulkSMSDto bulkSMSDto = new BulkSMSDto();
        List<Map> listSMS1 = new ArrayList<>();

        for (String mobileNumber : mobileNumbers) {
            bulkSMSDto.setOriginator("UNTU");
            bulkSMSDto.setDestination(mobileNumber);
            bulkSMSDto.setMessageText(phoneNumbers.getMessageText());
//            bulkSMSDto.setMessageReference(batchNumber);

            listSMS1.add(setMessageTextObject(bulkSMSDto));

        }

        return sendSMS(listSMS1);
    }

    private Map setMessageTextObject(BulkSMSDto bulkSMSDto) {
        String referenceNumber = UUID.randomUUID().toString();
        Map<String, Object> bulk1 = new HashMap<>();
        bulk1.put("originator",bulkSMSDto.getOriginator());
        bulk1.put("destination",bulkSMSDto.getDestination());
        bulk1.put("messageText", bulkSMSDto.getMessageText() );
        bulk1.put("messageReference",referenceNumber);

        return bulk1;
    }

    private String sendSMS(List<Map> listSMS1) {
        String batchNumber = "B" + UUID.randomUUID();
        Map<String, Object> json = new HashMap<>();
        json.put("batchNumber",batchNumber);
        json.put("messages", listSMS1);
        String json1 = new JSONObject(json).toString();
        System.out.printf("Bulk Message => %s%n",json1);
        HttpEntity<String> entity = new HttpEntity<>(json1, setESolutionsHeaders());
        return restTemplate.exchange(eSolutionsBaseURL + "bulk", HttpMethod.POST, entity, String.class).getBody();
    }

    public String getBalance() {
        HttpEntity<String> entity = new HttpEntity<>(setESolutionsHeaders());
        return restTemplate.exchange(eSolutionsBaseURL + "balance/UNTUAPI", HttpMethod.GET, entity, String.class).getBody();
    }

    @Scheduled(fixedRate = 5000)
    public String SchedulerConfig() {
        System.out.println("The time is now {}");
        return "The time is now {}";
    }

}
