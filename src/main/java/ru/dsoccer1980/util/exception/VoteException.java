package ru.dsoccer1980.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED, reason = "Too late for vote")
public class VoteException extends RuntimeException {

    public VoteException(String message) {
        super(message);
    }
}
