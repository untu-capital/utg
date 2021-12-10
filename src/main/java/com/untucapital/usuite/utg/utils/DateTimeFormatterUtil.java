package com.untucapital.usuite.utg.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Chirinda Nyasha Dell - 8/12/2021
 */

public class DateTimeFormatterUtil {
    public static String fcbDobFormatter(LocalDate dob) {

        return dob.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
