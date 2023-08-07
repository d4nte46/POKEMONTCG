package com.ppptcg.POKEMONTCG.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ppptcg.POKEMONTCG.DAO.PackageDao;
import com.ppptcg.POKEMONTCG.model.CardsetEntity;
import com.ppptcg.POKEMONTCG.model.userCardInputEntity;
import com.ppptcg.POKEMONTCG.nonSpringclasses.PokeapiPOJO;
import com.ppptcg.POKEMONTCG.nonSpringclasses.ptcg_io_interaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@Controller
public class  CardSuccessController {
    @Autowired
    PokeapiPOJO pokeapiPOJO;
    @Autowired
    PackageDao PackageRep;
    @GetMapping("/addcardsuccess")
    public String cardsuccess(@ModelAttribute("UCIE") userCardInputEntity UCIE, Model model) throws UnirestException, JsonProcessingException {
        ptcg_io_interaction ption = new ptcg_io_interaction(pokeapiPOJO.getApi_Key());
        CardsetEntity cardset = PackageRep.findByName(UCIE.getSetId());
        System.out.println(cardset.getId() + UCIE.getId());
        if(cardset.getCardset().contains("Trainer Gallery")){
            if (UCIE.getId()<10){
                String link = ption.get_card_img(cardset.getId() + "-TG0" + UCIE.getId()).replace("\"" , "") ;
                model.addAttribute("link",link);
                return "cardaddsuccess";}
            else{
                String link = ption.get_card_img(cardset.getId() + "-TG" + UCIE.getId()).replace("\"" , "") ;
                model.addAttribute("link",link);
                return "cardaddsuccess";
            }}
        else{
            String link = ption.get_card_img(cardset.getId() + "-" + UCIE.getId()).replace("\"" , "") ;
            model.addAttribute("link",link);
            return "cardaddsuccess";}
    }



}
