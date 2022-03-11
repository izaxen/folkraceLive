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

    @PutMapping("/rest/userImage/")
    public void updateClubImage(@RequestParam("image") MultipartFile file, @AuthenticationPrincipal CustomUserDetails user) throws IOException {
        userService.updateClubImage(file, user);
    }

    @DeleteMapping("/rest/userImage/")
    public void deleteClubImage(@AuthenticationPrincipal CustomUserDetails user) {
        userService.deleteClubImage(user);
    }

    @PostMapping("/rest/createUser")
    public UserNoPwDTO createUser(@RequestBody User user) throws Exception {
        return userService.createNewUser(user);
    }

    @PostMapping("/api/authenticate")
    public JWTResponse authenticate(@RequestBody JWTLogin jwtLogin) throws Exception {
        return jwtAuthenticate.authenticate(jwtLogin);
    }

}

