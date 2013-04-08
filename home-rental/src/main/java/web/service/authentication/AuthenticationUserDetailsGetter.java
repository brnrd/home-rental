package web.service.authentication;

/** 
 * @author Romain <ro.foncier@gmail.com>
 */

public class AuthenticationUserDetailsGetter implements UserDetailsService {
    private UserRepository userRepository;
 
    //required by cglib because I use class based aspect weaving
    protected AuthenticationUserDetailsGetter() {
    }
 
    public AuthenticationUserDetailsGetter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
 
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        User user = userRepository.findByLogin(username);
        throwExceptionIfNotFound(user, username);
        return new AuthenticationUserDetails(user);
    }
 
    private void throwExceptionIfNotFound(User user, String login) {
        if (user == null) {
            throw new UsernameNotFoundException("User with login " + login + "  has not been found.");
        }
    }
}