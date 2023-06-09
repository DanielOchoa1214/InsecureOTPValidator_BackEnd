package edu.eci.spti.auth.model;

public class User {

    private String userName;
    private String password;

    public User(){}

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{userName: " + userName + ", password: " + password + "}" ;
    }

    @Override
    public boolean equals(Object obj) {
        return equals((User) obj);
    }

    private boolean equals(User user){
        return this.userName.equals(user.userName) && this.password.equals(user.password);
    }
}
