package com.nfeeds.data.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nfeeds.data.auth.model.User;
import com.nfeeds.data.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/users")
public class AuthController {

    private final UserRepository userRepository;
    
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void newUser(@RequestBody String body) {
        log.debug(this.getClass().getSimpleName() + " - newUser");
        try {
            var newUser = new ObjectMapper().readValue(body, User.class);
            userRepository.save(newUser);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body not parsable to User");
        }
    }
    
    @GetMapping("/")
    public List<User> getUsers() {
        log.debug(this.getClass().getSimpleName() + " - getUsers");
        return userRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") String id) {
        log.debug(this.getClass().getSimpleName() + " - getUser");
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
    }
    
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") String id) {
        log.debug(this.getClass().getSimpleName() + " - deleteUser");
        userRepository.deleteById(id);
    }
}
