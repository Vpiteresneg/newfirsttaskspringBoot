package ru.vpiteresneg.kata.newfirsttaskspringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vpiteresneg.kata.newfirsttaskspringBoot.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}