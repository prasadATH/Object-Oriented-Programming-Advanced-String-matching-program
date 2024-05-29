package edu.curtin.app;


public class User {

    private String userFunction;
    private String userCriteria;

    public User() {
        userFunction = "";
        userCriteria = "";

    }

    public String getUserFunction() {
        return userFunction;
    }

    public String getUserCriteria() {
        return userCriteria;
    }

    public void setUserFunction(String inUserFunction) {
        userFunction = inUserFunction;
    }

    public void setUserCriteria(String inUserCriteria) {
        userCriteria = inUserCriteria;
    }

}