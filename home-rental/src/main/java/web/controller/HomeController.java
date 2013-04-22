package web.controller;

import java.security.Principal;
import java.util.List;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.Comment;
import web.model.Evaluation;
import web.model.Property;
import web.model.PropertyOptions;
import web.model.PropertyType;
import web.model.Reservation;
import web.model.Role;
import web.model.User;
import web.service.CommentService;
import web.service.EvaluationService;
import web.service.PropertyOptionsService;
import web.service.PropertyService;
import web.service.ReservationService;
import web.service.UserService;
import web.utils.PasswordHasher;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PropertyService propertyService;
    
    @Autowired
    private PropertyOptionsService propertyOptionsService;
    
    @Autowired
    private EvaluationService evalService;
    
    @Autowired
    private CommentService comService;
    
    @Autowired
    private ReservationService reservService;
    
    private PasswordHasher hash = new PasswordHasher();
    
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
    
    @RequestMapping(value = "/search/", method = RequestMethod.GET)
    public String searchView(Model model) {
        return "search";
    }
    
    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public String userView(@PathVariable String username, Model model) {
         
        // Get User
        User user = userService.findByUsername(username);
        Integer propertyCount = propertyService.findProperty(user).size();
        
        model.addAttribute("user", user);
        model.addAttribute("propertyCount", propertyCount);
        return "user";
    }
    
    @RequestMapping(value = "/property/{id}", method = RequestMethod.GET)
    public String propertyView(@PathVariable Integer id, Model model) {
         
        // Get Property
        Property property = propertyService.findById(id);
        PropertyOptions options = propertyOptionsService.findByProperty(property);
        Evaluation evaluation = evalService.findByProperty(property);
        Integer totalEval = (evaluation.getCleanliness() + evaluation.getConfort() + evaluation.getQaPrice())/3;
        
        model.addAttribute("property", property);
        model.addAttribute("options", options);
        model.addAttribute("totalEval", totalEval);
        return "property";
    }
}