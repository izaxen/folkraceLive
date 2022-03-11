package dsmi.folkracelive.jwt;

import dsmi.folkracelive.dto.jwt.JWTLogin;
import dsmi.folkracelive.dto.jwt.JWTResponse;
import dsmi.folkracelive.exceptions.InvalidLoginCredentials;
import dsmi.folkracelive.services.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component

public class JWTAuthenticate {

    private final JWTUtility jwtUtility;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public JWTAuthenticate(JWTUtility jwtUtility, AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
    }

    public JWTResponse authenticate(@RequestBody JWTLogin jwtLogin) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtLogin.getClubName(),
                            jwtLogin.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new InvalidLoginCredentials();
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtLogin.getClubName());
        final String token = jwtUtility.generateToken(userDetails);

        return new JWTResponse(token);
    }
}
