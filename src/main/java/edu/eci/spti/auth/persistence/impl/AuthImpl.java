package edu.eci.spti.auth.persistence.impl;

import edu.eci.spti.auth.model.User;
import edu.eci.spti.auth.persistence.AuthException;
import edu.eci.spti.auth.persistence.AuthInteface;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthImpl implements AuthInteface {

    private ConcurrentHashMap<String, User> users =  new ConcurrentHashMap<>();

    public AuthImpl(){
        users.put("danochoa1412@gmail.com", new User("danochoa1412@gmail.com", "password"));
    }

    @Override
    public User createUser(String userName, String password) throws AuthException {
        if (users.containsKey(userName)) throw new AuthException(AuthException.USER_EXISTS);
        User newUser = new User(userName, password);
        users.putIfAbsent(userName, newUser);
        return newUser;
    }

    @Override
    public User getUser(String userName) throws AuthException {
        if (!users.containsKey(userName)) throw new AuthException(AuthException.USER_NOT_FOUND);
        return users.get(userName);
    }
}
