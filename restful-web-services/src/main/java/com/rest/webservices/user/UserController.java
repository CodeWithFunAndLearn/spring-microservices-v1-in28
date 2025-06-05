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

@RestController
public class UserController {

    private UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    // This method will return all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userDaoService.getUsers();
    }

    // This method will return a user by id
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) throws UserNotFoundException {
        User user = userDaoService.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        return user;
    }

    // This method will save a user
    @PostMapping("/users/save")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        User savedUser = userDaoService.saveUser(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(uri).body(savedUser);
    }

    // This method will update a user
    @PutMapping("/users/update")
    public User updateUser(User user) {
        return userDaoService.saveUser(user);
    }

    // This method will delete a user
    @DeleteMapping("/users/delete/{id}")
    public void deleteUser(@PathVariable int id) {
            userDaoService.deleteUserById(id);

    }
}
