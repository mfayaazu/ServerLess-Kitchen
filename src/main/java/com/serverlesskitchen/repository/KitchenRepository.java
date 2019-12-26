package com.serverlesskitchen.repository;

import com.serverlesskitchen.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KitchenRepository extends MongoRepository<Recipe, Integer> {}