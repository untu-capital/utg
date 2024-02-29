package com.untucapital.usuite.utg.utils;

import com.untucapital.usuite.utg.dto.loans.LoanTransaction;
import com.untucapital.usuite.utg.exception.InvalidDateFormatExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Component
@Slf4j
public class MusoniUtils {

    public static long generateTimestamp(Long timestamp) {

        Instant now = Instant.now();
        long originalTimestamp = now.getEpochSecond();

        log.info("Original timestamp : {}", originalTimestamp);

        // Convert UNIX timestamp to Instant
        Instant originalInstant = Instant.ofEpochSecond(originalTimestamp);
        log.info("original instant: {}", originalInstant);

        // Subtract 24 hours (86400 seconds) from the Instant
        Instant subtractedInstant = originalInstant.minusSeconds(timestamp);

        // Convert the resulting Instant back to a UNIX timestamp
        long subtractedTimestamp = subtractedInstant.getEpochSecond();

        log.info("Original Timestamp: " + originalTimestamp);
        log.info("Subtracted 24 hours Timestamp: " + subtractedTimestamp);

        return subtractedTimestamp;
    }

    public static long getTimestamp() {
        Timestamp timestamp = (new Timestamp(System.currentTimeMillis()));
        long stamps = timestamp.getTime();
        String stampString = String.valueOf(stamps);
        String stamp = stampString.substring(0, stampString.length() - 3);

        long timestamps = Long.valueOf(stamp) - 1209600L;

        return timestamps;
    }

    public static Boolean compareDates(Long timestamp, LocalDate transDate) {
        // Assume you have a Timestamp object
        Timestamp timestamp1 = new Timestamp(timestamp);

        // Convert Timestamp to LocalDateTime
        LocalDateTime localDateTime = timestamp1.toLocalDateTime();

        // Extract LocalDate from LocalDateTime
        LocalDate searchDate = localDateTime.toLocalDate();

        if (transDate.isAfter(searchDate)) {
            return true;
        } else {
            return false;
        }
    }

    public static Long getUnixTimeMinus24Hours() {

        // Get the current Unix time in milliseconds
        long currentTimeMillis = System.currentTimeMillis();

        long millisecondsIn2_5Weeks = (long) (2.5 * 7 * 24 * 3600 * 1000 );

        return currentTimeMillis - millisecondsIn2_5Weeks;
    }

    public static Long getUnixTimeMinusDays() {
        // Get the current Unix time in seconds (Java 8 or later)
        long currentUnixTime = Instant.now().getEpochSecond();

        // Calculate the number of seconds in 22 days
        long secondsInDays = 29 * 24 * 60 * 60; // 22 days * 24 hours * 60 minutes * 60 seconds

        // Subtract the calculated seconds from the current time
        return currentUnixTime - secondsInDays;
    }


    public static Long getUnixTimeMinus1Hour() {

        // Get the current Unix time in seconds (Java 8 or later)
        long currentUnixTime = Instant.now().getEpochSecond();

        // Calculate the number of seconds in 24 hours
        long secondsIn1Hour = 3600;

        // Subtract the calculated seconds from the current time
        return currentUnixTime - secondsIn1Hour;
    }

    public static Boolean isValidDate(int[] dateArray) throws ParseException {
        LocalDate submitedDate = MusoniUtils.formatDate(dateArray);
        log.info("Formatted Date: {}", submitedDate);

        LocalDate previousDate = LocalDate.now().minusDays(28);

        int compareDates = submitedDate.compareTo(previousDate);
        if (compareDates < 0) {
            return false;
        } else if (compareDates > 0) {
            return true;
        }
        return true;
    }

    public static LocalDate formatDate(int[] dateArray) throws ParseException {
        if (dateArray.length < 3) {
            throw new IllegalArgumentException("Date array should have at least 3 elements: year, month, day.");
        }

        int year = dateArray[0];
        int month = dateArray[1];
        int day = dateArray[2];

        String dateString = String.format("%d-%d-%d", day, month, year);

        SimpleDateFormat dateFormat = new SimpleDateFormat("d-M-yyyy");

        Date date = dateFormat.parse(dateString);

        LocalDate localDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

    public static String getTransDate(LoanTransaction loanTransaction) throws ParseException {
        int[] datetime = loanTransaction.getDate();
        String oldstring = datetime[0] + "-" + datetime[1] + "-" + datetime[2];
        SimpleDateFormat old_format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat new_format = new SimpleDateFormat("dd-MMM-yyyy");
        Date transDates = old_format.parse(oldstring);
        String transDate = new_format.format(transDates);
        return transDate;
    }

    public static String formatMusoniDate(int[] datetime) throws ParseException {

        String oldstring = datetime[0] + "-" + datetime[1] + "-" + datetime[2];
        SimpleDateFormat old_format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat new_format = new SimpleDateFormat("dd-MMM-yyyy");
        Date transDates = old_format.parse(oldstring);
        String transDate = new_format.format(transDates);
        return transDate;
    }

    public static String formatMusoniDt(int[] datetime) throws ParseException {

        String oldstring = datetime[0] + "-" + datetime[1] + "-" + datetime[2];
        SimpleDateFormat old_format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat new_format = new SimpleDateFormat("dd-MMM-yyyy");
        Date transDates = old_format.parse(oldstring);
        String transDate = new_format.format(transDates);
        return transDate;
    }
    public static String formatPastelDates(LocalDate date) throws ParseException {

        SimpleDateFormat new_format = new SimpleDateFormat("dd-MMM-yyyy");
        String transDate = new_format.format(date);
        return transDate;
    }


    public static String currencyFormatter(BigDecimal amount) {
        Locale usa = new Locale("en", "US");
        NumberFormat currency = NumberFormat.getCurrencyInstance(usa);
        return currency.format(amount);
    }

    public static String getYearMonth(LocalDate date) {

        try {

            int year = date.getYear();
            int month = date.getMonthValue();

            return year + "," + month;

        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatExceptionHandler("Invalid date format: " + date);
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    // Add years to a date in Java
    public static Date addYears(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }

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

    public static int calculatePeriod() {
        // Define the starting point (January 2020)
        LocalDate startDate = LocalDate.of(2020, Month.JANUARY, 1);

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Calculate the number of months between the start date and the current date
        int months = (int) startDate.until(currentDate, java.time.temporal.ChronoUnit.MONTHS);

        // Add 1 to make the period 1-based
        return months + 1;
    }

}
