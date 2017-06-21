package com.golars.facebookJava;

public class ConfigTest extends BaseTest {

    public void testGetPrepareUrl() throws Exception {
        String token = "token";
        this.config.setTokenFb(token);
        assertEquals(token, this.config.getTokenFb().getToken());
        assertEquals("https://graph.facebook.com/v2.9/me?access_token=" + token + "&fields=id,name", this.config.getPrepareUrl("me", "id,name"));
    }
}