package com.example.springbeginner.services;

import com.example.springbeginner.exceptions.DuplicateEntityException;
import com.example.springbeginner.exceptions.EntityNotFoundException;
import com.example.springbeginner.exceptions.UnauthorizedOperationException;
import com.example.springbeginner.models.Beer;
import com.example.springbeginner.models.User;
import com.example.springbeginner.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerServiceImpl implements BeerService {

    private BeerRepository repository;

    @Autowired
    public BeerServiceImpl(BeerRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Beer> get(String name, Double minAbv, Double maxAbv, Integer styleId, String sortBy, String sortOrder) {
        return repository.get(name, minAbv, maxAbv, styleId, sortBy, sortOrder);
    }

    @Override
    public Beer get(int id) {
        return repository.get(id);
    }

    @Override
    public void create(Beer beer, User user) {
        boolean duplicateExists = true;
        try {
            repository.get(beer.getName());
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new DuplicateEntityException("Beer", "name", beer.getName());
        }

        repository.create(beer);
    }

    @Override
    public void update(Beer beer, User user) {
        if(!user.isAdmin()){
            throw new UnauthorizedOperationException("Only admins can modify beer.");
        }
        boolean duplicateExists = true;
        try {
            Beer existingBeer = repository.get(beer.getName());
            if (existingBeer.getId() == beer.getId()) {
                duplicateExists = false;
            }
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new DuplicateEntityException("Beer", "name", beer.getName());
        }

        repository.update(beer);
    }

    @Override
    public void delete(int id, User user) {
        if(!user.isAdmin()){
            throw new UnauthorizedOperationException("Only admins can delete beer");
        }
        repository.delete(id);
    }
}
