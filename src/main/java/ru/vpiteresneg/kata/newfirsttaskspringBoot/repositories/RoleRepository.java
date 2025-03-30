package ru.vpiteresneg.kata.newfirsttaskspringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface RoleRepository extends JpaRepository<ru.vpiteresneg.kata.newfirsttaskspringBoot.model.Role, Long> {
    Optional<ru.vpiteresneg.kata.newfirsttaskspringBoot.model.Role> findByName(String name);
}