package com.nhnacademy.account.exception;

import org.springframework.validation.BindingResult;

public class AlreadyUserException extends RuntimeException {
    public AlreadyUserException(BindingResult bindingResult) {
        super(bindingResult.toString());
    }
}
