package com.example.serverless.resource;

import com.example.serverless.error.UserNotFoundException;
import com.example.serverless.model.User;
import com.example.serverless.service.JsonPlaceholderService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final JsonPlaceholderService jsonPlaceholderService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    List<User> users = new ArrayList<>();

    public UserController(JsonPlaceholderService jsonPlaceholderService) {
        this.jsonPlaceholderService = jsonPlaceholderService;
    }

    @GetMapping
    List<User> findAll() {
        return users;
    }

    @GetMapping("/{id}")
    Optional<User> findById(@PathVariable Integer id) {
        return Optional.ofNullable(users.stream()
                .filter(u -> u.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Post with id: " + id + " not found.")));
    }

    @PostMapping
    void create(@RequestBody User user) {
        users.add(user);
    }

    @PutMapping("/{id}")
    void update(@RequestBody User user, @PathVariable Integer id) {
        users.stream()
                .filter(u -> u.id().equals(id))
                .findFirst()
                .ifPresent(value -> users.set(users.indexOf(value), user));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        users.removeIf(post -> post.id().equals(id));
    }

    @PostConstruct
    private void init() {
        if(users.isEmpty()) {
            log.info("Loading Posts using JsonPlaceHolderService");
            users = jsonPlaceholderService.loadPosts();
        }
    }
}
