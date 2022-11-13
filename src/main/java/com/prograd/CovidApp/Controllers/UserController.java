package com.prograd.CovidApp.Controllers;

import com.prograd.CovidApp.Model.User;
import com.prograd.CovidApp.Repository.CenterSearchRepo;
import com.prograd.CovidApp.Repository.CentreRepo;
import com.prograd.CovidApp.Repository.UserRepo;
import com.prograd.CovidApp.Repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserRepo uRepo;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "signin";
    }

    @GetMapping("/signin")
    public String signin() {
        return "redirect:/";
    }

    @PostMapping("/signin")
    public String successLog(@ModelAttribute User user, HttpSession session) {
        boolean f = userService.checkLogin(user.getUsername(), user.getPassword());

        if (!f) {
            session.setAttribute("msg", "Username or password is incorrect");
        }
        else {
            return "redirect:/centres";
        }

        return "redirect:/";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute User user, HttpSession session) {

        boolean f = userService.checkUsername(user.getUsername(), user.getEmail());

        if (f) {
            session.setAttribute("msg", "Email Id already exists");
        }
        else {
            User userdtl = userService.createUser(user);
            if (userdtl != null) {
                session.setAttribute("msg", "Registration Successful");

            } else {
                session.setAttribute("msg", "Something went wrong");
            }
            return "redirect:/signin";
        }

        return "redirect:/register";
    }
}
