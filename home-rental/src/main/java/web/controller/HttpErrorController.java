package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Romain <ro.foncier@gmail.com>
 */
@Controller
public class HttpErrorController {

    @RequestMapping(value = "/error/404")
    public String handle404() {
        return "404";
    }
}