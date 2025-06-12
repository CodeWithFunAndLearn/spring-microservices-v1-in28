package com.rest.webservices.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaController {

    private UserDaoService userDaoService;

    private UserRepostory userRepostory;

    public UserJpaController(UserDaoService userDaoService, UserRepostory userRepostory) {
        this.userDaoService = userDaoService;
        this.userRepostory = userRepostory;
    }

    // This method will return all users
    @GetMapping("/jpa/users")
    public List<User> getAllUsers() {
        return userRepostory.findAll();
    }

    // This method will return a user by id
    @GetMapping("/jpa/users/{id}")
    public Optional<User> getUserById(@PathVariable int id) throws UserNotFoundException {
        Optional<User> user = userRepostory.findById(id);
        return user;
    }

    // This method will save a user
    @PostMapping("/jpa/users/save")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        User savedUser = userRepostory.save(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(uri).body(savedUser);
    }

    // This method will update a user
    @PutMapping("/jpa/users/update")
    public User updateUser(User user) {
        return userDaoService.saveUser(user);
    }

    // This method will delete a user
    @DeleteMapping("/jpa/users/delete/{id}")
    public void deleteUser(@PathVariable int id) {
            userRepostory.deleteById(id);

    }
}
