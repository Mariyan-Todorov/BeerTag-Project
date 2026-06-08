package com.example.springbeginner.repositories;

import com.example.springbeginner.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    List<User> getAll();
    User getById(int id);
    User getByUsername(String username);
}
