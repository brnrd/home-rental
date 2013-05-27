package web.controller;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import web.model.Property;
import web.model.Reservation;
import web.model.User;
import web.service.PropertyService;
import web.service.ReservationService;
import web.service.UserService;

/**
 * @author Romain Foncier <ro.foncier at gmail.com>
 */
@Controller
public class BookingController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private PropertyService propService;
    
    @Autowired
    private ReservationService resService;

    @RequestMapping(value = "/s/booking", method = RequestMethod.POST)
    public @ResponseBody
    String bookingProcess(@RequestParam("target_property") Integer property_id,
            @RequestParam("date_rent_start") String checkin, 
            @RequestParam("date_rent_stop") String checkout,
            @RequestParam("guests_number") Integer guests,
            @RequestParam("price") Integer price,
            HttpServletRequest request, HttpServletResponse response, Principal current) {

        DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
        LocalDateTime s_checkin = formatter.parseLocalDateTime(checkin + " 00:00:00");
        LocalDateTime s_checkout = formatter.parseLocalDateTime(checkout + " 00:00:00");
        
        // Create a new reservation and save it.
        User booker = userService.findByUsername(current.getName());
        Property target_booking = propService.findById(property_id);
        if (booker != null && target_booking != null) {
            Reservation booking = new Reservation(booker, target_booking, s_checkin, s_checkout, guests, price);
            resService.saveReservation(booking);
            return "{\"status\": \"success\"}";
        }
        return "{\"status\": \"error\"}";
    }
}