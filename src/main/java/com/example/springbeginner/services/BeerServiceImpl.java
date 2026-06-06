package com.example.springbeginner.services;

import com.example.springbeginner.exceptions.DuplicateEntityException;
import com.example.springbeginner.exceptions.EntityNotFoundException;
import com.example.springbeginner.models.Beer;
import com.example.springbeginner.repositories.BeerRepository;

import java.util.List;

public class BeerServiceImpl implements BeerService {

    private BeerRepository repository;

    public BeerServiceImpl(BeerRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Beer> getAll(){
        return repository.getAll();
    }

    @Override
    public Beer getById(int id){
        return repository.getById(id);
    }

    @Override
    public void create(Beer beer){
        boolean duplicateExists = true;

        try{
            repository.getByName(beer.getName());
        }catch(EntityNotFoundException e){
            duplicateExists = false;
        }

        if( duplicateExists){
            throw new DuplicateEntityException("Beer", "name", beer.getName());
        }

        repository.create(beer);
    }

    @Override
    public void update(Beer beer){
        boolean duplicateExists = true;

        try{
            Beer existingBeer = repository.getByName(beer.getName());
            if(existingBeer.getId() == beer.getId()){
                duplicateExists = false;
            }
        }catch (EntityNotFoundException e){
            duplicateExists = false;
        }

        if(duplicateExists){
            throw new DuplicateEntityException("Beer","name", beer.getName());
        }

        repository.update(beer);
    }

    @Override
    public void delete(int id){
        repository.delete(id);
    }
}
