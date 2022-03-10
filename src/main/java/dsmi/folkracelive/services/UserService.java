package dsmi.folkracelive.services;

import dsmi.folkracelive.configs.PasswordConfig;
import dsmi.folkracelive.dto.models.UserNoPwDTO;
import dsmi.folkracelive.entities.User;
import dsmi.folkracelive.exceptions.ClubnameAlreadyExist;
import dsmi.folkracelive.exceptions.EmailAlreadyExists;
import dsmi.folkracelive.mapper.UserMapper;
import dsmi.folkracelive.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordConfig encode;

    public UserNoPwDTO createNewUser(User newUser) throws Exception {
        if (isClubName(newUser.getClubName())) {
            throw new ClubnameAlreadyExist();
        }
        if (isEmail(newUser.getEmail())) {
            throw new EmailAlreadyExists();
        }

        User user = User.builder().clubName(newUser.getClubName())
                .password(encode.encoder().encode(newUser.getPassword()))
                .email(newUser.getEmail())
                .role("USER")
                .build();

        return userMapper.UserNoPwDTO(userRepository.save(user));
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
