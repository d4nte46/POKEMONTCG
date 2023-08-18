package com.ppptcg.POKEMONTCG.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ppptcg.POKEMONTCG.DAO.PackageDao;
import com.ppptcg.POKEMONTCG.model.CardsetEntity;
import com.ppptcg.POKEMONTCG.model.userCardInputEntity;
import com.ppptcg.POKEMONTCG.nonSpringclasses.InfoFromList;
import com.ppptcg.POKEMONTCG.nonSpringclasses.PokeapiPOJO;
import com.ppptcg.POKEMONTCG.nonSpringclasses.ptcg_io_interaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class CardAddController {
    @Autowired
    PokeapiPOJO pokeapiPOJO;
    @Autowired
    PackageDao PackageRep;

    InfoFromList infl = new InfoFromList();
    @GetMapping("/addcard/addallsets")
    public String updateSets() throws UnirestException, JsonProcessingException {

        ptcg_io_interaction ption = new ptcg_io_interaction(pokeapiPOJO.getApi_Key());
        HashMap<String,String> setnames = ption.gotta_get_all_sets();
        setnames.forEach((name,id)-> PackageRep.save(new CardsetEntity(id,name)));
        return "redirect:/addcard";
    }

    @PostMapping("/addcard")
    public String setrandentity(@ModelAttribute("UCIE") userCardInputEntity UCIE, RedirectAttributes redirectAttributes){
            System.out.println(UCIE.getSetId() + " \n" + UCIE.getId() + "\n" + UCIE.getVarietyName());
            redirectAttributes.addFlashAttribute("UCIE",UCIE);
            return "redirect:/addcardsuccess";

    };

    @GetMapping ("/addcard")
    public String cardnew(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (session != null && session.getAttribute("isAuthenticated") != null) {
            if ((boolean) session.getAttribute("isAuthenticated")) {
                String[] varietyname = infl.gettheinfo("Variant List.txt");
                List<CardsetEntity> hehe = PackageRep.findAll();
                String[] setnames = new String[hehe.size()];
                for (int i = 0; i < hehe.size(); i++) {
                    setnames[i] = hehe.get(i).getCardset();
                }
                userCardInputEntity UCIE = new userCardInputEntity();
                model.addAttribute("setnames", setnames);
                model.addAttribute("varietyname", varietyname);
                model.addAttribute("UCIE", UCIE);
                return "cardnew";
            }
        }
        redirectAttributes.addFlashAttribute("message","Try logging in with correct credentials");
        return "redirect:/";
    }

}
