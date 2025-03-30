package ru.vpiteresneg.kata.newfirsttaskspringBoot.dao;



import ru.vpiteresneg.kata.newfirsttaskspringBoot.model.User;

import java.util.List;

public interface UserDao {
    User findById(Long id);
    List<User> getAll();
    void saveUser(User user);
    void deleteUser(Long id);
    User findByName(String name);
}