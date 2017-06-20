package com.golars.facebookJava;

import junit.framework.TestCase;

public class ConfigTest extends TestCase {
    public void testGetPrepareUrl() throws Exception {
        String token = "token";
        Config config = new Config("resources/config_test.properties");
        config.setTokenFb(token);
        assertEquals(token, config.getTokenFb().getToken());
        assertEquals("https://graph.facebook.com/v2.9/me?access_token=" + token + "&fields=id,name", config.getPrepareUrl("me", "id,name"));
    }
}