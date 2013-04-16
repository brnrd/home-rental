package web.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

@Service
@Transactional(readOnly = true)
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User currentUser = userDao.findByUsername(username);

        if (currentUser == null) {
            throw new UsernameNotFoundException("User not found", username);
        }
        return new DefaultUserDetails(currentUser, getAuthorities(currentUser.getRole().getRole()));
    }

    public List<GrantedAuthority> getAuthorities(String role) {
        return  getRoles(role);
    }

    public List<GrantedAuthority> getRoles(String role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (role.equals("ADMIN")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (role.equals("USER")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authorities;
    }
}