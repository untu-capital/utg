package com.untucapital.usuite.utg.utils;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

/**
 * @author Chirinda Nyasha Dell 23/11/2021
 */

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
        // TODO: Regex to validate email
        return true;
    }
}
