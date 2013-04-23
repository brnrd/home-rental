package web.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.PropertyService;
import web.service.UserService;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

@Controller
public class SearchController {

    @Autowired
    PropertyService propertyService;
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchView(Model model, Principal current, @RequestParam("location") String location, @RequestParam("checkin") String checkin, @RequestParam("checkout") String checkout, @RequestParam("guests_number") String guests) {
        // Specified if the current page is active and set the tab in the navbar.
        model.addAttribute("home", true);
                
        if (current != null) {
            model.addAttribute("current", current);
        }
        System.out.println(location);
        System.out.println(checkin);
        System.out.println(checkout);
        System.out.println(guests);
        return "search";
    }
}