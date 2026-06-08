package com.example.springbeginner.repositories;

import com.example.springbeginner.exceptions.EntityNotFoundException;
import com.example.springbeginner.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private final List<User> users;

    public UserRepositoryImpl(){
        users = new ArrayList<>();

        users.add(new User(1,"pesho", true));
        users.add(new User(2,"vladi", false));
        users.add(new User(1,"nadya", false));
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users);
    }

    @Override
    public User getById(int id) {
        return getAll().stream()
                .filter(user-> user.getId() == id)
                .findFirst()
                .orElseThrow(()-> new EntityNotFoundException("User", id));
    }

    @Override
    public User getByUsername(String username) {
        return getAll()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(()-> new EntityNotFoundException("User", "username", username));
    }
}
