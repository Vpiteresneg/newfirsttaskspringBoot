package ru.vpiteresneg.kata.newfirsttaskspringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.vpiteresneg.kata.newfirsttaskspringBoot.dao.RoleDao;
import ru.vpiteresneg.kata.newfirsttaskspringBoot.dao.UserDao;
import ru.vpiteresneg.kata.newfirsttaskspringBoot.model.Role;
import ru.vpiteresneg.kata.newfirsttaskspringBoot.model.User;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userDao.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    @Transactional
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public boolean saveUserWithRole(User user, String roleName) {
        try {
            Role role = roleDao.findByName(roleName);
            user.setRoles(Collections.singletonList(role));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.saveUser(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}
