package com.ppptcg.POKEMONTCG.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ppptcg.POKEMONTCG.DAO.CardDao;
import com.ppptcg.POKEMONTCG.DAO.PackageDao;
import com.ppptcg.POKEMONTCG.DAO.ownerCardDAO;
import com.ppptcg.POKEMONTCG.model.CardEntity;
import com.ppptcg.POKEMONTCG.model.CardsetEntity;
import com.ppptcg.POKEMONTCG.model.ownerCardEntity;
import com.ppptcg.POKEMONTCG.model.userCardInputEntity;
import com.ppptcg.POKEMONTCG.nonSpringclasses.PokeapiPOJO;
import com.ppptcg.POKEMONTCG.nonSpringclasses.ptcg_io_interaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class  CardSuccessController {
    @Autowired
    private PokeapiPOJO pokeapiPOJO;

    @Autowired
    private PackageDao PackageRep;

    @Autowired
    private CardDao CD;

    @Autowired
    private ownerCardDAO ocd;

//    @Autowired
//    private ResourceLoader RL;

//    @Autowired
//    private CacheManager cm;

    @GetMapping("/addcardsuccess")
    public String cardsuccess(@ModelAttribute("UCIE") userCardInputEntity UCIE, Model model, HttpSession session, RedirectAttributes redirectAttributes) throws UnirestException, JsonProcessingException {
        try{
            String link;
            ptcg_io_interaction ption = new ptcg_io_interaction(pokeapiPOJO.getApi_Key());
            CardsetEntity cardset = PackageRep.findByName(UCIE.getSetId());
            session.setAttribute("UCIE",UCIE);
            System.out.println(cardset.getId() + UCIE.getId());
            if(cardset.getCardset().contains("Trainer Gallery")){
                if (UCIE.getId()<10){
                    link = ption.get_card_img(cardset.getId() + "-TG0" + UCIE.getId()).replace("\"" , "") ;
                    model.addAttribute("link",link);
                    }
                else{
                    link = ption.get_card_img(cardset.getId() + "-TG" + UCIE.getId()).replace("\"" , "") ;
                }}
            else{
                link = ption.get_card_img(cardset.getId() + "-" + UCIE.getId()).replace("\"" , "") ;
            }
            if (link.equals("null")){
                redirectAttributes.addFlashAttribute("message","This Card does not Exist, Cross check with your physical Copy");
                return "redirect:/main";
            }
            model.addAttribute("link",link);
            return "cardaddsuccess";
        }catch (NullPointerException e){
            redirectAttributes.addFlashAttribute("message","There was a problem with the Card, Try Again");
            return "redirect:/main";
        }
    }

    @GetMapping("/addcardconfirm")
    public String confirmedcard (@SessionAttribute("UCIE") userCardInputEntity UCIE, HttpSession session,RedirectAttributes red) throws UnirestException, JsonProcessingException {
        ptcg_io_interaction ption = new ptcg_io_interaction(pokeapiPOJO.getApi_Key());
        String Id = PackageRep.findByName(UCIE.getSetId()).getId();
        red.addFlashAttribute("message","You have successfully added a card");
        System.out.println("The values are as follows \n User ID : "
                + session.getAttribute("userId")
                + " \n Card ID : " + UCIE.getId() + " \n Set ID : "
                + Id
                + " \n Variety :  "
                + UCIE.getVarietyName() );

        CardEntity temp = new CardEntity(Id , UCIE.getId(),ption.getArtist(UCIE.getId(),Id), UCIE.getVarietyName());
        CD.save(temp);

        ownerCardEntity tpoe = new ownerCardEntity(session.getAttribute("userId").toString(),temp.getId());

        ocd.save(tpoe);

        System.out.println(tpoe.toString());
        System.out.println(temp.toString());
        session.removeAttribute("UCIE");
        return "redirect:/main";
    }


}
