package ru.drKonarev.requestapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @Secured("ADMIN")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/search")
    @Secured("ADMIN")
    public List<UserDto> getAllUsersByName(@RequestParam("name") @NotBlank @NotNull String text) {
        return userService.searchUsersByName(text);
    }

    @PatchMapping("/{userId}")
    @Secured("ADMIN")
    public UserDto changeAccess(@PathVariable("userId") long userId) {
        return userService.changeAccess(userId);
    }
}

