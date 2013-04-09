package web.controller;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.Property;
import web.model.Type;
import web.model.User;
import web.service.PropertyService;
import web.service.UserService;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PropertyService propertyService;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homeView(Model model) {
        // Create User
        User u1 = new User("johndoe", "doe", "john", "johndoe@example.com", "test", false);
        userService.saveUser(u1);
        
        // Get User
        User user = userService.findByEmail("johndoe@example.com");
        model.addAttribute("user", user);
        
        // Create property
        Property p1 = new Property("My first property", "Beautiful flat", "View on Central Park", 100, 2, "USA", "NYC", "59th street, 6av.", new LocalDateTime(2013, 4, 1, 0, 0), new LocalDateTime(2013, 9, 1, 0, 0));
        propertyService.saveProperty(p1);
        
        // Get Property
        Property property = propertyService.findById(1);
        model.addAttribute("property", property);
        return "base";
    }
}