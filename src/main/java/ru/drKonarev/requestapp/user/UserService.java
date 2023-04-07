package ru.drKonarev.requestapp.user;

import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto changeAccess(Long userId);

    List<UserDto> searchUsersByName(String text);


}
