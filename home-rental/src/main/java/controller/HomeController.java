package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Romain <ro.foncier@gmail.com>
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String homeView() {
        return "Home";
    }
}