package com.ppptcg.POKEMONTCG.Controller;
import com.ppptcg.POKEMONTCG.DAO.UserEDao;
import com.ppptcg.POKEMONTCG.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ForgotPasswordController {
    @Autowired
    UserEDao user;

    @GetMapping("/forgotpassword")
    public String forgor(Model model,@ModelAttribute("verifyUrl") String verifyUrl){
        UserEntity email = new UserEntity();
        model.addAttribute("email",email);
        model.addAttribute("verifyUrl", verifyUrl);
        return "forgor";
    }

    @PostMapping("/forgotpassword")
    public String getemail(@ModelAttribute("email") String email , RedirectAttributes red){
        UserEntity login = user.findByEmail(email).orElse(null);
        if(login == null){
            String message = "User with this email does not exist";
            red.addFlashAttribute("message",message);
            return "redirect:/";
        }
        else{
            String url = "http://localhost:8080/reset?authCode="+login.getVerification_code()+"&userID="+login.getID();
            red.addFlashAttribute("verifyUrl",url);
            return "redirect:/forgotpassword";
        }
    }
}
