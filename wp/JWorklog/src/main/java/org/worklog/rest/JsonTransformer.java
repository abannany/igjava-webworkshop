package org.worklog.rest;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import spark.ResponseTransformer;

/**
 * This class is used to transform a given Java object to an JSON String
 * 
 * @author abannany
 *
 */
public class JsonTransformer implements ResponseTransformer {

    private Gson gson;

    /**
     * Instantiates a new {@link JsonTransformer}
     */
    public JsonTransformer() {
        this.gson = createGsonWithDateParser();
    }

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

    /**
     * Creates the gson with date parser.
     *
     * @return the gson
     */
    public static Gson createGsonWithDateParser() {
        GsonBuilder builder = new GsonBuilder();
        JsonDeserializer<Date> deser = new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return json == null ? null : new Date(json.getAsLong());
            }
        };
        JsonSerializer<Date> ser = new JsonSerializer<Date>() {
            @Override
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                return src == null ? null : new JsonPrimitive(src.getTime());
            }
        };
        builder.registerTypeAdapter(Date.class, deser);
        builder.registerTypeAdapter(Date.class, ser);
        return builder.create();
    }
}
