package hello;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
class UserIdNotSupported extends RuntimeException {

    public UserIdNotSupported(long userId) {
        super("The user id: '" + userId + "' is not supported. Try a valid number instead.");
    }
}