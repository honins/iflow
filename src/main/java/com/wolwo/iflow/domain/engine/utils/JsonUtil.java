package com.wolwo.iflow.domain.engine.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Created by hy
 * @date on 2021/1/25 11:30
 */
public class JsonUtil {

    private static Gson gson = new GsonBuilder().create();

    public static String toJson(Object object) {
        return object == null ? "" : gson.toJson(object);
    }

}
