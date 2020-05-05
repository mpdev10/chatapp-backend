package com.mpkd.chatapp.domain;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("all")
class InMemoryUserRepository implements UserRepository {

    private Map<String, User> userMap = Maps.newConcurrentMap();

    @Override
    public Optional<User> findByName(String name) {
        return Optional.ofNullable(userMap.get(name));
    }

    @Override
    public boolean existsByName(String name) {
        return userMap.containsKey(name);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends User> S save(S entity) {
        userMap.put(entity.getName(), entity);
        return entity;
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }
}
