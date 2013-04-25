package web.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
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
    public String searchView(Model model, Principal current, @RequestParam("location") String location, @RequestParam("latlong") String latlong, @RequestParam("checkin") String checkin, @RequestParam("checkout") String checkout, @RequestParam("guests_number") String guests) {
        // Specified if the current page is active and set the tab in the navbar.
        model.addAttribute("home", true);
                
        if (current != null) {
            model.addAttribute("current", current);
        }
        Map types = new HashMap<String, Integer>();
        types.put("FLAT", 10);
        types.put("HOUSE", 8);
        types.put("LOFT", 6);
        
        Map options = new HashMap<String, Integer>();
        options.put("Parking", 10);
        options.put("Wifi", 2);
        options.put("Laundry", 4);
        
        model.addAttribute("params", "[\""+location+"\", \""+checkin+"\", \""+checkout+"\", \""+guests+"\", [0, 300]]");
        model.addAttribute("types", types);
        model.addAttribute("options", options);
        return "search";
    }
}