package web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.common.StaticMap;
import web.model.Property;
import web.model.User;
import web.service.PropertyService;
import web.service.UserService;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
@RequestMapping("/user")
@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private PropertyService propertyService;
    
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String userView(@PathVariable String username, Model model) {

        // Get User
        User user = userService.findByUsername(username);
        Integer propertyCount = propertyService.findProperty(user).size();
        List<Property> properties = propertyService.findProperty(user);
        Integer evaluation = 0;
        Integer nbEval = 0;
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getNote() != null) {
                evaluation = evaluation + properties.get(i).getNote();
                nbEval++;
            }
        }
        evaluation = evaluation/nbEval;
        String pathMap;
        pathMap = StaticMap.buildMapURL(properties);

        model.addAttribute("user", user);
        model.addAttribute("propertyCount", propertyCount);
        model.addAttribute("map", pathMap);
        model.addAttribute("evaluation", evaluation);
        
        return "user";
    }

}
