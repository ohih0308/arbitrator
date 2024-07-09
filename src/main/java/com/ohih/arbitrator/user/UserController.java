package com.ohih.arbitrator.user;

import com.ohih.arbitrator.constant.PageNameConstant;
import com.ohih.arbitrator.constant.UrlConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping(UrlConstant.LOGIN)
    public String login() {
        return PageNameConstant.LOGIN;
    }

    @GetMapping(UrlConstant.REGISTER)
    public String register() {
        return PageNameConstant.REGISTER;
    }
}
