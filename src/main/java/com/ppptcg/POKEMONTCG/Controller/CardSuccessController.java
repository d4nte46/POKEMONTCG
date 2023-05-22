package com.ppptcg.POKEMONTCG.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ppptcg.POKEMONTCG.DAO.PackageDao;
import com.ppptcg.POKEMONTCG.model.Rand;
import com.ppptcg.POKEMONTCG.nonSpringclasses.PokeapiPOJO;
import com.ppptcg.POKEMONTCG.nonSpringclasses.ptcg_io_interaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class  CardSuccessController {
    @Autowired
    PokeapiPOJO pokeapiPOJO;
    @Autowired
    PackageDao PackageRep;
    @GetMapping("/addcardsuccess")
    public String cardsuccess(@ModelAttribute("rd") Rand rd,Model model) throws UnirestException, JsonProcessingException {
        model.addAttribute("rd",rd);
        ptcg_io_interaction ption = new ptcg_io_interaction(pokeapiPOJO.getApi_Key());
        String link = ption.get_card_img(rd.getPackname() + "-" + rd.getID()).replace("\"" , "") ;
        model.addAttribute("link",link);
        return "cardaddsuccess";
    }


}
