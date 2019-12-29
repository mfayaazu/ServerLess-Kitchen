package com.serverlesskitchen.repository;

import com.serverlesskitchen.model.CustomSequences;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SequenceRepository extends MongoRepository<CustomSequences,String> {
}
