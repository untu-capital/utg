package com.untucapital.usuite.utg.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

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

    public static Boolean isValidDate(int[] dateArray) throws ParseException {
        LocalDate submitedDate = MusoniUtils.formatDate(dateArray);
        log.info("Formatted Date: {}", submitedDate);

        LocalDate previousDate = LocalDate.now().minusDays(7);

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


}
