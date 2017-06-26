package com.golars.facebookJava.entity;

public class FbResponse {

    private User user;

    public FbResponse(User user) {
        this.user = user;
    }

    public static FbResponse parseResponse(){
        User user = new User("", "");
        FbResponse fbResponse = new FbResponse(user);
        return fbResponse;
    }
}
