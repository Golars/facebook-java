package com.golars.facebookJava;

import org.junit.Before;

public class BaseTest {

    protected Config config;

    @Before
    public void setUp() {
        this.config = new Config("resources/config_test.properties");
    }
}