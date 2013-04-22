package web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import web.model.Role;
import web.model.User;
import web.service.UserService;
import web.utils.Utilities;

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
                                        HttpServletRequest request, HttpServletResponse response, Model model) {
        
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            repository.saveContext(SecurityContextHolder.getContext(), request, response);
            rememberMeServices.loginSuccess(request, response, auth);
            return "redirect:/";
        } catch (BadCredentialsException ex) {
            model.addAttribute("login_error", true);
            return "login";
        }
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        model.addAttribute("userData", new User());
        return "signup";
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupProcess(@ModelAttribute("userData") User userData, BindingResult bindingResult, Model model) {
        
        // Check form integrity
        List<String> formError = Utilities.checkForm(userData);
        if (formError != null) {
            for  (String e : formError) {
                model.addAttribute(e, true);
            }
            return "signup";
        }
        
        // Check if the username already exists (TODO : Implement an ajax control later)
        if (userService.findByUsername(userData.getUsername()) != null) {
            model.addAttribute("username_duplicate_error", true);
            return "signup";
        }
        
        // Create a new Account
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
        User newAccount = new User(userData.getUsername(), userData.getName(), userData.getFirstname(), userData.getEmail(), encoder.encodePassword(userData.getPassword(), ""), Role.USER);
        userService.saveUser(newAccount);
        
        // Log the new User
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userData.getUsername(), userData.getPassword());
        Authentication auth = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return "redirect:/";
    }
}