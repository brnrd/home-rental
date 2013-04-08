package web.controller;

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
    public String homeView(Model model) {
        User user = userService.findByEmail("test");
        model.addAttribute("user", user);
        return "base";
    }
}