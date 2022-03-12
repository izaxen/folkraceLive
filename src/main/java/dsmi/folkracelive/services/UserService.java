package dsmi.folkracelive.services;

import dsmi.folkracelive.configs.PasswordConfig;
import dsmi.folkracelive.dto.models.UserNoPwDTO;
import dsmi.folkracelive.entities.User;
import dsmi.folkracelive.exceptions.ClubnameAlreadyExist;
import dsmi.folkracelive.exceptions.EmailAlreadyExists;
import dsmi.folkracelive.mapper.UserMapper;
import dsmi.folkracelive.repositories.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
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

    /**
     * Method that creates a new user and encodes PW with bcrypt and saves to DB
     *
     * @param newUser Object containing clubname, password, email
     * @throws Exception if clubname or email already exists
     */
    public void createNewUser(User newUser) throws Exception {
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
        userMapper.UserNoPwDTO(userRepository.save(user));
    }

    /**
     * Method that convert the incoming image file to base64 and saves it to the DB
     *
     * @param file Image file
     * @param user Userdetails from token
     * @return updated user
     * @throws IOException when converting base64
     */
    public UserNoPwDTO updateClubImage(MultipartFile file, CustomUserDetails user) throws IOException {


        byte[] image = Base64.encodeBase64(file.getBytes());

        user.getUser().setClubImage(new String(image));
        return userMapper.UserNoPwDTO(userRepository.save(user.getUser()));
    }

    /**
     * Method that set clubimage to null
     *
     * @param user Userdetails from token
     * @return updated user
     */
    public UserNoPwDTO deleteClubImage(CustomUserDetails user) {
        user.getUser().setClubImage(null);
        return userMapper.UserNoPwDTO(userRepository.save(user.getUser()));
    }

    /**
     * Method that controls if clubname exist in Db
     *
     * @param clubName String
     * @return boolean
     */
    private boolean isClubName(String clubName) {
        User user = userRepository.findByClubName(clubName);
        return user != null;
    }

    /**
     * Method that controls if email exists in DB
     *
     * @param email String
     * @return boolean
     */
    private boolean isEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    /**
     * Metod that updates clubdetails. Only changed parameters comes through restcontroller and these will update here
     *
     * @param userDetails    from token to set user
     * @param updatedDetails new values from Map.
     * @throws EmailAlreadyExists if email already taken
     */
    public void updateUser(CustomUserDetails userDetails, Map<String, String> updatedDetails) throws EmailAlreadyExists {
        User user = userDetails.getUser();
        if (updatedDetails.containsKey("email")) {
            if (isEmail(updatedDetails.get("email"))) throw new EmailAlreadyExists();
            user.setEmail(updatedDetails.get("email"));
        }
        if (updatedDetails.containsKey("password"))
            user.setPassword(encode.encoder().encode(updatedDetails.get("password")));
        userRepository.save(user);
    }
}
