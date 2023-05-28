package com.example.cookingrecipe.Room.DAO;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class TagConverter {
    @TypeConverter
    public String fromTagsList(List<String> tags) {
        Gson gson = new Gson();
        return gson.toJson(tags);
    }

    @TypeConverter
    public List<String> toTagsList(String tagsJson) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(tagsJson, type);
    }
}
