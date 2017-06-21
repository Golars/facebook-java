package com.golars.facebookJava;

import org.asynchttpclient.*;

public class Connect {

    private Config config;

    public Connect(Config config) {
        this.config = config;
    }

    public void getFbData(AsyncCompletionHandler<Response> response, String path, String fields) {
        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
        asyncHttpClient.prepareGet(this.config.getPrepareUrl(path, fields)).execute(response);
    }


    public void getFbDataByToken(AsyncCompletionHandler<Response> response, String path, String fields, Token token) {
        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
        asyncHttpClient.prepareGet(this.config.getPrepareUrl(path, fields, token)).execute(response);
    }
}
