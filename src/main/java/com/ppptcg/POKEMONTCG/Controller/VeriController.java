package com.ppptcg.POKEMONTCG.Controller;

import com.ppptcg.POKEMONTCG.DAO.UserEDao;
import com.ppptcg.POKEMONTCG.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VeriController {

    @Autowired
    UserEDao UserEDao;

    @GetMapping("/verify")
    public String verifyUser(@ModelAttribute("verifyUrl") String verifyUrl, Model model){
        model.addAttribute("verifyUrl", verifyUrl);
        return "verify";
    }
    @PostMapping("/verify")
    public String sendverify(@RequestParam(value = "email") String email , Model model, RedirectAttributes red){
        UserEntity loginUser = UserEDao.findByEmail(email).orElse(null);
        if (loginUser == null){
            String message = "Account with the email does not exist";
            red.addFlashAttribute("message",message);
            return "redirect:/";
        }else {
            String url = "http://localhost:8080/auth?authCode="+loginUser.getVerification_code()+"&userID="+loginUser.getID();
            Boolean showDiv = true;
            red.addFlashAttribute("verifyUrl",url);
            return "redirect:/verify";
        }

    }

}
