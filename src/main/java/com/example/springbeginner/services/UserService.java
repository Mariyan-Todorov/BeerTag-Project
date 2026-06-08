package com.example.springbeginner.services;

import com.example.springbeginner.models.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User getById(int id);
    User getByUsername(String username);
}
