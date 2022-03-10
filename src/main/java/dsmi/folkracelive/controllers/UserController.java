package dsmi.folkracelive.controllers;


import dsmi.folkracelive.entities.User;
import dsmi.folkracelive.DTO.JWTRequest;
import dsmi.folkracelive.DTO.JWTResponse;
import dsmi.folkracelive.services.CustomUserDetailsService;
import dsmi.folkracelive.jwt.JWTUtility;
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
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @PostMapping("/updateUser/{id}")
    public void updateUser(@RequestBody User user, @PathVariable String id){

    }
    @PostMapping("/updateUserImage/{id}")
    public void updateClubImage(@RequestParam("image")MultipartFile file, @PathVariable String id) throws IOException {
        byte[] image = Base64.encodeBase64(file.getBytes());
        String result = new String(image);
        System.out.println(result);
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user) throws Exception {
       String res = userService.createNewUser(user);
        System.out.println(res);
    }

    @PostMapping("/api/authenticate")
    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getClubName(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Felaktiga inloggningsupgifter", e);
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getClubName());
        final String token = jwtUtility.generateToken(userDetails);

        return new JWTResponse(token);
    }

}

