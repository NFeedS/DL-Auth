package com.nfeeds.data.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nfeeds.data.auth.services.ClientServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class ClientController {

    final private ClientServices clientServices;

    public ClientController(ClientServices clientServices) {
        this.clientServices = clientServices;
    }

    @PostMapping("api/v1/client/new/sender")
    public void createNewSender(@RequestBody String content){
        try {
            Map<String, String> data = new ObjectMapper().readValue(content, HashMap.class);

            String clientId = data.get("clientId");

            if(clientId == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "clientId not specified.");
            }

            String psw = data.get("password");

            if(psw == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password not specified.");
            }

            clientServices.createNewSender(clientId,psw);

        } catch (JsonMappingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body is not valid.");
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body is not valid.");
        }
    }

    @PostMapping("api/v1/client/new/receiver")
    public void createNewReceiver(@RequestBody String content){
        try {
            HashMap data = new ObjectMapper().readValue(content, HashMap.class);

            String clientId = (String) data.get("clientId");

            if(clientId == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "clientId not specified.");
            }

            String psw = (String) data.get("password");

            if(psw == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password not specified.");
            }

            clientServices.createNewReceiver(clientId,psw);

        } catch (JsonMappingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body is not valid.");
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body is not valid.");
        }
    }

    @DeleteMapping("api/v1/client/delete/{clientId}")
    public void deleteClient(@PathVariable String clientId,
                             @RequestParam("password") String password) {
        if(clientServices.checkAuth(clientId,password)){
            clientServices.deleteClient(clientId);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Authorization denied.");
        }
    }

    @GetMapping("api/internal/v1/client/auth")
    public Boolean authClient(@RequestBody String content){
        try {
            HashMap data = new ObjectMapper().readValue(content, HashMap.class);

            String clientId = (String) data.get("clientId");

            if(clientId == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "clientId not specified.");
            }

            String psw = (String) data.get("password");

            if(psw == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password not specified.");
            }

            return clientServices.checkAuth(clientId,psw);

        } catch (JsonMappingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body is not valid.");
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body is not valid.");
        }
    }
}
