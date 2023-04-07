package ru.drKonarev.requestapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final Pageable page = PageRequest.of(1, 5);

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll(page)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto changeAccess(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) throw new RuntimeException("Not found user with id: " + userId);
        if (user.get().getRoles().contains(Roles.OPERATOR) ||
                user.get().getRoles().contains(Roles.ADMIN))
            throw new RuntimeException("User already have this access level");
        user.get().getRoles().clear();
        user.get().getRoles().add(Roles.OPERATOR);
        return userMapper.toDto(userRepository.save(user.get()));
    }


    @Override
    public List<UserDto> searchUsersByName(String text) {
        return userRepository.findByName(text, page)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}
