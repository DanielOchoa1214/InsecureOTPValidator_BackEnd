package edu.eci.spti.auth.services;

import edu.eci.spti.auth.model.User;
import edu.eci.spti.auth.persistence.AuthException;
import edu.eci.spti.auth.persistence.AuthInteface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    @Autowired
    AuthInteface authParsistence;

    public boolean login(User loggingIn) throws AuthException {
        User storedUser = authParsistence.getUser(loggingIn.getUserName());
        return storedUser.equals(loggingIn);
    }

    public String sendOTP(String user) throws AuthException {
        User storedUser = authParsistence.getUser(user);
        String otp = authParsistence.generateOTP(storedUser);
        MailManager.sendMail(user, otp);
        return otp;
    }

    public boolean verifyOTP(String user, String otp) throws AuthException {
        String userOTP = authParsistence.getOTP(user);
        return userOTP.equals(otp);
    }
}
