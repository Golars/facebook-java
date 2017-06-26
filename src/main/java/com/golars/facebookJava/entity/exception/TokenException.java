package com.golars.facebookJava.entity.exception;

public class TokenException extends FbException {

    public TokenException() {
        super("Invalid Access Token");
    }
}
