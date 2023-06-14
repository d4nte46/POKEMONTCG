package com.ppptcg.POKEMONTCG.Controller;

import com.ppptcg.POKEMONTCG.DAO.UserDao;
import com.ppptcg.POKEMONTCG.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;


@Controller
public class HomeController {

    @Autowired
    UserDao UserDao ;

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


    @PostMapping("/signup")
    public String signup(
            @RequestParam(value = "emailsignup")
            String email,
            @RequestParam(value = "enteredpassword")
            String password,
            @RequestParam(value = "checkedpassword")
            String verify,
            Model model){
        String match ;
        System.out.println(email + "\n" + password + "\n" + verify + "\n");
        if (password.equals(verify)){
            UserEntity user = new UserEntity();
            user.setEmail(email);
            user.setPassword(password);
            user.setID();
            while(UserDao.existsById(user.getID())){
                user.setID();
            }
            user.setvericode();
            System.out.println(user.toString());
            try{
                UserDao.save(user);
            }
            catch(Exception e){
                match = "inherit";
                model.addAttribute("match", match);
                return "redirect:/";
            }
            return "redirect:/";
        }
        else{
            match = "inherit";
            model.addAttribute("match", match);
            return "redirect:/";
        }
    }
    /*
     * FOR REMEMBERING PASSWORD THING REMEMBER SESSION INSTEAD OF PASSWORD
     *
     *
     *
     * */

}

