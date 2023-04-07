package ru.drKonarev.requestapp.user;


import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getName(),
                user.getEmail(),
                "PASSWORD", user.getRoles());
    }

    User toUser(UserDto userDto) {
        return new User(null, userDto.getName(),
                userDto.getEmail(),
                userDto.getRoles(),
                userDto.getPassword());
    }
}
