package com.ppptcg.POKEMONTCG.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ppptcg.POKEMONTCG.DAO.PackageDao;
import com.ppptcg.POKEMONTCG.model.CardsetEntity;
import com.ppptcg.POKEMONTCG.model.userCardInputEntity;
import com.ppptcg.POKEMONTCG.model.userPackageCardInputEntity;
import com.ppptcg.POKEMONTCG.model.userPackageInputEntity;
import com.ppptcg.POKEMONTCG.nonSpringclasses.InfoFromList;
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
public class PackageAddController {

    @Autowired
    PokeapiPOJO pokeapiPOJO;

    @Autowired
    PackageDao PackageRep;
    InfoFromList infl = new InfoFromList();

    @PostMapping("/addallsets")
    public String updateSets() throws UnirestException, JsonProcessingException {


        ptcg_io_interaction ption = new ptcg_io_interaction(pokeapiPOJO.getApi_Key());
        HashMap<String,String> setnames = ption.gotta_get_all_sets();
        setnames.forEach((name,id)-> PackageRep.save(new CardsetEntity(id,name)));
        return "redirect:/addPackage";
    }

    @GetMapping("/addPackage")
    public String addPackage(Model model){
        List<CardsetEntity> hehe = PackageRep.findAll();
        userPackageCardInputEntity[] LoUPCIE = new userPackageCardInputEntity[10];
        userPackageInputEntity UPIE = new userPackageInputEntity(LoUPCIE);

        String[] setnames = new String[hehe.size()];

        for (int i = 0; i < hehe.size(); i++) {
            setnames[i] = hehe.get(i).getCardset();
        }
        String[] varietyname = infl.gettheinfo("Variant List.txt");

        model.addAttribute("setnames", setnames);
        model.addAttribute("listofvariety", varietyname);
        model.addAttribute("UPIE",UPIE);

        return "packagenew";
    }

    @PostMapping("/addPackage")
    public String packageConfirm(@ModelAttribute("UPIE") userPackageInputEntity UPIE, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("UPIE",UPIE);
        return "redirect:/packageaddsuccess";
//
//        System.out.println(UPIE.getPackageNo() + " \n" + UPIE.getPackageName() + "\n" );
//        for (userPackageCardInputEntity up : UPIE.getListOfUPCIE() ){
//            System.out.println(up.toString());
//        }
//        redirectAttributes.addFlashAttribute("UPIE",UPIE);
//        return "redirect:/addPackage";
    }

}

