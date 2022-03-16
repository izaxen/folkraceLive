package dsmi.folkracelive.controllers;


import dsmi.folkracelive.dto.jwt.JWTLogin;
import dsmi.folkracelive.dto.jwt.JWTResponse;
import dsmi.folkracelive.dto.models.UserNoPwDTO;
import dsmi.folkracelive.entities.User;
import dsmi.folkracelive.exceptions.EmailAlreadyExists;
import dsmi.folkracelive.jwt.JWTAuthenticate;
import dsmi.folkracelive.services.CustomUserDetails;
import dsmi.folkracelive.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;
    private final JWTAuthenticate jwtAuthenticate;

    @Autowired
    public UserController(UserService userService, JWTAuthenticate jwtAuthenticate) {
        this.userService = userService;
        this.jwtAuthenticate = jwtAuthenticate;
    }

    @PutMapping("/rest/updatedUserDetails")
    public void updateUser(@AuthenticationPrincipal CustomUserDetails userDetails
                            ,@RequestParam Map<String, String> updatedDetails) throws EmailAlreadyExists {
        userService.updateUser(userDetails, updatedDetails);
    }

    /**
     * Restcontroller that takes in an image for the club
     * @param file imagefile
     * @param user tokendetails to user
     * @throws IOException If problem with file
     */
    @PutMapping("/rest/userImage/")
    public UserNoPwDTO updateClubImage(@RequestParam("image") MultipartFile file, @AuthenticationPrincipal CustomUserDetails user) throws IOException {
        return userService.updateClubImage(file, user);
    }

    /**
     * Restcontroller that delete club image
     * @param user tokendetails to user
     */
    @DeleteMapping("/rest/userImage/")
    public UserNoPwDTO deleteClubImage(@AuthenticationPrincipal CustomUserDetails user) {
       return userService.deleteClubImage(user);
    }

    /**
     * Restcontroller for creating a new user. After user is created it automaticly sets a token
     * @param user Object that contains clubName, password and email
     * @return String Token
     * @throws Exception if clubname or email taken
     */
    @PostMapping("/api/createUser")
    public JWTResponse createUser(@RequestBody User user) throws Exception {
        userService.createNewUser(user);
        return jwtAuthenticate.authenticate(new JWTLogin(user.getClubName(), user.getPassword()));
    }

    /**
     * Restcontroller that authenticate the user through its credentials and sets a new token
     * @param jwtLogin Object with clubname and password
     * @return String token
     * @throws Exception If login fails
     */
    @PostMapping("/api/authenticate")
    public JWTResponse authenticate(@RequestBody JWTLogin jwtLogin) throws Exception {
        return jwtAuthenticate.authenticate(jwtLogin);
    }

}

