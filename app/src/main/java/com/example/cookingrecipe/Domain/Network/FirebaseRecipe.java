package com.example.cookingrecipe.Domain.Network;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cookingrecipe.Domain.Model.Ingredient;
import com.example.cookingrecipe.Domain.Model.Recipe;
import com.example.cookingrecipe.Domain.Model.Step;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseRecipe {
    DatabaseReference databaseReference;

    public interface RecipeListCallback {
        void onRecipeListReady(List<Recipe> recipeList);


    }

    public interface RecipeCallback {
        void onRecipeReady(Recipe recipe);


    }

    public void getAllRecipe(RecipeListCallback callback) {
        databaseReference = FirebaseDatabase.getInstance().getReference("recipes");
        List<Recipe> recipeList = new ArrayList<>();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                recipeList.add(convert(dataSnapshot));
                callback.onRecipeListReady(recipeList);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                Recipe recipe = convert(dataSnapshot);
                int index = getIndex(recipeList, recipe.getId());
                if (index != -1) {
                    recipeList.set(index, recipe);
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Recipe recipe = dataSnapshot.getValue(Recipe.class);
                int index = getIndex(recipeList, recipe.getId());
                if (index != -1) {
                    recipeList.remove(index);
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                // Do nothing
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý lỗi ở đây
            }
        });
    }

    public void getRecipeById(String recipeId, RecipeCallback callback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("recipes").child(recipeId);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            Recipe recipe;

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    recipe = convert(dataSnapshot);
                    callback.onRecipeReady(recipe);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public Recipe convert(DataSnapshot dataSnapshot) {
        Recipe recipe = new Recipe();
        recipe.setId(dataSnapshot.getKey());
        recipe.setTitle(dataSnapshot.child("title").getValue(String.class));
        recipe.setDescription(dataSnapshot.child("description").getValue(String.class));
        recipe.setImage(dataSnapshot.child("image").getValue(String.class));
        recipe.setServings(dataSnapshot.child("servings").getValue(String.class));
        Integer timeValue = dataSnapshot.child("time").getValue(Integer.class);
        if (timeValue != null) {
            recipe.setTime(timeValue.intValue());
        } else {
            recipe.setTime(0);
        }

        List<Ingredient> ingredientList = new ArrayList<>();
        DataSnapshot ingredientsSnapshot = dataSnapshot.child("ingredients");
        for (DataSnapshot ingredientSnapshot : ingredientsSnapshot.getChildren()) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(ingredientSnapshot.child("name").getValue(String.class));
            ingredient.setAmount(ingredientSnapshot.child("amount").getValue(String.class));
            ingredient.setUnit(ingredientSnapshot.child("unit").getValue(String.class));
            ingredientList.add(ingredient);
        }
        recipe.setIngredients(ingredientList);

        List<String> tags = new ArrayList<>();
        DataSnapshot tagsSnapshot = dataSnapshot.child("tags");
        for (DataSnapshot tagSnapshot : tagsSnapshot.getChildren()) {
            tags.add(tagSnapshot.getValue(String.class));
        }
        recipe.setTags(tags);

        List<Step> steps = new ArrayList<>();
        DataSnapshot stepsSnapshot = dataSnapshot.child("steps");
        for (DataSnapshot stepSnapshot : stepsSnapshot.getChildren()) {
            int stepOrder = stepSnapshot.child("step_order").getValue(Integer.class);
            String description = stepSnapshot.child("description").getValue(String.class);
            String image = stepsSnapshot.child("image").getValue(String.class);
            steps.add(new Step(stepOrder, description, image));

        }
        recipe.setSteps(steps);
        return recipe;
    }

    private int getIndex(List<Recipe> recipeList, String recipeId) {
        for (int i = 0; i < recipeList.size(); i++) {
            if (recipeList.get(i).getId().equals(recipeId)) {
                return i;
            }
        }
        return -1;
    }

//    public void insertRecipe(Recipe recipe) {
//        databaseReference = FirebaseDatabase.getInstance().getReference("recipes");
//        System.out.println(recipe);
//        if( recipe!=null)        databaseReference.push().setValue(recipe);
//
//    }
}
