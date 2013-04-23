package web.controller;

import java.security.Principal;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.User;
import web.service.UserService;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
                
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homeView(Model model, Principal current) {
        // Specified if the current page is active and set the tab in the navbar.
        model.addAttribute("home", true);
                
        if (current != null) {
            System.out.println(current.getName());
            User u_log = userService.findByUsername(current.getName());
            if (u_log.getCreated().plusSeconds(60).isAfter(LocalDateTime.now())) {
                model.addAttribute("new_user", true);
            } else  {
                if (u_log.getLastConnection().plusSeconds(60).isAfter(LocalDateTime.now())) {
                    System.out.println("logged user");
                    model.addAttribute("logged_user", true);
                }
            }
            
            model.addAttribute("current", u_log);
        }
        return "home";
    }
}