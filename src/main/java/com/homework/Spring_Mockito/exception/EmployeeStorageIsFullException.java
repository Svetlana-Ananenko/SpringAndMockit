package com.homework.Spring_Mockito.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeStorageIsFullException extends RuntimeException {
    public EmployeeStorageIsFullException() {
        super("Слишком много людей в базе, увольте кого-нибудь");
    }

    public EmployeeStorageIsFullException(String message) {
        super(message);
    }
}