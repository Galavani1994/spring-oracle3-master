package com.developer.springOracle3.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author am.azizkhani on 2018-05-13.
 */
public final class ObjectConverter {

    private static final Gson            gson     = new Gson();
    public static final  ObjectConverter instance = new ObjectConverter();

    private ObjectConverter() {
    }

    public <O> O getCloneObject(Object input, final Class<O> clazz) {
        final String json = gson.toJson(input);
        return gson.fromJson(json, clazz);
    }

    public <O> List<O> getCloneObjectList(Object input, final Class clazz) {
        final String json = gson.toJson(input);
        final Type typeTok = TypeToken.getParameterized(List.class, clazz).getType();
        return gson.fromJson(json, typeTok);
    }
}