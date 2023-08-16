package com.ppptcg.POKEMONTCG.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ppptcg.POKEMONTCG.DAO.PackageDao;
import com.ppptcg.POKEMONTCG.model.CardsetEntity;
import com.ppptcg.POKEMONTCG.model.userCardInputEntity;
import com.ppptcg.POKEMONTCG.nonSpringclasses.PokeapiPOJO;
import com.ppptcg.POKEMONTCG.nonSpringclasses.ptcg_io_interaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class  CardSuccessController {
    @Autowired
    private PokeapiPOJO pokeapiPOJO;

    @Autowired
    private PackageDao PackageRep;

//    @Autowired
//    private ResourceLoader RL;

//    @Autowired
//    private CacheManager cm;

    @GetMapping("/addcardsuccess")
    public String cardsuccess(@ModelAttribute("UCIE") userCardInputEntity UCIE, Model model, HttpSession session) throws UnirestException, JsonProcessingException {
        ptcg_io_interaction ption = new ptcg_io_interaction(pokeapiPOJO.getApi_Key());
        CardsetEntity cardset = PackageRep.findByName(UCIE.getSetId());
        session.setAttribute("UCIE",UCIE);
        System.out.println(cardset.getId() + UCIE.getId());
        if(cardset.getCardset().contains("Trainer Gallery")){
            if (UCIE.getId()<10){
                String link = ption.get_card_img(cardset.getId() + "-TG0" + UCIE.getId()).replace("\"" , "") ;
                model.addAttribute("link",link);
//                red.addFlashAttribute("UCIE",UCIE);
                return "cardaddsuccess";}
            else{
                String link = ption.get_card_img(cardset.getId() + "-TG" + UCIE.getId()).replace("\"" , "") ;
                model.addAttribute("link",link);
//                red.addFlashAttribute("UCIE",UCIE);
                return "cardaddsuccess";
            }}
        else{
            String link = ption.get_card_img(cardset.getId() + "-" + UCIE.getId()).replace("\"" , "") ;
            model.addAttribute("link",link);
//            model.addAttribute("UCIE",UCIE);
            return "cardaddsuccess";
        }
    }

    @GetMapping("/addcardconfirm")
    public String confirmedcard (HttpSession session,RedirectAttributes red){
        red.addFlashAttribute("message","You have successfully added a card");
        System.out.println("The values are as follows \n User ID : " + session.getAttribute("userId") + " \n Card ID " + UCIE.getId() + " \n Set ID " + PackageRep.findByName(UCIE.getSetId()).getCardset());
        session.removeAttribute("UCIE");
        return "redirect:/main";
    }

//    @GetMapping("/preloadimage")
//    public ResponseEntity<Resource> preloadImage(Model model){
//        Cache cache =
//
//
//
//    }



}
