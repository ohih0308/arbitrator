package com.ohih.arbitrator.common;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ArbitratorService {
    private final MessageSource messageSource;

    public String getMessage(Integer responseCode) {
        return messageSource.getMessage(Integer.toString(responseCode), new Object[]{}, Locale.getDefault());
    }
}
