package com.ohih.arbitrator.common;

import com.ohih.arbitrator.constant.PageNameConstant;
import com.ohih.arbitrator.constant.UrlConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class ArbitratorController {

    @GetMapping(UrlConstant.HOME)
    public String home() {
        return PageNameConstant.HOME;
    }
}
