package web.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.User;
import web.service.UserService;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
@Controller
public class FouroFourController {
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String fourHundrerAndFour(Model model, Principal current) {
        if (current != null) {
//            Find the user by its username
            User u_log = userService.findByUsername(current.getName());
//            Add the current value of the model with the user
            model.addAttribute("current", u_log);
        }
        return "404";
    }
    
}
