package com.ohih.arbitrator.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterDto {
    private String email;
    private String password;
    private String name;
}
