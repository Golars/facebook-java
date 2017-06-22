package com.golars.facebookJava;

import com.golars.facebookJava.entity.User;

import java.util.concurrent.CompletableFuture;

public class ConnectTest extends BaseTest {

    public void testGetUserByToken() throws Exception {
        Connect connect = new Connect(this.config);
        CompletableFuture<User> result = connect.getUserByToken(this.config.getFbToken()).thenApply((User user) -> {
            assertEquals("1913452152229577", user.getId());
            assertEquals("Golars Golars", user.getName());
            assertEquals("golars@bigmir.net", user.getEmail());
            return user;
        });

        assertNotNull(result.get());
    }
}