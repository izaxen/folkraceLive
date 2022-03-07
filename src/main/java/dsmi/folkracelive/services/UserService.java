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

    public void createNewUser(User newUser) {
        User user = User.builder().clubName(newUser.getClubName())
                .password(encode.encoder().encode(newUser.getPassword()))
                .email(newUser.getEmail())
                .role("USER")
                .build();


        userRepository.save(user);

    }
}
