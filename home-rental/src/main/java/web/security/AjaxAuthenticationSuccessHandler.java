package web.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Service;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

@Service
public class AjaxAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {   
        
    public AjaxAuthenticationSuccessHandler() {}
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {  
         
        System.out.println("Start success handler");
        HttpSession session = request.getSession();     
        DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) session.getAttribute(WebAttributes.ACCESS_DENIED_403);
        //check if login is originated from ajax call
        System.out.println("Start success handler - before check header");
        if ("true".equals(request.getHeader("X-Ajax-call"))) {
            try {
                System.out.println("Check header success");
                response.getWriter().print("success");
                response.getWriter().flush();
        } catch (IOException e) {               
           //handle exception...
        }
        } else {
            System.out.println("Check header error");
            setAlwaysUseDefaultTargetUrl(false);        
        }
    }
}