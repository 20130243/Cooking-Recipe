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


//        return recipeList;
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
            System.out.println(stepOrder + " day la buoc");
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
        Ingredient bun = new Ingredient("Bún tươi", 200, "g");
        Ingredient thitHeo = new Ingredient("Thịt heo", 200, "g");
        Ingredient tom = new Ingredient("Tôm tươi", 100, "g");
        Ingredient hanhTim = new Ingredient("Hành tím", 1, "củ");
        Ingredient dauAn = new Ingredient("Dầu ăn", 1, "thìa canh");
        Ingredient muoi = new Ingredient("Muối", 1, "muỗng cà phê");
        Ingredient duong = new Ingredient("Đường", 1, "muỗng cà phê");
        Ingredient tieu = new Ingredient("Tiêu", 1, "muỗng cà phê");
        Ingredient nuocMam = new Ingredient("Nước mắm", 1, "muỗng canh");

// Tạo đối tượng Step
        Step step1 = new Step(1, "Thái thịt heo và tôm thành từng miếng vừa ăn. Băm nhỏ hành tím.", null);
        Step step2 = new Step(2, "Cho dầu ăn vào chảo, đợi dầu nóng rồi cho thịt heo, tôm và hành tím vào chiên đến khi thịt và tôm chín vàng, hành thơm.", null);
        Step step3 = new Step(3, "Nấu bún tươi trong nước sôi khoảng 3-4 phút cho chín, sau đó rửa sạch bằng nước lạnh và để ráo.", null);
        Step step4 = new Step(4, "Chuẩn bị nước chấm: trộn đều nước mắm, đường, tiêu và nước chanh.", null);
        Step step5 = new Step(5, "Đắp bún và rau sống lên đĩa, sau đó thêm chả giò và thịt heo, tôm đã chiên lên trên. Dùng nước chấm kèm.", null);

// Tạo danh sách nguyên liệu và bước nấu
        List<Ingredient> ingredients = Arrays.asList(bun, thitHeo, tom, hanhTim, dauAn, muoi, duong, tieu, nuocMam);

        List<Step> steps = Arrays.asList(step1, step2, step3, step4, step5);

// Tạo đối tượng Recipe
        Recipe bunChaGio = new Recipe("null", "Bún chả giò", "Bún tươi và thơm phức được kết hợp với chả giò giòn rụm, rau sống và nước chấm đậm đà.", "https://cdn.tgdd.vn/Files/2021/08/20/1376583/cach-lam-banh-mi-thit-nuong-cuc-don-gian-bang-chai-nhua-co-san-tai-nha-202108201640593483.jpg", "4", 30, Arrays.asList("Món chính"), ingredients, steps);
        databaseReference.push().setValue(bunChaGio);

    }
}
