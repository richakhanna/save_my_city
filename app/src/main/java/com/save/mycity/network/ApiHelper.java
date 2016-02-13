package com.save.mycity.network;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by harshikesh.kumar on 01/02/16.
 */
public class ApiHelper {
    /**
     * Format for decoding JSON dates in string format.
     */
    private static final SimpleDateFormat JSON_STRING_DATE = new SimpleDateFormat("yyy-MM-dd");

    public static GsonBuilder getGsonBuilder() {
        GsonBuilder builder = new GsonBuilder();

        // class types
        builder.registerTypeAdapter(Integer.class, new JsonDeserializer<Integer>() {
            @Override
            public Integer deserialize(JsonElement json, Type typeOfT,
                                       JsonDeserializationContext context) throws
                JsonParseException {
                try {
                    return Integer.valueOf(json.getAsInt());
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        });
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context) throws JsonParseException {

                try {
                    return JSON_STRING_DATE.parse(json.getAsString());
                } catch (ParseException e) {
                    return null;
                }
            }
        });

        return builder;
    }
}
