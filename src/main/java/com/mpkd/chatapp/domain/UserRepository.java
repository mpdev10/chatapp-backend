package com.mpkd.chatapp.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByName(String name);

    boolean existsByName(String name);

}
