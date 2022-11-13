package com.prograd.CovidApp.Controllers;

import com.prograd.CovidApp.Model.Centre;
import com.prograd.CovidApp.Model.User;
import com.prograd.CovidApp.Repository.CenterSearchRepo;
import com.prograd.CovidApp.Repository.UserRepo;
import com.prograd.CovidApp.Repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserRepo uRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private CenterSearchRepo cSRepo;

    @GetMapping("/admlogin")
    public String admLogin() {
        return "adminLogin";
    }

    @PostMapping("/admlogin")
    public String successAdmLog(@ModelAttribute User user) {
        boolean f = userService.checkAdmin(user.getUsername());
        if(f) {
            return "Admin";
        }
        else {
            return "redirect:/";
        }
    }

    @GetMapping("/addcentre")
    public String addCentre() {
        return "updateCentre";
    }

    @PostMapping("/addcentre")
    public String successAdd(@ModelAttribute Centre centre) {
        try {
            cSRepo.addCentre(centre);
        }
        catch(Exception ignored){}
        return "Admin";
    }

    @GetMapping("/removecentre")
    public String removeCentre() {
        return "removeCentre";
    }

    @PostMapping("/removecentre/{centre}")
    public String successRemove(String centre) {
        try {
            Centre cen = cSRepo.findByName(centre);
            cSRepo.removeCentre(cen);
        }
        catch(Exception ignored){}
        return "Admin";
    }

    @ResponseBody
    @GetMapping("/getdosage")
    public List<Centre> successDosage(@ModelAttribute Centre centre) {
        return cSRepo.getDosage(centre.getDosage());
    }
}
