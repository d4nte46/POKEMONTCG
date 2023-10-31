package com.ppptcg.POKEMONTCG.Controller;

import com.ppptcg.POKEMONTCG.DAO.UserEDao;
import com.ppptcg.POKEMONTCG.DAO.UserIDDao;
import com.ppptcg.POKEMONTCG.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class ResetController {

    @Autowired
    UserIDDao User;

    @Autowired
    UserEDao EUser;

    @PostMapping("/reset")
    public String resetlepassword(@RequestParam(value = "password") String password ,@RequestParam(value = "matchpassword") String match ,@RequestParam(value = "etomatch") String email, Model model, RedirectAttributes red){
        UserEntity login = EUser.findByEmail(email).orElse(null);
        if(login==null){
            String message = "Email Does not match, try generating the URL again.";
            red.addFlashAttribute("message",message);
            return "redirect:/";
        }else{
            if(password.equals(match)) {
                login.setvericode();
                login.setPassword(password);
                login.setVerified(false);
                login.setAttempts(0);
                EUser.save(login);
                String message = "Password Has been Reset. Make sure to Verify Your Account";
                red.addFlashAttribute("message", message);
                return "redirect:/";
            }else{
                String message = "The passwords does not match, Try generating the url again";
                red.addFlashAttribute("message",message);
                return "redirect:/";
            }
        }
    }

    @GetMapping("/reset")
    public String resetpassword(@RequestParam("authCode") String authCode, @RequestParam("userID") String ID, Model model, RedirectAttributes red){
        UserEntity login = User.findById(ID).orElse(null);
        if(login == null){
            String message = "User does not Exist";
            red.addFlashAttribute("message",message);
            return "redirect:/";
        }else{
            if(!(login.getVerification_code().equals(authCode))){
                String message = "Verification check returns False, try regenerating URL";
                red.addFlashAttribute("message",message);
                return "redirect:/";
            }
            else{
                model.addAttribute("etomatch",login.getEmail());
                return "reset";
            }
        }
    }
}
