package com.golars.facebookJava;

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.Response;

public class ConnectTest extends BaseTest {

    public void testGetFbData() throws Exception {
        Connect connect = new Connect(this.config);

        connect.getFbData(new AsyncCompletionHandler<Response>() {
            @Override
            public Response onCompleted(Response response) throws Exception {
                System.out.println(response.getResponseBody());
                assertEquals(true, true);
                return response;
            }

            @Override
            public void onThrowable(Throwable t) {
            }
        }, "me", "id,name");
    }

    public void testGetFbDataByToken() throws Exception {
        Connect connect = new Connect(this.config);
        
        String token = "token";
        this.config.setFbToken("token");
        assertEquals(token, this.config.getFbToken().getToken());


        connect.getFbData(new AsyncCompletionHandler<Response>() {
            @Override
            public Response onCompleted(Response response) throws Exception {
                System.out.println(response.getResponseBody());
                assertEquals(true, true);
                return response;
            }

            @Override
            public void onThrowable(Throwable t) {
            }
        }, "me", "id,name");
    }
}