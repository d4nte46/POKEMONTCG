package com.ppptcg.POKEMONTCG.Controller;

import com.ppptcg.POKEMONTCG.DAO.UserEDao;
import com.ppptcg.POKEMONTCG.DAO.UserIDDao;
import com.ppptcg.POKEMONTCG.model.UserEntity;
import com.ppptcg.POKEMONTCG.nonSpringclasses.BCRYPTgenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;


@Controller
public class HomeController {

    @Autowired
    UserIDDao UserDao ;

    @Autowired
    UserEDao UserEDao;


    @GetMapping("/")
    public String home(Model model) {
        UserEntity User = new UserEntity();
        String message = (String) model.getAttribute("message");
        model.addAttribute("User",User);
        model.addAttribute("message", message);
        return "home";
    }

    @PostMapping("/signin")
    public String setUser(@RequestParam("loginemail")  String email, @RequestParam("password") String password,Model model, RedirectAttributes red, HttpSession session) {
//        System.out.println(User.getEmail() +"\n" + User.getPassword());
//        return "redirect:/";
        UserEntity loginuser = UserEDao.findByEmail(email).orElse(null);
        if (loginuser == null){
            String message = "User with this email does not exist";
            red.addFlashAttribute("message",message);
            return "redirect:/";
        }else {
            if(loginuser.getAttempts()>=7){
                return "forgotpassword";
            }else{
                BCRYPTgenerator BG = new BCRYPTgenerator();
                if(!BG.matchpassword(password,loginuser.getPassword())){
                    String message = "The Password Does not match!";
                    loginuser.setAttempts(loginuser.getAttempts()+1);
                    UserDao.save(loginuser);
                    red.addFlashAttribute("message",message);
                    return "redirect:/";
                }else {
                    if (loginuser.getAttempts() >= 7) {
                        return "forgotpassword";
                    } else if (!(loginuser.isVerified())) {
                        return "verify";
                    } else {
                        loginuser.setAttempts(0);
                        UserDao.save(loginuser);
                        session.setAttribute("isAuthenticated",true);
                        session.setAttribute("userId",loginuser.getID());
                        return "redirect:/main";
                    }
                }
            }
        }
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

    @GetMapping("/main")
    public String mainpage(HttpSession session, RedirectAttributes redirectAttributes) {
        if (session != null && session.getAttribute("isAuthenticated") != null) {
            if ((boolean) session.getAttribute("isAuthenticated")) {
                return "main";
            }
        }

        redirectAttributes.addFlashAttribute("message","Try logging in with correct credentials");
        return "redirect:/";
    /*
     * FOR REMEMBERING PASSWORD THING REMEMBER SESSION INSTEAD OF PASSWORD
     *
     *
     *
     */
        }
}

