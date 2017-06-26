package com.golars.facebookJava;

import com.golars.facebookJava.exception.FbException;
import com.golars.facebookJava.exception.PermissionException;
import com.golars.facebookJava.exception.TokenException;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.Response;

import java.util.concurrent.CompletableFuture;

public class CompletionHandler<T> extends AsyncCompletionHandler<T> {

    private CompletableFuture<T> promise;

    public CompletionHandler(CompletableFuture<T> promise) {
        this.promise = promise;
    }

    @Override
    public void onThrowable(Throwable t) {
        t.printStackTrace();
        promise.completeExceptionally(new FbException(t.getMessage()));
    }

    @Override
    public T onCompleted(Response response) throws Exception {
        return null;
    }


    public FbException prepareException(FbException error) {
        int errorCode = error.getJsonObject().get("code").getAsInt();

        if (errorCode == 190) {
            return new TokenException();
        }

        if (errorCode == 200) {
            return new PermissionException();
        }

        return error;
    }
}
