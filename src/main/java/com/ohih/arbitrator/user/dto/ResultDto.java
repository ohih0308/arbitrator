package com.ohih.arbitrator.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResultDto {
    private boolean success;
    private List<Integer> responseCodes;
    private String redirectUrl;
}