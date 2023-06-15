package com.ppptcg.POKEMONTCG.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VeriController {

    @GetMapping("/verify")
    public String verifyUser(){

        return "verify";
    }

}
