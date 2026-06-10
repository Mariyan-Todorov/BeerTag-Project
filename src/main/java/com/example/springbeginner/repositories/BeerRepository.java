package com.example.springbeginner.repositories;

import com.example.springbeginner.models.Beer;

import java.util.List;

public interface BeerRepository {
    List<Beer> get(String name, Double minAbv, Double maxAbv, Integer styleId, String sortBy, String sortOrder);

    Beer get(int id);

    Beer get(String name);

    void create(Beer beer);

    void update(Beer beer);

    void delete(int id);

    List<Beer> getAll();
}
