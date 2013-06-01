package web.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.Property;
import web.model.Reservation;
import web.model.User;
import web.service.ReservationService;
import web.service.UserService;
import web.utils.StaticMap;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

@Controller
public class ReservationController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ReservationService reservService;

    @RequestMapping(value = "/s/account/{username}/reservations", method = RequestMethod.GET)
    public String reservationView(@PathVariable String username, Model model, Principal current, HttpServletRequest request, HttpServletResponse response) {
        // Specified if the current page is active and set the tab in the navbar.
        model.addAttribute("home", true);
                
        if (current != null && current.getName().equals(username)) {
            User u_log = userService.findByUsername(username);
            if (u_log != null) {
                model.addAttribute("current", u_log);
                
                // Get all Reservations
                List<Reservation> reservations = reservService.findByUser(u_log);
                
                // Sort Reservation by expected and passed.
                ArrayList<Reservation> exp_res = new ArrayList<Reservation>();
                ArrayList<Reservation> pas_res = new ArrayList<Reservation>();
                ArrayList<Property> properties = new ArrayList<Property>();
                for (int i = 0; i < reservations.size(); i++) {
                    Reservation res = reservations.get(i);
                    if (res.getRentStop().isBefore(LocalDateTime.now())) {
                        pas_res.add(res);
                    } else {
                        exp_res.add(res);
                    }
                    
                    // Add property
                    properties.add(res.getProperty());
                }
                
                String pathMap = StaticMap.buildMapURL(properties, null);
                
                // Return the two lists of reservations
                model.addAttribute("reservations", exp_res);
                model.addAttribute("p_reservations", pas_res);
                model.addAttribute("map", pathMap);
            
                return "reservation";
            }
        }
        
        // Return a status 401 : Unauthorize.
        try {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
}