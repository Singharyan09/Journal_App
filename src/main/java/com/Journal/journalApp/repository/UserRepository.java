package com.Journal.journalApp.repository;

import com.Journal.journalApp.Model.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {
    UserEntity findByUsername(String username);
}

