package com.golars.facebookJava;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private String url;

    private Token tokenFb;

    public Config(String configFilePath) {

        Properties config = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(configFilePath);
            config.load(input);
            this.tokenFb = new Token(config.getProperty("token"));
            this.url = config.getProperty("baseUrl") + config.getProperty("version") + '/';
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Token getTokenFb() {
        return tokenFb;
    }

    public void setTokenFb(String tokenFb) {
        this.tokenFb = new Token(tokenFb);
    }

    public String getPrepareUrl(String path, String fields) {
        return this.url + path + "?access_token=" + this.tokenFb.getToken() + "&fields=" + fields;
    }
}
