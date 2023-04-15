package edu.eci.spti.auth.persistence;

import edu.eci.spti.auth.model.User;

import java.util.List;
import java.util.Map;

public interface AuthInteface {
    User createUser(String userName, String password) throws AuthException;
    User getUser(String userName) throws AuthException;
    String generateOTP(User user) throws  AuthException;
    String getOTP(String user) throws AuthException;
    void setUser(User newUser) throws AuthException;

    List<Map<String, ?>> getInfo();
}
