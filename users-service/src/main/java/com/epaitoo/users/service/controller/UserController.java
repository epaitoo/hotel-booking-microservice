package com.epaitoo.users.service.controller;

import com.epaitoo.users.service.entity.User;
import com.epaitoo.users.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAllUsers() {
        log.info("Inside findAllUsers of UserController");
        return userService.findAll();
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        log.info("Inside Save User of UserController");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        log.info("Inside getUserById of UserController");
        return userService.findUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User newUser) {
        log.info("Inside updateUser of UserController");
        Optional<User> updateUser = userService.updateUser(id, newUser);
        return updateUser.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> {
                   User created = userService.saveUser(newUser);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(created.getUserId())
                            .toUri();
                    return ResponseEntity.created(location).body(created);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
