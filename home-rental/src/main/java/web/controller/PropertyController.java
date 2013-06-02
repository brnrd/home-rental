package web.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
import web.model.User;
import web.service.CommentService;
import web.service.EvaluationService;
import web.service.PropertyOptionsService;
import web.service.PropertyService;
import web.service.ReservationService;
import web.service.UserService;
import web.utils.StaticMap;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>, Romain <ro.foncier@foncier.com>
 */
@Controller
public class PropertyController {

    @Autowired
    private PropertyService propertyService;
    @Autowired
    private PropertyOptionsService optionsService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private EvaluationService evaluationService;
    
    
    /**
     * Get the property view
     * @param id the property id
     * @param model the model that receive the data to generate the template
     * @param current the current user
     * @return the property String that gonna call the property page.
     */
    @RequestMapping(value = "/property/{id}", method = RequestMethod.GET)
    public String propertyView(@PathVariable Integer id, Model model, Principal current, 
        HttpServletRequest request, HttpServletResponse response) {
        // Specified if the current page is active and set the tab in the navbar.
        model.addAttribute("home", true);
        
        // Get Property by its id
        Property property = propertyService.findById(id);
        if (property == null) {
            try {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return null;
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        
        // Get option of this property
        PropertyOptions options = optionsService.findByProperty(property);
        // Get the comments of this property
        List<Comment> comments = commentService.findByProperty(property);
        // Extract the note from the property object
        Integer evaluation = property.getNote();
        // Generate the static map url based on the latitude and longitude of the property
        String pathMap = StaticMap.buildMapURL(new Double(property.getLatitude()).toString() + "," + new Double(property.getLongitude()).toString(), null);
        
        List<String> pictures = new LinkedList<String>();
        for (int i = 1; i < 4; i++) {
            pictures.add(new Integer(i).toString() + ".jpg");
        }
        // If there the user calling the page is logged
        if (current != null) {
            User u_log = userService.findByUsername(current.getName());
            model.addAttribute("current", u_log);
            model.addAttribute("isOwnerCurrent", u_log.getId().equals(property.getOwner().getId()));
        }
        
        // Add the attributes to the model
        model.addAttribute("property", property);
        model.addAttribute("options", options);
        model.addAttribute("evaluation", evaluation);
        model.addAttribute("comments", comments);
        model.addAttribute("map", pathMap);
        model.addAttribute("pictures", pictures);

        return "property";
    }

    /**
     * Get the new property view. Only for logged users.
     * @param model the model that receive the data to generate the template
     * @param current the current user
     * @return the new property String that gonna call the property page.
     */
    @RequestMapping(value = "/s/property/new", method = RequestMethod.GET)
    public String newView(Model model, Principal current, HttpServletRequest request, HttpServletResponse response) {
        if (current != null) {
            User u_log = userService.findByUsername(current.getName());
            model.addAttribute("current", u_log);
        
            // Create a new Property
            Property property = new Property();
            // Create a new PropertyOption
            PropertyOptions options = new PropertyOptions();
            // Twist to get the rentPeriodStart and Stop
            String rentStart = null;
            String rentStop = null;
            // Get the different existing types of property
            List<PropertyType> types = Arrays.asList(PropertyType.values());

            // Add the attributes to the model
            model.addAttribute("property", property);
            model.addAttribute("options", options);
            model.addAttribute("types", types);
            model.addAttribute("rentStart", rentStart);
            model.addAttribute("rentStop", rentStop);
            
            return "new_property";
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

    /**
     * POST new property. Only for logged users.
     * @param property the property object
     * @param options the options
     * @param rentStart the String with the rent start date
     * @param rentStop the String with the rent stop date
     * @param bindingResult 
     * @param model the model that receive the data to generate the template
     * @param current the current user
     * @return 
     */
    @RequestMapping(value = "/s/property/new", method = RequestMethod.POST)
    public String saveProperty(final Property property, final PropertyOptions options, final String rentStart, final String rentStop, Principal current, HttpServletRequest request,  HttpServletResponse response) {

        if (current != null) {
            property.setOwner(userService.findByUsername(current.getName()));
        
            DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
            DateTime rentStartTmp = formatter.parseDateTime(rentStart + " 00:00:00");
            DateTime rentStopTmp = formatter.parseDateTime(rentStop + " 00:00:00");
            property.setRentPeriodStart(rentStartTmp.toLocalDateTime());
            property.setRentPeriodStop(rentStopTmp.toLocalDateTime());

            // Handle new property
            propertyService.saveProperty(property);
            options.setProperty(property);
            optionsService.savePropertyOptions(options);
            
            return "redirect:/property/" + property.getId();
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

    /**
     * Update property informations. Only for owner of this property.
     * @param property, uOptions, rentStart, rentStop, id, current
     * @return 
     */
    @RequestMapping(value = "/s/property/{id}/update", method = RequestMethod.POST)
    public String updateProperty(@PathVariable Integer id, final Property uProperty, final PropertyOptions uOptions, final String rentStart, final String rentStop, Principal current, HttpServletRequest request,  HttpServletResponse response) {
        if (current != null) {
            User u_log = userService.findByUsername(current.getName());
            // Get property
            Property property = propertyService.findById(id);
            if (property != null && property.getOwner().getId().equals(u_log.getId())) {
                property.setAddress(uProperty.getAddress());
                property.setCity(uProperty.getCity());
                property.setCountry(uProperty.getCountry());
                property.setPrice(uProperty.getPrice());
                property.setRooms(uProperty.getRooms());
                property.setShortDesc(uProperty.getShortDesc());
                property.setTitle(uProperty.getTitle());
                property.setType(uProperty.getType());
                property.setLongDesc(uProperty.getLongDesc());

                DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
                DateTime rentStartTmp = formatter.parseDateTime(rentStart + " 00:00:00");
                DateTime rentStopTmp = formatter.parseDateTime(rentStop + " 00:00:00");
                property.setRentPeriodStart(rentStartTmp.toLocalDateTime());
                property.setRentPeriodStop(rentStopTmp.toLocalDateTime());

                PropertyOptions options = optionsService.findByProperty(property);
                options.setLaundry(uOptions.getLaundry());
                options.setParking(uOptions.getParking());
                options.setSwimmingPool(uOptions.getSwimmingPool());
                options.setWifi(uOptions.getWifi());

                // Handle new property
                propertyService.saveProperty(property);
                optionsService.savePropertyOptions(options);

                return "redirect:/property/" + property.getId();
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

    /**
     * Delete property and all its informations. Only for owner of this property.
     * @param id, current
     * @return 
     */
    @RequestMapping(value = "/s/property/{id}/delete", method = RequestMethod.POST)
    public String deleteProperty(@PathVariable Integer id, Principal current, HttpServletRequest request,  HttpServletResponse response) {
        if (current != null) {
            User u_log = userService.findByUsername(current.getName());
            // Get property
            Property property = propertyService.findById(id);
            if (property != null && property.getOwner().getId().equals(u_log.getId())) {
                
                // Delete all options
                PropertyOptions options = optionsService.findByProperty(property);
                if (options != null) {
                    optionsService.deletePropertyOptions(options.getId());
                }
                
                // Delete comments
                List<Comment> comments = commentService.findByProperty(property);
                if (comments != null) {
                    for (int j = 0; j < comments.size(); j++) {
                        Comment comment = comments.get(j);
                        commentService.deleteComment(comment.getId());
                    }
                }
                
                // Delete reservations
                List<Reservation> reservations = reservationService.findByProperty(property);
                if (reservations != null) {
                    for (int j = 0; j < reservations.size(); j++) {
                        Reservation reservation = reservations.get(j);
                        reservationService.deleteReservation(reservation.getId());
                    }
                }
                
                // Delete evaluations
                Evaluation evaluation = evaluationService.findByProperty(property);
                if (evaluation != null) {
                    evaluationService.deleteEvaluation(evaluation.getId());
                }
                
                // Delete instance
                propertyService.deleteProperty(property.getId());

                return "redirect:/s/" + u_log.getUsername() + "/properties";
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
    
    // Mapping for dynamically load the property form within modal during the edition operations
    @RequestMapping(value = "/s/property/{id}/modal", method = RequestMethod.GET)
    public String propertyModalView(@PathVariable Integer id, Model model) {
        
        Property property = propertyService.findById(id);
        PropertyOptions options = optionsService.findByProperty(property);
        List<PropertyType> types = Arrays.asList(PropertyType.values());
        
        DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy");
        String rentStart = property.getRentPeriodStart().toString(formatter);
        String rentStop = property.getRentPeriodStop().toString(formatter);
        
        model.addAttribute("property", property);
        model.addAttribute("options", options);
        model.addAttribute("types", types);
        model.addAttribute("rentStart", rentStart);
        model.addAttribute("rentStop", rentStop);
        
        return "property_modal";
    }
}
