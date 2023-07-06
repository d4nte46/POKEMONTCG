package com.ppptcg.POKEMONTCG.Controller;

import com.ppptcg.POKEMONTCG.DAO.UserIDDao;
import com.ppptcg.POKEMONTCG.model.UserEntity;
import com.ppptcg.POKEMONTCG.nonSpringclasses.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    UserIDDao UserDao;
    @GetMapping("/auth")
    public String authUser(@RequestParam("authCode") String authCode, @RequestParam("userID") String ID, Model model, RedirectAttributes red){
        UserEntity User = UserDao.findById(ID).orElse(null);
        RandomString rd = new RandomString(13);
        if(User == null){
            return "redirect:/";
        }else{
            if(User.getVerification_code().equals(authCode)){
                User.setVerified(true);
                User.setvericode();
                User.setAttempts(0);
                UserDao.save(User);
                String message = "User email is Verified. Enjoy Our Services!";
                red.addFlashAttribute("message",message);
                return "redirect:/";
            }else{
                return "redirect:/";
            }
        }
    }
}
