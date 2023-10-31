package com.ppptcg.POKEMONTCG.Controller;

import com.ppptcg.POKEMONTCG.DAO.PackageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @Autowired
    PackageDao PackageRep;
    @GetMapping("/demo")
    String testing(Model model){
        return "demo";
    }

}
