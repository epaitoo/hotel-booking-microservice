package com.epaitoo.users.service.service;

import com.epaitoo.users.service.entity.User;
import com.epaitoo.users.service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        log.info("Inside SaveUser in UserService");
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        log.info("Inside getUserById of UserService");
        return userRepository.findByUserId(id);
    }

    public List<User> findAll() {
        log.info("Inside findAll of UserService");
        return userRepository.findAll();
    }

    public Optional<User> updateUser(Long id, User newUser) {
        log.info("Inside updateUser of UserService");
        return userRepository.findById(id)
                .map(oldUser -> {
                    User updatedUser = oldUser.updateWith(newUser);
                    return userRepository.save(updatedUser);
                });
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
