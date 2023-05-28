package com.example.cookingrecipe.Room.DAO;

import androidx.room.TypeConverter;

import com.example.cookingrecipe.Domain.Model.Ingredient;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

public class IngredientConverter {
    @TypeConverter
    public String fromIngredientsList(List<Ingredient> ingredients) {
        Gson gson = new Gson();
        return gson.toJson(ingredients);
    }

    @TypeConverter
    public List<Ingredient> toIngredientsList(String ingredientsJson) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Ingredient>>() {}.getType();
        return gson.fromJson(ingredientsJson, type);
    }
}
