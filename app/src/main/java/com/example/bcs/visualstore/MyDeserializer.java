package com.example.bcs.visualstore;

import com.example.bcs.visualstore.PojoDatas.Data;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class MyDeserializer implements JsonDeserializer<Data> {
    @Override
    public Data deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonElement data = json.getAsJsonObject().get("data");
        return new Gson().fromJson(data, Data.class);
    }
}