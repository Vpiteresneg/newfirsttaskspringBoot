package ru.vpiteresneg.kata.newfirsttaskspringBoot.dao;


import ru.vpiteresneg.kata.newfirsttaskspringBoot.model.Role;

public interface RoleDao {
    Role findByName(String name);
    void saveRole(Role role);
}
