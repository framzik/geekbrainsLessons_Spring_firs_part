package ru.khrebtov.service;

import org.springframework.data.domain.Page;
import ru.khrebtov.controller.UserDto;
import ru.khrebtov.controller.UserListParams;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll();

    Page<UserDto> findWithFilter(UserListParams userListParams);

    Optional<UserDto> findById(Long id);

    void save(UserDto user);

    void deleteById(Long id);
}
