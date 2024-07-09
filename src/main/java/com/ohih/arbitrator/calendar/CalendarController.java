package com.ohih.arbitrator.calendar;

import com.ohih.arbitrator.constant.PageNameConstant;
import com.ohih.arbitrator.constant.UrlConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarController {
    @GetMapping(UrlConstant.CALENDAR)
    public String calendar() {
        return PageNameConstant.CALENDAR;
    }
}
