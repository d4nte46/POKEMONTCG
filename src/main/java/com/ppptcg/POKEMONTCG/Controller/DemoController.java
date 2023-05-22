package com.ppptcg.POKEMONTCG.Controller;

import com.ppptcg.POKEMONTCG.DAO.PackageDao;
import com.ppptcg.POKEMONTCG.model.Rand;
import com.ppptcg.POKEMONTCG.model.TableNameIdEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DemoController {

    @Autowired
    PackageDao PackageRep;
    @GetMapping("/demo")
    String testing(Model model){
        return "demo";
    }

}
