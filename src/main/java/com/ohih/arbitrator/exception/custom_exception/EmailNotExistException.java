package com.ohih.arbitrator.exception.custom_exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmailNotExistException extends RuntimeException {
    private int responseCode;

    public EmailNotExistException(int responseCode) {
    }
}
