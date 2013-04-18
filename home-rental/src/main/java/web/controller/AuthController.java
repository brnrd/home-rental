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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
        return "auth/login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String loginProcess(@RequestParam("auth_username") String username, 
                                        @RequestParam("auth_pwd") String password,
                                        HttpServletRequest request, HttpServletResponse response) {
        
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        try {
            System.out.println("Before authenticate");
            Authentication auth = authenticationManager.authenticate(token);
            System.out.println("After authenticate");
            SecurityContextHolder.getContext().setAuthentication(auth);
            repository.saveContext(SecurityContextHolder.getContext(), request, response);
            rememberMeServices.loginSuccess(request, response, auth);
            return "{\"status\": \"succes\", \"msg\": \"Welcome ! You are successfully logged !\"}";
        } catch (BadCredentialsException ex) {
            return "{\"status\": \"error\", \"msg\": \"Warning ! Your login/password are incorrects !\"}";
        }
    }
}