package dsmi.folkracelive.controllers;


import dsmi.folkracelive.entities.User;
import dsmi.folkracelive.jwt.JWTRequest;
import dsmi.folkracelive.jwt.JWTResponse;
import dsmi.folkracelive.services.CustomUserDetailsService;
import dsmi.folkracelive.jwt.utility.JWTUtility;
import dsmi.folkracelive.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user){
        userService.createNewUser(user);

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

