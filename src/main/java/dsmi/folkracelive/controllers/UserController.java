package dsmi.folkracelive.controllers;


import dsmi.folkracelive.entities.jwt.JWTRequest;
import dsmi.folkracelive.entities.jwt.JWTResponse;
import dsmi.folkracelive.services.CustomUserDetailsService;
import dsmi.folkracelive.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
public class UserController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @PostMapping("/authenticate")
    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getClubname(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Felaktiga inloggningsupgifter", e);
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getClubname());
        final String token = jwtUtility.generateToken(userDetails);

        return new JWTResponse(token);
    }

}

