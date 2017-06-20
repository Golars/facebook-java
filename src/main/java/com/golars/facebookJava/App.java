package com.golars.facebookJava;


import org.asynchttpclient.*;

public class App {
    public static void main(String[] args) {
        try {
            Config config = new Config("resources/config.properties");

            Connect connect = new Connect(config);
            connect.getFbData(new AsyncCompletionHandler<Response>() {
                @Override
                public Response onCompleted(Response response) throws Exception {
                    System.out.println(response.getResponseBody());
                    return response;
                }

                @Override
                public void onThrowable(Throwable t) {
                }
            }, "me", "id,name");

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
