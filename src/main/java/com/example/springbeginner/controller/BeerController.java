package com.example.springbeginner.controller;

import com.example.springbeginner.models.Beer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/beers")
public class BeerController {

    private final List<Beer> beers;

    public BeerController(){
        beers = new ArrayList<>();

        beers.add(new Beer(1, "Glarus English Ale", 4.6));
        beers.add(new Beer(2, "Rhombus Porter", 5.0));
    }

    @GetMapping
    public List<Beer> getAll(){
        return beers;
    }

    @GetMapping("/{id}")
    public Beer getById(@PathVariable int id){
        return beers.stream()
                .filter(beer-> beer.getId() == id)
                .findFirst()
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Beer with id %d not found.", id)));
    }

    @PostMapping
    public Beer create(@Valid @RequestBody Beer beer){
        beers.add(beer);
        return beer;
    }

    @PutMapping
    public Beer update(@RequestBody Beer beer){
        Beer updateBeer = beers.stream()
                .filter(item -> item.getId() == beer.getId())
                .findFirst().orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Beer with id %id not found.", beer.getId())
                ));
        updateBeer.setAbv(beer.getAbv());
        updateBeer.setName(beer.getName());

        return updateBeer;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        Beer deleteBeer = beers.stream()
                .filter(item -> item.getId() == id)
                .findFirst().orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Beer with id %id no exist.", id)
                ));

        beers.remove(deleteBeer);
    }
}
