package com.example.springbeginner.controller;

import com.example.springbeginner.configuration.BeanConfiguration;
import com.example.springbeginner.exceptions.DuplicateEntityException;
import com.example.springbeginner.exceptions.EntityNotFoundException;
import com.example.springbeginner.models.Beer;
import com.example.springbeginner.services.BeerService;
import jakarta.validation.Valid;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/beers")
public class BeerController {

    private BeerService service;

    public BeerController(){
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        this.service = context.getBean(BeerService.class);
    }
    @GetMapping
    public List<Beer> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Beer getById(@PathVariable int id){
        try {
            return service.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Beer create(@Valid @RequestBody Beer beer){
        try {
            service.create(beer);
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }
        return beer;
    }

    @PutMapping("/{id}")
    public Beer update(@PathVariable int id, @RequestBody Beer beer){
       try{
           service.update(beer);
       }catch (EntityNotFoundException e){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
       }catch (DuplicateEntityException e){
           throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
       }

        return beer;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        try{
            service.delete(id);
        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
