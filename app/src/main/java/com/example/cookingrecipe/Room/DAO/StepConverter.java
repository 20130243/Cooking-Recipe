package com.example.cookingrecipe.Room.DAO;

import androidx.room.TypeConverter;

import com.example.cookingrecipe.Domain.Model.Step;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class StepConverter {
    @TypeConverter
    public String fromStepsList(List<Step> steps) {
        Gson gson = new Gson();
        return gson.toJson(steps);
    }

    @TypeConverter
    public List<Step> toStepsList(String stepsJson) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Step>>() {}.getType();
        return gson.fromJson(stepsJson, type);
    }
}