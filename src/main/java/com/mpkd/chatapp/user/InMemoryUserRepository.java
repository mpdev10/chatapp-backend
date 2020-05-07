package com.mpkd.chatapp.user;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Optional;

class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> userMap = Maps.newConcurrentMap();

    @Override
    public Optional<User> findByName(String name) {
        return Optional.ofNullable(userMap.get(name));
    }

    @Override
    public boolean existsByName(String name) {
        return userMap.containsKey(name);
    }

    @Override
    public User save(User user) {
        return userMap.put(user.getName(), user);
    }

}
