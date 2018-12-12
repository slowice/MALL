package xb.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/jqwidgetsdemo")
    public String goToDashboard(){
        return "jqwidgetsdemo";
    }

    @RequestMapping("/moduletest")
    public String moduleTest(){
        return "/moduletest";
    }
}
