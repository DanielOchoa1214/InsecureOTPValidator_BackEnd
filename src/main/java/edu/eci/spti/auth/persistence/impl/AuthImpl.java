package edu.eci.spti.auth.persistence.impl;

import edu.eci.spti.auth.model.User;
import edu.eci.spti.auth.persistence.AuthException;
import edu.eci.spti.auth.persistence.AuthInteface;
import javafx.util.Pair;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthImpl implements AuthInteface {

    private ConcurrentHashMap<String, User> users =  new ConcurrentHashMap<>();
    private List<Pair<String, String>> activeOTPs = new ArrayList<>();

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

    @Override
    public String generateOTP(User user) throws AuthException {
        if (!users.containsKey(user.getUserName())) throw new AuthException(AuthException.USER_NOT_FOUND);
        String otp = RandomStringUtils.randomNumeric(4);
        activeOTPs.add(new Pair<>(user.getUserName(), otp));
        setTimeout(() -> {
            activeOTPs.remove(new Pair<>(user.getUserName(), otp));
        }, 10000);
        return otp;
    }

    private void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
}
