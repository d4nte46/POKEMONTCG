package com.ppptcg.POKEMONTCG.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ppptcg.POKEMONTCG.DAO.*;
import com.ppptcg.POKEMONTCG.model.*;
import com.ppptcg.POKEMONTCG.nonSpringclasses.PokeapiPOJO;
import com.ppptcg.POKEMONTCG.nonSpringclasses.ptcg_io_interaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;

@Controller
public class  CardSuccessController {
    private final PokeapiPOJO pokeapiPOJO;

    private final PackageDao PackageRep;

    private final CardDao CD;

    private final ownerCardDAO ocd;

    private final pokemonDAO pd;

    private final subtypesDAO sd;

    private final typesDAO td;

    private final TrainerDAO TrD;

    private final EnergyDao ED;

    public CardSuccessController(PokeapiPOJO pokeapiPOJO, PackageDao PackageRep, CardDao CD, ownerCardDAO ocd, pokemonDAO pd, subtypesDAO sd, typesDAO td, TrainerDAO TrD, EnergyDao ED) {
        this.pokeapiPOJO = pokeapiPOJO;
        this.PackageRep = PackageRep;
        this.CD = CD;
        this.ocd = ocd;
        this.pd = pd;
        this.sd = sd;
        this.td = td;
        this.TrD = TrD;
        this.ED = ED;
    }
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
            String ST ;
            if(cardset.getCardset().contains("Trainer Gallery")){
                if (UCIE.getId()<10){
                    ST = cardset.getId() + "-TG0" + UCIE.getId();
                    link = ption.get_card_img(ST).replace("\"" , "") ;
                    }
                else{
                    ST = cardset.getId() + "-TG" + UCIE.getId();
                    link = ption.get_card_img(ST).replace("\"" , "") ;
                }}
            else{
                ST = cardset.getId() + "-" + UCIE.getId();
                link = ption.get_card_img(ST).replace("\"" , "") ;
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

        Short Id = UCIE.getId();
        String SetId = PackageRep.findByName(UCIE.getSetId()).getId();
        String suppe = ption.getSuperType(Id,SetId);
        red.addFlashAttribute("message","You have successfully added a card");
        System.out.println("The values are as follows \n User ID : "
                + session.getAttribute("userId")
                + " \n Card ID : " + Id + " \n Set ID : "
                + SetId
                + " \n Variety :  "
                + UCIE.getVarietyName() );

        CardEntity temp = new CardEntity(SetId ,Id,ption.getArtist(UCIE.getId(),SetId), ption.getRarity(Id,SetId),UCIE.getVarietyName());
//        CD.save(temp);

        ownerCardEntity tpoe = new ownerCardEntity(session.getAttribute("userId").toString(),temp.getId());
//        ocd.save(tpoe);

        if(suppe.equals("Pokémon")){
            String[] PKNO = ption.getNationalDexID(Id,SetId);
            String[] PKSub = ption.getSubTypes(Id,SetId);
            String[] PKTypes = ption.getTypes(Id,SetId);
            for(String number : PKNO){
                PokemonEntity tpc = new PokemonEntity(temp.getId(), Short.parseShort(number));
                System.out.println(tpc.toString());
//                pd.save(tpc);
            }
            for(String subType : PKSub){
                SubtypesEntity tse = new SubtypesEntity(temp.getId(), subType);
                System.out.println(tse.toString());
//                sd.save(tse);
            }
            for(String Type : PKTypes){
                TypesCounterEntity tte = new TypesCounterEntity(temp.getId(),Type);
                System.out.println(tte.toString());
//                td.save(tte);
            }
        }else if (suppe.equals("Trainer")) {
            String[] TRSub = ption.getSubTypes(Id,SetId);
            for(String sub : TRSub){
                TrainerEntity tte = new TrainerEntity(temp.getId(), sub,ption.getName(Id,SetId));
                System.out.println(tte.toString());
//                TrD.save(tte);
            }
        }else {
            EnergyEntity tee = new EnergyEntity(temp.getId(),ption.getName(Id,SetId));
            System.out.println(tee.toString());
//            ED.save(tee);
        }



        System.out.println(suppe);
        System.out.println(tpoe.toString());
        System.out.println(temp.toString());
        session.removeAttribute("UCIE");
        return "redirect:/main";
    }


}
