package edu.eci.spti.auth.persistence;

import edu.eci.spti.auth.model.User;

public interface AuthInteface {
    User createUser(String userName, String password) throws AuthException;
    User getUser(String userName) throws AuthException;
    String generateOTP(User user) throws  AuthException;
}
