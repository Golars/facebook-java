package com.golars.facebookJava.exception;

public class TokenException extends FbException {

    public TokenException() {
        super("Invalid Access Token");
    }
}
