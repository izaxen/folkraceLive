package dsmi.folkracelive.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT, reason = "A clubname already exists.")
public class ClubnameAlreadyExist extends Exception{
}
