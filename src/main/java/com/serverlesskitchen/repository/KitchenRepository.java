package com.serverlesskitchen.repository;

import com.serverlesskitchen.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenRepository extends MongoRepository<Recipe,Integer> {

  Recipe findByKitchenId(int id);
}