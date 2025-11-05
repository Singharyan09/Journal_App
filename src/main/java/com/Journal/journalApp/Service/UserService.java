package com.Journal.journalApp.Service;

import com.Journal.journalApp.Model.UserEntity;
import com.Journal.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public  class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveEntry(UserEntity userEntity){
        userRepository.save(userEntity);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void delete(ObjectId id) {
        userRepository.deleteById(id);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
