package com.example.cookingrecipe.Room.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.cookingrecipe.Domain.Model.Ingredient;
import com.example.cookingrecipe.Domain.Model.Recipe;
import com.example.cookingrecipe.Domain.Model.Step;
import com.example.cookingrecipe.Room.DAO.IngredientConverter;
import com.example.cookingrecipe.Room.DAO.StepConverter;
import com.example.cookingrecipe.Room.DAO.TagConverter;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "recipes")
@TypeConverters({TagConverter.class, IngredientConverter.class, StepConverter.class})
public class RecipeEntity {
    @PrimaryKey
    @NonNull
    private String id;
    private String title;
    private String description;
    private String image;
    private String servings;
    private int time;
    @TypeConverters(TagConverter.class)
    private List<String> tags;
    @TypeConverters(IngredientConverter.class)
    private List<Ingredient> ingredients;
    @TypeConverters(StepConverter.class)
    private List<Step> steps;

    public RecipeEntity(@NonNull String id, String title, String description, String image, String servings, int time, List<String> tags, List<Ingredient> ingredients, List<Step> steps) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.servings = servings;
        this.time = time;
        this.tags = tags;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public RecipeEntity() {
    }

    @Override
    public String toString() {
        return "RecipeEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", servings='" + servings + '\'' +
                ", time=" + time +
                ", tags=" + tags +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                '}';
    }

    public Recipe toRecipe() {
        return new Recipe(this.id, this.title, this.description, this.getImage(), this.servings, this.time, this.tags, this.ingredients, this.steps);
    }

    public List<Recipe> toRecipeList(List<RecipeEntity> list) {
        List<Recipe> recipe = new ArrayList<>();
        for (RecipeEntity recipeEntity : list) {
            recipe.add(recipeEntity.toRecipe());
        }
        return recipe;
    }

}
