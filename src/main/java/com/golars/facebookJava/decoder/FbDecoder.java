package com.golars.facebookJava.decoder;

import com.golars.facebookJava.entity.exception.FbException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.asynchttpclient.Response;

public class FbDecoder {

    public static JsonObject parseResponse(Response response) throws FbException{
        JsonParser p = new JsonParser();
        JsonObject jsonObject = (JsonObject) p.parse(response.getResponseBody());
        if (jsonObject.has("error")) {
            JsonObject error = jsonObject.get("error").getAsJsonObject();
            throw new FbException(error.get("message").getAsString(), error);
        }

        if (!(jsonObject.has("id") && jsonObject.has("name"))) {
            throw new FbException("Json is invalid", jsonObject);
        }

        return jsonObject;
    }
}
