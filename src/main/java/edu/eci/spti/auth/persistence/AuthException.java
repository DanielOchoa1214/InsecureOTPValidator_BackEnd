package edu.eci.spti.auth.persistence;

public class AuthException extends Exception{

    public static String USER_NOT_FOUND = "We couldn't find the specified user";
    public static String USER_EXISTS = "The user already existes";

    public AuthException(String message){
        super(message);
    }
}
