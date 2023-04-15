package edu.eci.spti.auth.controllers;

import edu.eci.spti.auth.model.User;
import edu.eci.spti.auth.persistence.AuthException;
import edu.eci.spti.auth.services.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/passwords")
    public ResponseEntity<?> generateOTP(@RequestBody User user){
        try{
            String otp = authService.sendOTP(user.getUserName());
            return new ResponseEntity<>(otp, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/passwords")
    public ResponseEntity<?> verifyOTP(@RequestParam("user") String user, @RequestParam("otp") String otp){
        try{
            boolean success = authService.verifyOTP(user, otp);
            return new ResponseEntity<>(success, HttpStatus.ACCEPTED);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
