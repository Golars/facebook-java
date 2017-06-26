package com.golars.facebookJava;

import com.golars.facebookJava.entity.Token;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private String url;

    private Token fbToken;

    public Config() {
    }

    public Config(Token token, String url) {
        this.fbToken = token;
        this.url = url;
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

    public static Config loadFromFile(String configFilePath) {
        Config config = new Config();
        Properties fileConfig = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(configFilePath);
            fileConfig.load(input);
            Token fbToken = new Token(fileConfig.getProperty("token", ""));
            String url = fileConfig.getProperty("baseUrl") + fileConfig.getProperty("version") + '/';
            config = new Config(fbToken, url);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return config;
    }
}
