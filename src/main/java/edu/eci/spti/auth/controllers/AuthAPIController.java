package edu.eci.spti.auth.controllers;

import edu.eci.spti.auth.model.User;
import edu.eci.spti.auth.persistence.AuthException;
import edu.eci.spti.auth.services.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentMap;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/auth")
public class AuthAPIController {

    @Autowired
    AuthServices authService;

    @GetMapping
    public ResponseEntity<?> login(@RequestParam("user") String user, @RequestParam("password") String password){
        try{
            boolean loggedIn = authService.login(new User(user, password));
            return new ResponseEntity<>(loggedIn, HttpStatus.ACCEPTED);
        } catch (AuthException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/passwords")
    public ResponseEntity<?> login(@RequestParam("user") String user){
        try{
            boolean success = authService.sendOTP(user);
            return new ResponseEntity<>("HOLI", HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
