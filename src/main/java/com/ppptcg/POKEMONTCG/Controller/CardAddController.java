package com.ppptcg.POKEMONTCG.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ppptcg.POKEMONTCG.nonSpringclasses.PokeapiPOJO;
import com.ppptcg.POKEMONTCG.nonSpringclasses.ptcg_io_interaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(method = RequestMethod.POST ,value = "/addcard")
public class CardAddController {
    @Autowired
    PokeapiPOJO pokeapiPOJO;
    @PostMapping("/addallsets")
    public String updateSets() throws UnirestException, JsonProcessingException {

        System.out.println("CUM");
        ptcg_io_interaction ption = new ptcg_io_interaction(pokeapiPOJO.getApi_Key());
        System.out.println(ption.gotta_get_all_sets());
        return "redirect:/addcard";
    }
}
