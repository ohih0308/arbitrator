package com.ohih.arbitrator.exception.custom_exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IncorrectCredentialException extends RuntimeException {
    private int responseCode;

    public IncorrectCredentialException(int responseCode) {
        this.responseCode = responseCode;
    }
}
