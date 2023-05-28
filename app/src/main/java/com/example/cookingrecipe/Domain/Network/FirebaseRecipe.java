package com.example.cookingrecipe.Domain.Network;

import android.util.Log;

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

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirebaseRecipe {
    DatabaseReference databaseReference;
    public interface RecipeListCallback {
        void onRecipeListReady(List<Recipe> recipeList);
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


//        return recipeList;
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
            ingredient.setAmount(ingredientSnapshot.child("amount").getValue(Integer.class));
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

    public void insertRecipe(Recipe recipe) {
        databaseReference = FirebaseDatabase.getInstance().getReference("recipes");
        Recipe recipe1 = new Recipe(null, "Bánh mì thịt nướng", "Bánh mì thơm ngon với thịt nướng, rau thơm và nước sốt chấm", "https://cdn.tgdd.vn/Files/2021/08/20/1376583/cach-lam-banh-mi-thit-nuong-cuc-don-gian-bang-chai-nhua-co-san-tai-nha-202108201640593483.jpg", "1", 30, Arrays.asList("Bánh mì", "Điểm tâm"), null, null);

        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient = new Ingredient("Thịt nướng", 200, "g");
        ingredients.add(ingredient);
        ingredient = new Ingredient("Rau thơm", 50, "g");
        ingredients.add(ingredient);
        ingredient = new Ingredient("Nước mắm", 2, "thìa canh");
        ingredients.add(ingredient);
        ingredient = new Ingredient("Đường", 1, "thìa canh");
        ingredients.add(ingredient);
        ingredient = new Ingredient("Tỏi băm", 1, "thìa cafe");
        ingredients.add(ingredient);
        ingredient = new Ingredient("Ớt băm nhuyễn", 1, "thìa cafe");
        ingredients.add(ingredient);
        recipe1.setIngredients(ingredients);

        List<Step> steps = new ArrayList<>();
        Step step = new Step(1, "Chuẩn bị nước sốt: trộn đều nước mắm, đường, tỏi băm và ớt băm nhuyễn.", "https://cdn.tgdd.vn/Files/2021/08/20/1376583/cach-lam-banh-mi-thit-nuong-cuc-don-gian-bang-chai-nhua-co-san-tai-nha-202108201647284558.jpg");
        steps.add(step);
        step = new Step(2, "Thái thịt nướng thành từng miếng mỏng.", null);
        steps.add(step);
        step = new Step(3, "Rã đôi bánh mì, nướng trên bếp hoặc lò vi sóng để bánh mì giòn.", null);
        steps.add(step);
        step = new Step(4, "Sắp xếp rau thơm, thịt nướng, cà chua và dưa chuột lên bánh mì.", null);
        steps.add(step);
        step = new Step(5, "Phục vụ bánh mì với nước sốt chấm.", "https://cdn.tgdd.vn/Files/2021/08/20/1376583/cach-lam-banh-mi-thit-nuong-cuc-don-gian-bang-chai-nhua-co-san-tai-nha-202108201657055360.jpg");
        steps.add(step);
        recipe1.setSteps(steps);
        databaseReference.push().setValue(recipe1);

    }
}
