package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import web.service.UserService;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/login/", method = RequestMethod.POST)
    public @ResponseBody String loginView(Model model) {
        return "login";
    }
}