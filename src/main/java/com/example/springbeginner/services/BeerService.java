package com.example.springbeginner.services;

import com.example.springbeginner.models.Beer;

import java.util.List;

public interface BeerService {
    List<Beer> get(String name, Double minAbv, Double maxAbv, Integer styleId, String sortBy, String sortOrder);

    Beer get(int id);

    void create(Beer beer);

    void update(Beer beer);

    void delete(int id);
}
