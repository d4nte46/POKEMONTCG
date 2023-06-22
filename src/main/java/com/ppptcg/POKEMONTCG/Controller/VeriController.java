package com.ppptcg.POKEMONTCG.Controller;

import com.ppptcg.POKEMONTCG.DAO.UserEDao;
import com.ppptcg.POKEMONTCG.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class VeriController {

    @Autowired
    UserEDao UserEDao;


    @GetMapping("/verify")
    public String verifyUser(){
        return "verify";
    }
    @PostMapping("/verify")
    public String sendverify(@RequestParam(value = "email") String email ){
        UserEntity loginUser = UserEDao.findByEmail(email).orElse(null);
        if (loginUser == null){
            System.out.println("Account with the email does not exist");
        }else {
            String url = "http://localhost:8080/auth?authCode="+loginUser.getVerification_code()+"&userID="+loginUser.getID();
            System.out.println(url);
        }
        return "redirect:/";

    }

}
