package web.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.SearchResult;
import web.service.PropertyService;
import web.service.SearchService;
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
    
    @Autowired
    private SearchService searchService;
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchView(Model model, Principal current, @RequestParam("location") String location, @RequestParam("latlong") String latlong, @RequestParam("checkin") String checkin, @RequestParam("checkout") String checkout, @RequestParam("guests_number") String guests) {
        // Specified if the current page is active and set the tab in the navbar.
        model.addAttribute("home", true);
                
        if (current != null) {
            model.addAttribute("current", current);
        }
        
        // Check results from Search Service.
        DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
        LocalDateTime s_checkin = formatter.parseLocalDateTime(checkin + " 00:00:00");
        LocalDateTime s_checkout = formatter.parseLocalDateTime(checkout + " 00:00:00");
        Integer s_guests = Integer.parseInt(guests);
        
        // Define which kind of request must be call.
        String[] dest = location.split("");
        
        List<SearchResult> res = searchService.searchForSpecificCountry("USA", new LocalDateTime(2013, 04, 25, 0, 0), new LocalDateTime(2013, 04, 26, 0, 0), 2);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i) instanceof SearchResult);
            System.out.println(res.get(i).getClass().getName());
            //System.out.println(p.getPropertyId()+" - "+p.getPropertyTitle()+" - "+p.getPropertyCity());
        }
        
        // Tests
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