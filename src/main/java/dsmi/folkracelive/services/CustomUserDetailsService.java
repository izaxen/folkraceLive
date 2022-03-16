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

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method that looking in the DB if user exist and creates a user and returns it in a Customeruserdetails
     * @param clubName String
     * @return Customerdetailsuser
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String clubName) throws UsernameNotFoundException {

        User user = userRepository.findByClubName(clubName);
        if(user == null){
            throw new UsernameNotFoundException("Clubname not found");
        }
        return new CustomUserDetails(user);
    }
}
