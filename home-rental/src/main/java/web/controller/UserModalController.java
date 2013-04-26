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
public class UserModalController {
    
    @Autowired
    private UserService userService;
    
     @RequestMapping(value = "/s/account/modal/", method = RequestMethod.GET)
     public String userModalView(Model model, Principal current) {
         
         if (current != null) {
            User u_log = userService.findByUsername(current.getName());
            model.addAttribute("user", u_log);
        }
         
         return "user_modal";
     }
}
