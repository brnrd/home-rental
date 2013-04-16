package web.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import web.model.User;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

public class DefaultUserDetails implements UserDetails {
    private Long id;
    private final String login;
    private final String passwordHash;
    private final boolean enabled;
    private Collection<GrantedAuthority> grantedAuthorities;
 
    public DefaultUserDetails(User user, Collection<GrantedAuthority> authorities) {
        this.login = user.getUsername();
        this.passwordHash = user.getPassword();
        this.enabled = user.isEnabled();
        this.grantedAuthorities = authorities;
    }
 
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }
 
    @Override
    public String getPassword() {
        return passwordHash;
    }
 
    @Override
    public String getUsername() {
        return login;
    }
 
    @Override
    public boolean isEnabled() {
        return enabled;
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    public String getLogin() {
        return login;
    }
}