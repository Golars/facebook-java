package com.golars.facebookJava;

import com.golars.facebookJava.entity.User;
import com.golars.facebookJava.entity.Token;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.asynchttpclient.Response;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;

import java.util.concurrent.CompletableFuture;

public class Connect {

    private Config config;

    public Connect(Config config) {
        this.config = config;
    }

    public CompletableFuture<User> getUserByToken(Token token) throws FbException {
        CompletableFuture<User> promise = new CompletableFuture<>();

        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
        asyncHttpClient
                .prepareGet(this.config.getPrepareUrl("me", "id,name,email,picture.width(720).height(720)", token))
                .execute(new AsyncCompletionHandler<Response>() {

                    @Override
                    public Response onCompleted(Response response) throws Exception {
                        JsonParser p = new JsonParser();
                        JsonObject jsonObject = (JsonObject) p.parse(response.getResponseBody());
                        if (jsonObject.has("error")) {
                            JsonObject error = jsonObject.get("error").getAsJsonObject();
                            promise.completeExceptionally(new FbException(error.get("message").getAsString(), error));
                            return response;
                        }

                        if (!(jsonObject.has("id") && jsonObject.has("name"))) {
                            promise.completeExceptionally(new FbException("Json is invalid", jsonObject));
                            return response;
                        }

                        User user = new User(jsonObject.get("id").getAsString(), jsonObject.get("name").getAsString());
                        if (jsonObject.has("email")) {
                            user.setEmail(jsonObject.get("email").getAsString());
                        }
                        if (jsonObject.has("picture")) {
                            JsonObject avatar = jsonObject.get("picture").getAsJsonObject();
                            user.setCover(avatar.get("data").getAsJsonObject().get("url").getAsString());
                        }
                        promise.complete(user);
                        return response;
                    }

                    @Override
                    public void onThrowable(Throwable t) {
                        t.printStackTrace();
                        promise.completeExceptionally(new FbException(t.getMessage()));
                    }
                });

        return promise;
    }
}
