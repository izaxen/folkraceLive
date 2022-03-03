package dsmi.folkracelive.services;

import dsmi.folkracelive.entities.User;
import dsmi.folkracelive.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByClubName(username);
        if(user == null){
            throw new UsernameNotFoundException("Username not found");
        }
        return new CustomUserDetails(user);
    }
}
