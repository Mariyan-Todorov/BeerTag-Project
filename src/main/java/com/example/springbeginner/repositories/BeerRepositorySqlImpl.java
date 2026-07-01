package com.example.springbeginner.repositories;

import com.example.springbeginner.exceptions.EntityNotFoundException;
import com.example.springbeginner.models.Beer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BeerRepositorySqlImpl implements BeerRepository {
    private  final SessionFactory sessionFactory;

    @Autowired
    public BeerRepositorySqlImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Beer> getAll(){
        try (Session session = sessionFactory.openSession()){
            Query<Beer> query = session.createQuery("from Beer",Beer.class);
            return query.list();
        }
    }

    @Override
    public List<Beer> get(String name, Double minAbv, Double maxAbv, Integer styleId, String sortBy, String sortOrder) {
        try(Session session = sessionFactory.openSession()){
            Query<Beer> query = session.createQuery("from Beer where name = :name",Beer.class);
            query.setParameter("name", name);
            List<Beer> result = query.list();
            if(result.size() == 0){
                throw new EntityNotFoundException("Beer", "name", name);
            }
            return result;
        }
    }

    @Override
    public Beer get(int id) {
        try(Session session = sessionFactory.openSession()){
            Beer beer = session.find(Beer.class, id);
            if(beer == null){
                throw new EntityNotFoundException("Beer", id);
            }
            return beer;
        }
    }

    @Override
    public Beer get(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(Beer beer) {
//        try(Session session = sessionFactory.openSession()){
//            session.save(beer);
//        }
    }

    @Override
    public void update(Beer beer) {
//        try(Session session = sessionFactory.openSession()){
//            session.beginTransaction();
//            session.update(beer);
//            session.getTransaction().commit();
//        }
    }

    @Override
    public void delete(int id) {

    }

}
