package com.nfeeds.data.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nfeeds.data.auth.model.User;
import com.nfeeds.data.auth.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.logging.Log;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Operation(summary = "Create a new user entry.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created."),
            @ApiResponse(responseCode = "500", description = "Body was not parsable."),
    })
    @PostMapping("")
    public void newUser(@RequestBody String body) {
        try {
            var newUser = new ObjectMapper().readValue(body, User.class);
            userRepository.save(newUser);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body not parsable to User");
        }
    }

    @Operation(summary = "Get all the users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
    })
    @GetMapping("")
    public List<User> getUsers() {
        var ret = new ArrayList<User>();
        userRepository.findAll().forEach(ret::add);
        return ret;
    }

    @Operation(summary = "Get a user by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found.", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "User not found.", useReturnTypeSchema = true),
    })
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") String id) {
        var user = userRepository.findById(id);

        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }

        return user.get();
    }

    @Operation(summary = "Delete a user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted.", useReturnTypeSchema = true),
    })
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") String id) {
        userRepository.deleteById(id);
    }
}
