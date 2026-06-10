package com.example.springbeginner.services;

import com.example.springbeginner.models.Beer;
import com.example.springbeginner.models.User;

import java.util.List;

public interface BeerService {

    List<Beer> getAll();

    List<Beer> get(String name, Double minAbv, Double maxAbv, Integer styleId, String sortBy, String sortOrder);

    Beer get(int id);

    void create(Beer beer, User user);

    void update(Beer beer, User user);

    void delete(int id, User user);
}
