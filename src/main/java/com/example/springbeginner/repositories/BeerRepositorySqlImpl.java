package com.example.springbeginner.repositories;

import com.example.springbeginner.models.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@PropertySource("classpath:application.properties")
@Repository
public class BeerRepositorySqlImpl implements BeerRepository {
    private final String dbUrl;
    private final String dbUsername;
    private final String dbPassword;

    @Autowired
    public BeerRepositorySqlImpl(Environment environment){
        dbUrl = environment.getProperty("database.url");
        dbUsername = environment.getProperty("database.username");
        dbPassword = environment.getProperty("database.password");
    }

    @Override
    public List<Beer> getAll(){
        try (
                Connection connection =  DriverManager.getConnection(dbUrl,dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select beer_id, name, abv from beers");
                ){
            return getBeers(resultSet);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<Beer> get(String name, Double minAbv, Double maxAbv, Integer styleId, String sortBy, String sortOrder) {
        return List.of();
    }

    @Override
    public Beer get(int id) {
        return null;
    }

    @Override
    public Beer get(String name) {
        return null;
    }

    @Override
    public void create(Beer beer) {

    }

    @Override
    public void update(Beer beer) {

    }

    @Override
    public void delete(int id) {

    }

    private List<Beer> getBeers(ResultSet beersData) throws SQLException{
        List<Beer> beers = new ArrayList<>();
        while (beersData.next()){
            Beer beer = new Beer(
                    beersData.getInt("beer_id"),
                    beersData.getString("name"),
                    beersData.getDouble("abv")
            );
            beers.add(beer);
        }

        return beers;
    }
}
