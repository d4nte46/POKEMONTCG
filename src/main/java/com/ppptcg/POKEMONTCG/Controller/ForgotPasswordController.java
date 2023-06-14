package com.ppptcg.POKEMONTCG.Controller;
import com.ppptcg.POKEMONTCG.model.UserEntity;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ForgotPasswordController {

    @GetMapping("/forgotpassword")
    public String forgor(Model model){
        UserEntity email = new UserEntity();
        model.addAttribute("email",email);
        return "forgor";
    }

    @PostMapping("/forgotpassword")
    public String getemail(@ModelAttribute("email") String email ){
        System.out.println(email.toString());
        return "redirect:/";
    }
}
