package com.ohih.arbitrator.exception.custom_exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValidationException extends RuntimeException {
    private List<Integer> errorCodes;

    public ValidationException(List<Integer> errorCodes) {
        this.errorCodes = errorCodes;
    }
}
