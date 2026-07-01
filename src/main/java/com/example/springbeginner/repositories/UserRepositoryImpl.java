package com.example.springbeginner.repositories;

import com.example.springbeginner.exceptions.EntityNotFoundException;
import com.example.springbeginner.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private final SessionFactory sessionFactory;

    public UserRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAll() {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public User getById(int id) {
        try(Session session = sessionFactory.openSession()){
            User user = session.find(User.class, id);
            if(user == null){
                throw new EntityNotFoundException("User", id);
            }
            return user;
        }
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
