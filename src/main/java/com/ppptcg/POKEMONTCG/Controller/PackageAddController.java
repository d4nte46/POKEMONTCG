package com.ppptcg.POKEMONTCG.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ppptcg.POKEMONTCG.DAO.PackageDao;
import com.ppptcg.POKEMONTCG.model.CardsetEntity;
import com.ppptcg.POKEMONTCG.model.userPackageInputEntity;
import com.ppptcg.POKEMONTCG.nonSpringclasses.InfoFromList;
import com.ppptcg.POKEMONTCG.nonSpringclasses.PokeapiPOJO;
import com.ppptcg.POKEMONTCG.nonSpringclasses.ptcg_io_interaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(method = RequestMethod.POST ,value = "/addPackage")
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

    @GetMapping
    public String addPackage(Model model){
        List<CardsetEntity> hehe = PackageRep.findAll();
        userPackageInputEntity[] LoUPIE = new userPackageInputEntity[10];
        String[] setnames = new String[hehe.size()];
        for (int i = 0; i < hehe.size(); i++) {
            setnames[i] = hehe.get(i).getCardset();
        }
        String[] varietyname = infl.gettheinfo("Variant List.txt");

        model.addAttribute("setnames", setnames);
        model.addAttribute("variety", varietyname);
        model.addAttribute("LoUPIE",LoUPIE);

        return "packagenew";
    }


}

