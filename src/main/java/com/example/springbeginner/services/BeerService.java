package com.example.springbeginner.services;

import com.example.springbeginner.models.Beer;

import java.util.List;

public interface BeerService {
    List<Beer> getAll();

    Beer getById(int id);

    void create(Beer beer);

    void update(Beer beer);

    void delete(int id);
}
