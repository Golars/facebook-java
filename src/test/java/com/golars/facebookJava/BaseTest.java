package com.golars.facebookJava;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.junit.Before;

public class BaseTest {

    protected Config config;

    protected AsyncHttpClient client;

    @Before
    public void setUp() {

        this.client = new DefaultAsyncHttpClient();
        this.config = Config.loadFromFile("resources/config_test.properties");
    }
}