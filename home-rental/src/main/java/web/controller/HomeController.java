package web.controller;

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
import web.model.User;
import web.service.CommentService;
import web.service.EvaluationService;
import web.service.PropertyOptionsService;
import web.service.PropertyService;
import web.service.ReservationService;
import web.service.UserService;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

@Controller
public class HomeController {
    
    private final static String BASE_URL = "http://maps.googleapis.com/maps/api/staticmap?";
    private final static String ZOOM = "zoom=10";
    private final static String SIZE = "size=600x300";
    private final static String KEY = "key=AIzaSyCIE1oJyR-XDU30PRTTMhadZtRVI1Spf7I";
    private final static String MARKER = "markers=";
    private final static String MARKER_SEPARATOR = "%7C";
    private final static String COLOR_RED = "color:red";
    private final static String LABEL = "label:";
    private final static String SENSOR = "sensor=false";
    private final static String SEPARATOR = "&";

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
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homeView(Model model) {
        /*
        // Create User
        User u1 = new User("johndoe", "doe", "john", "johndoe@example.com", "test", false);
        userService.saveUser(u1);
        
        // Create User
        //User u1 = new User("johndoe", "doe", "john", "johndoe@example.com", "test", false);
        //userService.saveUser(u1);
       
        // Get User
        User user = userService.findByEmail("johndoe@example.com");
        model.addAttribute("user", user);
        
        // Create property
        //Property p1 = new Property(user, "My first property", "Beautiful flat", "View on Central Park", 100, PropertyType.FLAT, 2, "USA", "NYC", "59th street, 6av.", new LocalDateTime(2013, 4, 1, 0, 0), new LocalDateTime(2013, 9, 1, 0, 0));
        //propertyService.saveProperty(p1);
        
        // Get Property
        Property property = propertyService.findById(1);
        model.addAttribute("property", property);
        
        // Create property_options
        //PropertyOptions po1 = new PropertyOptions(property, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE);
        //propertyOptionsService.savePropertyOptions(po1);
        //PropertyOptions po2 = new PropertyOptions(property, false, false, true, true);
        //propertyOptionsService.savePropertyOptions(po2);
        
        // Get Property
        PropertyOptions pOptions1 = propertyOptionsService.findByProperty(property);
        model.addAttribute("po1", pOptions1);
        
        // Create evaluation
        //Evaluation ev1 = new Evaluation(property, 80, 80, 80);
        //evalService.saveEvaluation(ev1);
        
        // Get evaluation
        Evaluation ev2 = evalService.findByProperty(property);
        model.addAttribute("ev", ev2);
        
        // Create comment
        //Comment com1 = new Comment(user, property, "Test first comment message !!!");
        //comService.saveComment(com1);
        
        // Get comment
        Comment com2 = comService.findByProperty(property);
        //com2.setMessage("Test comment message updated !!!");
        //comService.saveComment(com2);
        Comment com3 = comService.findByUser(user).get(0);
        model.addAttribute("com2", com2);
        model.addAttribute("com3", com3);
        
        // Delete comment
        //Comment com1 = new Comment(user, property, "Test comment message");
        //comService.saveComment(com1);
        //comService.deleteComment(com1.getId());
        
        // Create reservation
        //Reservation res1 = new Reservation(user, property, new LocalDateTime(2013, 4, 20, 12, 0), new LocalDateTime(2013, 4, 27, 12, 0), 2, 200);
        //reservService.saveReservation(res1);
        
        // Get reservation
        Reservation res2 = reservService.findByProperty(property).get(0);
        Reservation res3 = reservService.findByUser(user).get(0);
        model.addAttribute("res2", res2);
        model.addAttribute("res3", res3);
        */
        
        // Specified if the current page is active and set the tab in the navbar.
        model.addAttribute("home", true);
        return "home";
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
        String pathMap;
        pathMap = buildMapURL(property);
        
        model.addAttribute("property", property);
        model.addAttribute("options", options);
        model.addAttribute("totalEval", totalEval);
        model.addAttribute("map", pathMap);
        return "property";
    }
    
    private String buildMapURL(List<Property> properties) {
        StringBuilder url = new StringBuilder();
        url.append(BASE_URL);
        url.append(ZOOM);
        url.append(SEPARATOR);
        url.append(SIZE);
        url.append(SEPARATOR);
        url.append(KEY);
        url.append(SEPARATOR);
        url.append(addMarkers(properties));
        url.append(SEPARATOR);
        url.append(SENSOR);
        return url.toString();
    }
    
    private String buildMapURL(Property property) {
        StringBuilder url = new StringBuilder();
        url.append(BASE_URL);
        url.append(ZOOM);
        url.append(SEPARATOR);
        url.append(SIZE);
        url.append(SEPARATOR);
        url.append(KEY);
        url.append(addMarkers(property));
        url.append(SEPARATOR);
        url.append(SENSOR);
        return url.toString();
    }

    private String addMarkers(List<Property> properties) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < properties.size(); i++) {
            if (i > 0) {
                string.append(SEPARATOR);
            }
            if (properties.get(i).getCoordinates() != null) {
                string.append(SEPARATOR);
                string.append(MARKER);
                string.append(COLOR_RED);
                string.append(MARKER_SEPARATOR);
                string.append(LABEL);
                string.append(i);
                string.append(MARKER_SEPARATOR);
                string.append(properties.get(i).getCoordinates());
//            } else {
//                string.append(properties.get(i).getAddress());
            }
        }
        return string.toString();
    }

    private String addMarkers(Property property) {
        StringBuilder string = new StringBuilder();
        if (property.getCoordinates() != null) {
            string.append(SEPARATOR);
            string.append(MARKER);
            string.append(COLOR_RED);
            string.append(MARKER_SEPARATOR);
            string.append(LABEL);
            string.append(1);
            string.append(MARKER_SEPARATOR);
            string.append(property.getCoordinates());
//            } else {
//                string.append(properties.get(i).getAddress());
        }
        return string.toString();
    }
    
}