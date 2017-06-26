package com.golars.facebookJava.decoder;

import com.golars.facebookJava.entity.User;
import com.golars.facebookJava.exception.FbException;
import com.google.gson.JsonObject;
import org.asynchttpclient.Response;

public class UserDecoder {

    private User user;

    public UserDecoder(Response response) throws FbException {

        JsonObject jsonObject = FbDecoder.parseResponse(response);

        User user = new User(jsonObject.get("id").getAsString(), jsonObject.get("name").getAsString());
        if (jsonObject.has("email")) {
            user.setEmail(jsonObject.get("email").getAsString());
        }
        if (jsonObject.has("picture")) {
            JsonObject avatar = jsonObject.get("picture").getAsJsonObject();
            user.setCover(avatar.get("data").getAsJsonObject().get("url").getAsString());
        }

        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
