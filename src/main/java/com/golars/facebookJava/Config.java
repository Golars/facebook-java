package com.golars.facebookJava;

import com.golars.facebookJava.entity.Token;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private String url;

    private Token fbToken;

    public Config(String configFilePath) {

        Properties config = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(configFilePath);
            config.load(input);
            this.fbToken = new Token(config.getProperty("token", ""));
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

    public Token getFbToken() {
        return fbToken;
    }

    public void setFbToken(String fbToken) {
        this.fbToken = new Token(fbToken);
    }

    public String getPrepareUrl(String path, String fields) {
        return this.getPrepareUrl(path, fields, this.getFbToken());
    }

    public String getPrepareUrl(String path, String fields, Token token) {
        return this.url + path + "?access_token=" + token.getToken() + "&fields=" + fields;
    }
}
