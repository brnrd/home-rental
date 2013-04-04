package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Romain <ro.foncier@gmail.com>
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public ModelAndView homeView(@RequestParam(required = false, defaultValue = "") String name) {
        ModelAndView mav = new ModelAndView("results");
        return mav;
    }
}