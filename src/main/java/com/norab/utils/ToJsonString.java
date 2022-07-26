package com.norab.utils;

import com.cedarsoftware.util.io.JsonWriter;

import java.util.Map;

public class ToJsonString {
    public String jsonString() {
        Map<String, Object> conf = Map.of(JsonWriter.SKIP_NULL_FIELDS, true, JsonWriter.TYPE, false);
        return JsonWriter.objectToJson(this, conf);
    }
}
