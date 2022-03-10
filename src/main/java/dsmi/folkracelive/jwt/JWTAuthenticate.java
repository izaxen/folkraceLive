package dsmi.folkracelive.jwt;

import dsmi.folkracelive.dto.jwt.JWTLogin;
import dsmi.folkracelive.dto.jwt.JWTResponse;
import dsmi.folkracelive.exceptions.InvalidLoginCredentials;
import dsmi.folkracelive.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class JWTAuthenticate {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

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
