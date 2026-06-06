package com.example.springbeginner.configuration;

import com.example.springbeginner.repositories.BeerMapRepositoryImpl;
import com.example.springbeginner.repositories.BeerRepository;
import com.example.springbeginner.services.BeerService;
import com.example.springbeginner.services.BeerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public BeerService beerService(){
        return new BeerServiceImpl(beerRepository());
    }

    @Bean
    public BeerRepository beerRepository(){
        return new BeerMapRepositoryImpl();
    }
}
