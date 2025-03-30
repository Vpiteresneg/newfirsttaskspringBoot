package ru.vpiteresneg.kata.newfirsttaskspringBoot.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.vpiteresneg.kata.newfirsttaskspringBoot.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findById(Long id);
    List<User> getAll();
    void saveUser(User user);
    void deleteUser(Long id);
    boolean saveUserWithRole(User user, String roleName);
}
