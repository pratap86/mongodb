package com.pratap.mongodb.student.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {

    public static String prettyPrintJson(Object object){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }
}
