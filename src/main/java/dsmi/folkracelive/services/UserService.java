package dsmi.folkracelive.services;

import dsmi.folkracelive.configs.PasswordConfig;
import dsmi.folkracelive.entities.User;
import dsmi.folkracelive.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordConfig encode;

    public String createNewUser(User newUser) throws Exception {
        if (isClubName(newUser.getClubName())) {
            return "Clubname already taken";
        }
        if (isEmail(newUser.getEmail())) {
            return "Email already taken";
        }

        User user = User.builder().clubName(newUser.getClubName())
                .password(encode.encoder().encode(newUser.getPassword()))
                .email(newUser.getEmail())
                .role("USER")
                .build();
        userRepository.save(user);
        return "New user created";
    }

    private boolean isClubName(String clubName) {
        User user = userRepository.findByClubName(clubName);
        return user != null;
    }

    private boolean isEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }
}
