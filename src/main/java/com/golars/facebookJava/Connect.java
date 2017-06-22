package com.golars.facebookJava;

import com.golars.facebookJava.entity.Token;
import com.golars.facebookJava.entity.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

import java.util.concurrent.CompletableFuture;

public class Connect {

    private Config config;

    public Connect(Config config) {
        this.config = config;
    }

    public CompletableFuture<User> getUserByToken(Token token) {
        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
        CompletableFuture<Response> result = asyncHttpClient
                .prepareGet(this.config.getPrepareUrl("me", "id,name,email,picture.width(720).height(720)", token))
                .execute()
                .toCompletableFuture();

        return result.thenApply((Response response) -> {
            JsonParser p = new JsonParser();
            JsonObject jsonObject = (JsonObject) p.parse(response.getResponseBody());
            User user = new User(jsonObject.get("id").getAsString(), jsonObject.get("name").getAsString());
            user.setEmail(jsonObject.get("email").getAsString());
            JsonObject avatar = jsonObject.get("picture").getAsJsonObject();
            if(avatar.isJsonObject()){
                user.setCover(avatar.get("data").getAsJsonObject().get("url").getAsString());
            }
            return user;
        }).toCompletableFuture();
    }
}
