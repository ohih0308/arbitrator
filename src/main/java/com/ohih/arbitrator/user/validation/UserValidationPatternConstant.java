package com.ohih.arbitrator.user.validation;

import java.util.regex.Pattern;

public interface UserValidationPatternConstant {
    Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+{}:;<>,.?~])[A-Za-z\\d!@#$%^&*()_+{}:;<>,.?~]{8,16}$");
    Pattern USERNAME_PATTERN = Pattern.compile("^[가-힣]{2,10}$");
}