package hello.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserIdNotSupportedException extends RuntimeException {

    public UserIdNotSupportedException(long userId) {
        super("The user id: '" + userId + "' is not supported. Try a valid number instead.");
    }
}