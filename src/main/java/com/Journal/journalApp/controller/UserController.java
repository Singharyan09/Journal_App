/**
 * This File create User with Credential Passward, Username
 *  UserController - > UserService - > UserRepository - > MongoDB
 *  this having Entity UserEntity
 * **/


package com.Journal.journalApp.controller;
import com.Journal.journalApp.Service.UserService;
import com.Journal.journalApp.Model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> findAll(){
        return userService.findAll();
    }

    @PostMapping
    public void create(@RequestBody UserEntity userEntity){
        userService.saveEntry(userEntity);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> update(@RequestBody UserEntity userEntity , @PathVariable String username) {
        UserEntity userOld = userService.findByUsername(username);
        if (userOld != null) {
            userOld.setPassword(userEntity.getPassword());
            userService.saveEntry(userOld);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}