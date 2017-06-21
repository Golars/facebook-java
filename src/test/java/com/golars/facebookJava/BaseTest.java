package com.golars.facebookJava;

import junit.framework.TestCase;

public class BaseTest extends TestCase {

    protected Config config;

    public void setUp() throws Exception {
        this.config = new Config("resources/config_test.properties");
        super.setUp();
    }

    public void testingConfig() {
        this.config.setFbToken("token");
        assertEquals("token", this.config.getFbToken().getToken());
    }
}