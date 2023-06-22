package com.ppptcg.POKEMONTCG.Controller;

import com.ppptcg.POKEMONTCG.DAO.UserIDDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    UserIDDao UserDao;
    @GetMapping("/auth")
    public String authUser(@RequestParam("authCode") String authCode, @RequestParam("userID") String ID){
        System.out.println(authCode +"\n"+ ID);
        return "temp";
    }
}
