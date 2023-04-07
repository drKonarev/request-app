package ru.drKonarev.requestapp.user.service;

import ru.drKonarev.requestapp.user.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto changeAccess(Long userId);

    List<UserDto> searchUsersByName(String text);


}
