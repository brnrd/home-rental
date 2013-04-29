package web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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
import web.service.UserService;

/**
 * @author Romain Foncier <ro.foncier at gmail.com>
 */
@Controller
//@WebServlet(urlPatterns = "/login-ajax", asyncSupported = true)
public class AuthAjaxController extends HttpServlet {

    @Autowired
    @Qualifier("authenticationManager")
    AuthenticationManager authenticationManager;
    @Autowired
    SecurityContextRepository repository;
    @Autowired
    private UserService userService;

    /*
     public void doGet(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
     AsyncContext asyncCtx = request.startAsync();
     ServletRequest req = asyncCtx.getRequest();
     if (req.isAsyncStarted()) {
     asyncCtx.dispatch("ajax-login.html");
     }
     }

     public void doPost(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
     response.setContentType("text/json");
     PrintWriter out = response.getWriter();
     out.print("{\"status\": \"success\"}");
     }*/
    @RequestMapping(value = "/login-ajax", method = RequestMethod.GET)
    public String loginAjax() {
        return "ajax-login";
    }

    @RequestMapping(value = "/login-ajax", method = RequestMethod.POST)
    public @ResponseBody
    String loginAjaxProcess(@RequestParam("auth_username") String username,
            @RequestParam("auth_pwd") String password,
            HttpServletRequest request, HttpServletResponse response, Model model) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            repository.saveContext(SecurityContextHolder.getContext(), request, response);

            // Update last connection
            userService.connected(userService.findByUsername(username));
            return "{\"status\": \"success\"}";
        } catch (BadCredentialsException ex) {
            return "{\"status\": \"error\"}";
            //return "login";
        }
    }
}