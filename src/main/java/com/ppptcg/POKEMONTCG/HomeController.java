package com.ppptcg.POKEMONTCG;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/addPackage")
    public String addPackage(){
        return "packagenew";
    }

    @GetMapping("/addcard")
    public String cardnew(){
        return "cardnew";
    }

//    @PostMapping
//    public String redirectCard(){
//        return "redirect:addcard";
//    }

}
