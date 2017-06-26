package com.golars.facebookJava;

import com.golars.facebookJava.decoder.UserDecoder;
import com.golars.facebookJava.entity.Token;
import com.golars.facebookJava.entity.User;
import com.golars.facebookJava.exception.FbException;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;

import java.util.concurrent.CompletableFuture;

public class Connect {

    private Config config;

    private AsyncHttpClient httpClient;

    public Connect(Config config, AsyncHttpClient httpClient) {
        this.config = config;
        this.httpClient = httpClient;
    }

    public CompletableFuture<User> getUserByToken(Token token) throws FbException {
        CompletableFuture<User> promise = new CompletableFuture<>();
        this.httpClient
                .prepareGet(this.config.getPrepareUrl("me", "id,name,email,picture.width(720).height(720)", token))
                .execute(new CompletionHandler(promise) {
                    @Override
                    public User onCompleted(Response response) throws Exception {
                        try{
                            UserDecoder userDecoder = new UserDecoder(response);
                            promise.complete(userDecoder.getUser());
                        } catch (FbException e) {
                            this.prepareException(e).printStackTrace();
                        }

                        return null;
                    }
                });

        return promise;
    }
}
