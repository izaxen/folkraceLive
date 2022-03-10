package dsmi.folkracelive.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Invalid login credentials")
public class InvalidLoginCredentials extends Exception{
}
