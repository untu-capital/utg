package com.untucapital.usuite.utg.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.untucapital.usuite.utg.model.fcb.Report;
import com.untucapital.usuite.utg.model.fcb.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Chirinda Nyasha Dell 23/11/2021
 */

public class FormatterUtil {
    private static final Logger log = LoggerFactory.getLogger(FormatterUtil.class);

    private static final ObjectMapper prettyPrinter = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .registerModule(new JavaTimeModule());

    public static String toJson(Object obj) {

        String prettyJsonObj = "";
        if (obj != null) {
            try {
                prettyJsonObj = prettyPrinter.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                log.error("Failed to convert Object to JSON - {}", e.getMessage(), e);
            }
        }
        return prettyJsonObj;
    }

    public static Response toFCBResponse(String obj) {

        Response response = new Response();
        if (obj != null) {
            try {
                response = prettyPrinter.readValue(obj, new TypeReference<Response>() {
                });
            } catch (JsonProcessingException e) {
                log.error("Failed to convert Object to FCB Response - {}", e.getMessage(), e);
            }
        }
        return response;
    }

    public static List<Report> toFCBReport(String stringReport) {

        ReportList reportList = new ReportList();
        if (stringReport != null) {
            try {
                reportList = prettyPrinter.readValue(stringReport, new TypeReference<>() {
                });
            } catch (JsonProcessingException e) {
                log.error("Failed to convert Object to FCB Report - {}", e.getMessage(), e);
            }
        }
        return reportList.getReport();
    }
}

class ReportList {

    @JsonProperty(value = "Report")
    public List<Report> report;

    public List<Report> getReport() {
        return report;
    }

    public void setReport(List<Report> report) {
        this.report = report;
    }
}
