package com.nfeeds.data.auth.services;

import com.nfeeds.data.auth.model.Client;
import com.nfeeds.data.auth.model.ClientType;
import com.nfeeds.data.auth.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

@Service
public class ClientServices {

    private final ClientRepository clientRepository;

    public ClientServices(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void createNewSender(String clientId, String psw) {
        if(clientRepository.checkClientIdExistence(clientId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Public id already in use.");
        }

        String salt = generateSalt();
        String hpsw = "";

        try {
            hpsw = hashPassword(psw,salt);
        } catch (NoSuchAlgorithmException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Hash algorithm not found.");
        } catch (InvalidKeySpecException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid key.");
        }

        clientRepository.saveAndFlush(new Client(clientId, ClientType.Sender,salt,hpsw));
    }

    public void createNewReceiver(String clientId, String psw) {

        if(clientRepository.checkClientIdExistence(clientId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Public id already in use.");
        }

        String salt = generateSalt();
        String hpsw = "";

        try {
            hpsw = hashPassword(psw,salt);
        } catch (NoSuchAlgorithmException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Hash algorithm not found.");
        } catch (InvalidKeySpecException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid key.");
        }

        clientRepository.saveAndFlush(new Client(clientId, ClientType.Receiver,salt,hpsw));
    }

    public void deleteClient(String clientId) {
        clientRepository.deleteById(clientId);
    }

    public Boolean checkAuth(String clientId, String psw){
        Client c = clientRepository.getReferenceById(clientId);
        try {
            return c.getHashpsw().equals(hashPassword(psw,c.getSalt()));
        } catch (NoSuchAlgorithmException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Hash algorithm not found.");
        } catch (InvalidKeySpecException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid key.");
        }
    }

    private String generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return Arrays.toString(salt);
    }

    private String hashPassword(String psw, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(psw.toCharArray(), salt.getBytes(), 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return Arrays.toString(hash);
    }
}
