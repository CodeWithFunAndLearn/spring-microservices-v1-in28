package com.rest.webservices.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
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

    private PostRepostory postRepostory;

    private UserRepostory userRepostory;

    public UserJpaController(PostRepostory postRepostory, UserRepostory userRepostory) {
        this.postRepostory = postRepostory;
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
        // Check if the user exists
        Optional<User> existingUser = userRepostory.findById(user.getId());
        if (existingUser.isEmpty()) {
            throw new UserNotFoundException("User with id " + user.getId() + " not found");
        }
        // Update the user
        return userRepostory.save(user);
    }

    // This method will delete a user
    @DeleteMapping("/jpa/users/delete/{id}")
    public void deleteUser(@PathVariable int id) {
            userRepostory.deleteById(id);

    }

    @GetMapping ("/jpa/users/{id}/posts")
    public List<Post> getPostsForUser(@PathVariable int id) throws UserNotFoundException {
        Optional<User> user = userRepostory.findById(id);
        if (user.isEmpty()) 
            throw new UserNotFoundException("User with id " + id + " not found");
        return user.get().getPosts();
    }
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Post> savePostForUser(@PathVariable int id, @Valid @RequestBody Post post) throws UserNotFoundException {
        Optional<User> user = userRepostory.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("User with id " + id + " not found");
        post.setUser(user.get());
        Post savedPost = postRepostory.save(post);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(uri).body(savedPost);
    }
}
