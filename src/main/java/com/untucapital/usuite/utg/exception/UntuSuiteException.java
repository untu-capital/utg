package com.untucapital.usuite.utg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Chirinda Nyasha Dell 23/11/2021
 */

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UntuSuiteException extends RuntimeException {

    public UntuSuiteException(String message) {
        super(message);
    }

    public UntuSuiteException(String message, Throwable cause) {
        super(message, cause);
    }
}
