package com.ohih.arbitrator.user.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResultDto {
    private boolean success;
    private int responseCode;
    private String redirectUrl;
}
