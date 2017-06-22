package com.golars.facebookJava;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigTest extends BaseTest {

    @Test
    public void testGetPrepareUrl() throws Exception {
        String token = "token";
        this.config.setFbToken(token);
        assertEquals(token, this.config.getFbToken().getToken());
        assertEquals("https://graph.facebook.com/v2.9/me?access_token=" + token + "&fields=id,name", this.config.getPrepareUrl("me", "id,name"));
    }
}