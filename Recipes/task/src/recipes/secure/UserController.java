package recipes.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/register")
    public void register(@Valid @RequestBody User user) {
        User toSave = new User();
        toSave.setEmail(user.getEmail());
        toSave.setPassword(user.getPassword());
        userService.save(toSave);
    }

}
