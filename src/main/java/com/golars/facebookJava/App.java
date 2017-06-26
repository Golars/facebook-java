package com.golars.facebookJava;


import com.golars.facebookJava.entity.Token;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;

public class App {
    public static void main(String[] args) {
        try {
            Config config = new Config("resources/config.properties");

            AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
            Token fbToken = new Token("EAAEz2eMHkdQBAAszTQUMJhz1kHzz059KEexP0MssGgNs6jZAwpvTWlEBqvLdM4jXr37jlDjDBdaghX7KJ83g6GMZAgqqerrgih3LF7X756Q7bzgyXYkEK6TfzZAwR3Ja09gH4ccAcEZC00vRORl8ZBoDFTl7zla8ZD");

            Connect connect = new Connect(config, asyncHttpClient);
            connect.getUserByToken(fbToken).whenComplete((user, ex) -> {
                if (ex != null) {
                    ex.printStackTrace();
                    return;
                }
                System.out.println(user);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
