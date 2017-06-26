package com.golars.facebookJava;

import com.golars.facebookJava.entity.User;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class ConnectTest extends BaseTest {

    @Test
    public void testGetUserByToken() throws Exception {
        Connect connect = new Connect(this.config, this.client);
        CompletableFuture<User> result = connect.getUserByToken(this.config.getFbToken()).thenApply((User user) -> {
            assertEquals("1913452152229577", user.getId());
            assertEquals("Golars Golars", user.getName());
            assertEquals("golars@bigmir.net", user.getEmail());
            return user;
        });

        assertNotNull(result.get());
    }
}