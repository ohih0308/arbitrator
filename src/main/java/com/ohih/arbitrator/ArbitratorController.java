package com.ohih.arbitrator;

import com.ohih.arbitrator.constant.PageNameConstant;
import com.ohih.arbitrator.constant.UrlConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArbitratorController {

    @GetMapping(UrlConstant.HOME)
    public String home() {
        return PageNameConstant.HOME;
    }
}
