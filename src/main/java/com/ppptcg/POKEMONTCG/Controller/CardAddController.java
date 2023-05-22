package com.ppptcg.POKEMONTCG.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ppptcg.POKEMONTCG.DAO.PackageDao;
import com.ppptcg.POKEMONTCG.model.Rand;
import com.ppptcg.POKEMONTCG.model.TableNameIdEntity;
import com.ppptcg.POKEMONTCG.nonSpringclasses.PokeapiPOJO;
import com.ppptcg.POKEMONTCG.nonSpringclasses.ptcg_io_interaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;

@Controller
public class CardAddController {
    @Autowired
    PokeapiPOJO pokeapiPOJO;
    @Autowired
    PackageDao PackageRep;
    @PostMapping("/addcard/addallsets")
    public String updateSets() throws UnirestException, JsonProcessingException {

        System.out.println("CUM");
        ptcg_io_interaction ption = new ptcg_io_interaction(pokeapiPOJO.getApi_Key());
        HashMap<String,String> setnames = ption.gotta_get_all_sets();
        setnames.forEach((name,id)-> PackageRep.save(new TableNameIdEntity(id,name)));

        return "redirect:/addcard";
    }

    @PostMapping("/addcard")
    public String setrandentity(@ModelAttribute("rd") Rand rd, RedirectAttributes redirectAttributes){
            System.out.println(rd.getPackname() + " \n" + rd.getID() + "\n" + rd.isReverseholofoil());
            redirectAttributes.addFlashAttribute("rd",rd);
            return "redirect:/addcardsuccess";

    };

    @GetMapping ("/addcard")
    public String cardnew(Model model){
        List<TableNameIdEntity> setnames = PackageRep.findAll();
        Rand rd = new Rand();
        model.addAttribute("setnames", setnames);
        model.addAttribute("rd",rd);
        return "cardnew";
    }

}
