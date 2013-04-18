package web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import web.model.User;
import web.service.UserService;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

@Controller
public class AuthController {

    @Autowired
    @Qualifier("authenticationManager")
    AuthenticationManager authenticationManager;
    
    @Autowired
    SecurityContextRepository repository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    RememberMeServices rememberMeServices;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginProcess(@RequestParam("auth_username") String username, 
                                        @RequestParam("auth_pwd") String password,
                                        HttpServletRequest request, HttpServletResponse response) {
        
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            //repository.saveContext(SecurityContextHolder.getContext(), request, response);
            rememberMeServices.loginSuccess(request, response, auth);
            return "redirect:/";
        } catch (BadCredentialsException ex) {
            return "login";
        }
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup() {
        return "signup";
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupProcess(@RequestParam("auth_username") String username, 
                                        @RequestParam("auth_pwd") String password,
                                        HttpServletRequest request, HttpServletResponse response) {
        
        // Create new player and add in context
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            //repository.saveContext(SecurityContextHolder.getContext(), request, response);
            rememberMeServices.loginSuccess(request, response, auth);
            return "redirect:/";
        } catch (BadCredentialsException ex) {
            return "signup";
        }
    }
}