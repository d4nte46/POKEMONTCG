package com.ppptcg.POKEMONTCG.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ppptcg.POKEMONTCG.DAO.PackageDao;
import com.ppptcg.POKEMONTCG.model.CardsetEntity;
import com.ppptcg.POKEMONTCG.model.userPackageCardInputEntity;
import com.ppptcg.POKEMONTCG.model.userPackageInputEntity;
import com.ppptcg.POKEMONTCG.nonSpringclasses.PokeapiPOJO;
import com.ppptcg.POKEMONTCG.nonSpringclasses.ptcg_io_interaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class PackageAddSuccessController {

    private final PokeapiPOJO pokeapiPOJO;

    private final PackageDao PackageRep;

    public PackageAddSuccessController(PokeapiPOJO pokeapiPOJO, PackageDao packageRep) {
        this.pokeapiPOJO = pokeapiPOJO;
        PackageRep = packageRep;
    }


    @GetMapping("/packageaddsuccess")
    public String packagesuccess(@ModelAttribute("UPIE") userPackageInputEntity userPackageInputEntity, Model model, HttpSession session, RedirectAttributes redirectAttributes) throws UnirestException, JsonProcessingException {
        try{
            ptcg_io_interaction ption = new ptcg_io_interaction(pokeapiPOJO.getApi_Key());
            ArrayList<String> listOfLink = new ArrayList<>();
            CardsetEntity cardsetEntity = PackageRep.findByName(userPackageInputEntity.getPackageName());
            String cardSetName = userPackageInputEntity.getPackageName();
            String ST,link;
            for (userPackageCardInputEntity up : userPackageInputEntity.getListOfUPCIE()){
                    if(cardSetName.contains("Trainer Gallery")){
                        if (up.getSetNumber()<10){
                            ST = cardsetEntity.getId() + "-TG0" + up.getSetNumber();
                            link = ption.get_card_img(ST).replace("\"" , "") ;
                        }
                        else{
                            ST = cardsetEntity.getId() + "-TG" + up.getSetNumber();
                            link = ption.get_card_img(ST).replace("\"" , "") ;
                        }}
                    else{
                        ST = cardsetEntity.getId() + "-" + up.getSetNumber();
                        link = ption.get_card_img(ST).replace("\"" , "") ;
                    } if (link.equals("null")){
                        redirectAttributes.addFlashAttribute("message","One of the Card in the Package does not Exist, Cross check with your physical Copy");
                        return "redirect:/main";
                    }
                    listOfLink.add(link);
                    model.addAttribute("LINKS",listOfLink);
            }

            return "packageaddsuccess";
        } catch (NullPointerException e){
            redirectAttributes.addFlashAttribute("message","There was a problem with the Card, Try Again");
            return "redirect:/main";
        }
    }
}
