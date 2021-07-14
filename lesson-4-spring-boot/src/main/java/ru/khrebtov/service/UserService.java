package ru.khrebtov.service;

import org.springframework.data.domain.Page;
import ru.khrebtov.controller.UserListParams;
import ru.khrebtov.persist.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Page<User> findWithFilter(UserListParams userListParams);

    Optional<User> findById(Long id);

    void save(User user);

    void deleteById(Long id);
}
