package com.ohih.arbitrator.user;

import com.ohih.arbitrator.constant.PageNameConstant;
import com.ohih.arbitrator.constant.UrlConstant;
import com.ohih.arbitrator.constant.WebConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping(UrlConstant.LOGIN)
    public String login(Model model) {
        model.addAttribute("emailSuffix", WebConstant.EMAIL_SUFFIX);
        return PageNameConstant.LOGIN;
    }

    @GetMapping(UrlConstant.REGISTER)
    public String register(Model model) {
        model.addAttribute("emailSuffix", WebConstant.EMAIL_SUFFIX);
        return PageNameConstant.REGISTER;
    }
}
