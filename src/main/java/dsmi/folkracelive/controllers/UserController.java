package dsmi.folkracelive.controllers;


import dsmi.folkracelive.dto.jwt.JWTResponse;
import dsmi.folkracelive.dto.jwt.JWTLogin;
import dsmi.folkracelive.dto.models.UserNoPwDTO;
import dsmi.folkracelive.entities.User;
import dsmi.folkracelive.exceptions.InvalidLoginCredentials;
import dsmi.folkracelive.jwt.JWTAuthenticate;
import dsmi.folkracelive.jwt.JWTUtility;
import dsmi.folkracelive.services.CustomUserDetailsService;
import dsmi.folkracelive.services.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
//@RequestMapping("/rest")
public class UserController {



    @Autowired
    private UserService userService;

    @Autowired
    private JWTAuthenticate jwtAuthenticate;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @PutMapping("/updateUser/{id}")
    public void updateUser(@RequestBody User user, @PathVariable String id) {

    }

    @PutMapping("/updateUserImage/{id}")
    public void updateClubImage(@RequestParam("image") MultipartFile file, @PathVariable String id) throws IOException {
        byte[] image = Base64.encodeBase64(file.getBytes());
        String result = new String(image);
        System.out.println(result);
    }

    @PostMapping("/createUser")
    public UserNoPwDTO createUser(@RequestBody User user) throws Exception {
        return userService.createNewUser(user);
    }

    @PostMapping("/api/authenticate")
    public JWTResponse authenticate(@RequestBody JWTLogin jwtLogin) throws Exception {
        return jwtAuthenticate.authenticate(jwtLogin);
    }

}

