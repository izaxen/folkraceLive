package dsmi.folkracelive.services;

import dsmi.folkracelive.configs.PasswordConfig;
import dsmi.folkracelive.dto.models.UserNoPwDTO;
import dsmi.folkracelive.entities.User;
import dsmi.folkracelive.exceptions.ClubnameAlreadyExist;
import dsmi.folkracelive.exceptions.EmailAlreadyExists;
import dsmi.folkracelive.mapper.UserMapper;
import dsmi.folkracelive.repositories.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordConfig encode;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordConfig encode) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encode = encode;
    }


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

    public UserNoPwDTO updateClubImage(MultipartFile file, CustomUserDetails user) throws IOException {

        byte[] image = Base64.encodeBase64(file.getBytes());
        String result = new String(image);
        System.out.println(result);
        user.getUser().setClubImage(result);
        return userMapper.UserNoPwDTO(userRepository.save(user.getUser()));

    }

    public UserNoPwDTO deleteClubImage(CustomUserDetails user){
        user.getUser().setClubImage(null);
        return userMapper.UserNoPwDTO(userRepository.save(user.getUser()));
    }

    private boolean isClubName(String clubName) {
        User user = userRepository.findByClubName(clubName);
        return user != null;
    }

    private boolean isEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    public void updateUser(CustomUserDetails userDetails, Map<String, String> updatedDetails) throws EmailAlreadyExists {
        User user = userDetails.getUser();
        if(updatedDetails.containsKey("email")) {
            if(isEmail(updatedDetails.get("email"))) throw new EmailAlreadyExists();
            user.setEmail(updatedDetails.get("email"));
        }
        if(updatedDetails.containsKey("password")) user.setPassword(encode.encoder().encode(updatedDetails.get("password")));
        userRepository.save(user);
    }
}
