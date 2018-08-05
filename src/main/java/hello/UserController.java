package hello;

import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import hello.exceptions.UserIdNotSupportedException;
import hello.exceptions.UserNotFoundException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final String templateOldUser = "Welcome back, %s!";
    private static final String templateNewUser = "Hello, %s!";
    private ArrayList<User> users = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/{userId}")
    public User getUser (@PathVariable long userId) throws UserNotFoundException {

            User user = this.findUserById(userId);
            return user;
        //return new User(counter.incrementAndGet(),
        //        String.format(template, name));
    }

    @GetMapping(value = "/hateoas/{userId}", produces = MediaTypes.HAL_JSON_VALUE)
    public User getUserHateoas(@PathVariable long userId ) throws UserNotFoundException{
        User user = this.findUserById(userId);

        Link link = ControllerLinkBuilder.linkTo(UserController.class)
                .slash(user.getUserId()).withSelfRel();

        user.add(link);

        return user;
    }

    @PostMapping("/newUser")
    public ResponseEntity<?> newUser(@RequestParam("userId") long userId) throws UserIdNotSupportedException {

        if(this.isIdAlreadyUsed(userId))
            throw new UserIdNotSupportedException(userId);

        User user = new User(userId);
        users.add(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    private boolean isIdAlreadyUsed(long userId) {

        for (User user : users) {
            if (user.getUserId() == userId)
                return true;
        }

        return false;
    }

    private User findUserById(long userId) throws UserNotFoundException{

        for(User user : users){
            if(user.getUserId() == userId)
                return user;
        }

        throw new UserNotFoundException(userId);
    }
}