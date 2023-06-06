package com.ppptcg.POKEMONTCG.Controller;

import com.ppptcg.POKEMONTCG.DAO.PackageDao;
import com.ppptcg.POKEMONTCG.model.Rand;
import com.ppptcg.POKEMONTCG.model.TableNameIdEntity;
import com.ppptcg.POKEMONTCG.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {



    @GetMapping("/")
    public String home(Model model) {
        UserEntity User = new UserEntity();
        model.addAttribute("User",User);
        return "home";
    }

    @PostMapping("/signin")
    public String setUser(@ModelAttribute("User") UserEntity User) {
        System.out.println(User.getEmail() +"\n" + User.getPassword());
        return "redirect:/";
    }

    /*
    * FOR REMEMBERING PASSWORD THING REMEMBER SESSION INSTEAD OF PASSWORD
    *
    *
    *
    * */

}
