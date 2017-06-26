package com.golars.facebookJava.exception;

import com.google.gson.JsonObject;

public class FbException extends Exception {
    private JsonObject jsonObject;

    public FbException(String message) {
        super(message);
    }

    public FbException(String message, JsonObject jsonObject) {
        super(message);
        this.jsonObject = jsonObject;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }
}
