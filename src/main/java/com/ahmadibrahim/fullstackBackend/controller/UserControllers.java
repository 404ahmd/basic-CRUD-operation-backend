package com.ahmadibrahim.fullstackBackend.controller;

import com.ahmadibrahim.fullstackBackend.exception.UserNotFoundException;
import com.ahmadibrahim.fullstackBackend.model.User;
import com.ahmadibrahim.fullstackBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserControllers {

    @Autowired
    private UserRepository userRepository;

    //post the data user
    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    //get all data user
    @GetMapping("/users")
    List<User> getAllUsers(){
     return userRepository.findAll();
    }

    //Search user by id "NOT FOUND"
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    //edit user by id
    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    //delete user data
    @DeleteMapping("user/{id}")
    String deleteUser(@PathVariable Long id){
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        userRepository.deleteById(id);
        return "User by id "+id+"has been deleted";
    }
}
