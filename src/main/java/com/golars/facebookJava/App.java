package com.golars.facebookJava;


import com.golars.facebookJava.entity.Token;

public class App {
    public static void main(String[] args) {
        try {
            Config config = new Config("resources/config.properties");

            Token fbToken = new Token("EAAEz2eMHkdQBAAszTQUMJhz1kHzz059KEexP0MssGgNs6jZAwpvTWlEBqvLdM4jXr37jlDjDBdaghX7KJ83g6GMZAgqqerrgih3LF7X756Q7bzgyXYkEK6TfzZAwR3Ja09gH4ccAcEZC00vRORl8ZBoDFTl7zla8ZD");
            Connect connect = new Connect(config);
            connect.getUserByToken(fbToken).thenAccept(System.out::println);
        } catch (FbException e) {
            System.err.println("Facebook json : " + e.getJsonObject());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
