package com.prograd.CovidApp.Controllers;

import com.prograd.CovidApp.Model.Centre;
import com.prograd.CovidApp.Model.User;
import com.prograd.CovidApp.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CentreController {
    @Autowired
    private CenterSearchRepo cSRepo;
    @Autowired
    private CentreRepo cRepo;
    @Autowired
    private UserSearchRepo uRepo;

    @GetMapping(value = "/centres")
    public List<Centre> getCentres() {
        return cRepo.findAll();
    }

    @GetMapping(value="/centres/{centre}")
    public Centre getCentre(@PathVariable String centre) {
        return cSRepo.findByName(centre);
    }

    @GetMapping("/centres/{user}/bookcentre/{centre}")
    public Centre bookCentre(@PathVariable String user, @PathVariable String centre) {
        List<Centre> centres = getCentres();
        int totalAval = 0, currAval = 0;
        for(Centre c: centres) {
            totalAval+=c.getTotalAvailability();
            currAval+=c.getCurrentAvailability();
        }
        if(totalAval-currAval>=10) {
            return null;
        }
        Centre cen = null;
        try {
        cen = cSRepo.findByName(centre);
            try {
                cen.getCandidates().add(uRepo.findByUsername(user));
            }
            catch (Exception e) {
                System.out.println("Error in finding user");
                return null;
            }
        }
        catch (Exception e) {
            System.out.println("Error in finding centre");
            return null;
        }
        int n = cen.getCurrentAvailability();
        cen.setCurrentAvailability(n-1);
        return cRepo.save(cen);
    }
}
