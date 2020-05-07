package com.mpkd.chatapp.user;

import org.springframework.data.repository.Repository;

import java.util.Optional;


interface UserRepository extends Repository<User, Long> {

    Optional<User> findByName(String name);

    boolean existsByName(String name);

    User save(User user);
}
