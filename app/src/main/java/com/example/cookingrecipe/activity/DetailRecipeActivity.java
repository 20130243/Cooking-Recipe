package com.example.cookingrecipe.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.cookingrecipe.Adapter.IngredientAdapter;
import com.example.cookingrecipe.Adapter.StepAdapter;
import com.example.cookingrecipe.Domain.Model.Ingredient;
import com.example.cookingrecipe.Domain.Model.Recipe;
import com.example.cookingrecipe.Domain.Model.Step;
import com.example.cookingrecipe.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DetailRecipeActivity extends AppCompatActivity {
    private MaterialToolbar toolbar;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewIngredients;
    private RecyclerView recyclerViewSteps;
    private TextView titleTextView;
    private TextView servingTextView;
    private TextView timeTextView;
    private String recipeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("day là key");
        myRef.setValue("Hello, World!");
        setContentView(R.layout.activity_detail_recipe);
        toolbar = findViewById(R.id.materialToolbar);
        setSupportActionBar(toolbar);
        // click vào item của menu
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.more) {
                    // Do something
                    return true;
                }
                return false;
            }
        });
        recipeId = getIntent().getStringExtra("recipeId" );
        Recipe recipe = new Recipe();
        recipe.setTitle("Cơm tấm");
        recipe.setDescription("Cơm tấm được xem như là món ăn đặc sản của người Sài Gòn. Từng đĩa cơm tấm nóng hổi, thơm phức luôn là lựa chọn hàng đầu cho những bữa ăn nhanh nhưng đủ dinh dưỡng.\n" +
                "\n" +
                "Mùa dịch này không thể đi hàng quán được. Vậy cùng Bách hóa XANH học ngay cách nấu cơm tấm tại nhà thơm ngon như ngoài tiệm nhé!");
        recipe.setImage("comtam");
        recipe.setServings("2 - 3");
        recipe.setTime(45);
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(new Ingredient("sườn cốt lết heo vừa",4,"miếng"));
        ingredientList.add(new Ingredient("gạo tấm",300,"g"));
        ingredientList.add(new Ingredient("gạo trắng để làm thính",60,"g"));
        ingredientList.add(new Ingredient("100g bì heo", 100,"g"));
        ingredientList.add(new Ingredient("3 quả trứng gà",3,""));
        ingredientList.add(new Ingredient("50g thịt nạc heo xay",50,"g"));
        ingredientList.add(new Ingredient("50g miến",50,"g"));
        ingredientList.add(new Ingredient("Nấm mèo khô",0,""));
        recipe.setIngredients(ingredientList);
        List<Step> stepList = new ArrayList<Step>();
        stepList.add(new Step(1, "Gạo tấm mua về vo sạch qua 2 - 3 lần và nấu trong nồi cơm điện như bình thường.","https://www.cet.edu.vn/wp-content/uploads/2022/09/cach-lam-oc-huong-xao-bo-toi.jpg"));
        stepList.add(new Step(1, "Gạo tấm mua về vo sạch qua 2 - 3 lần và nấu trong nồi cơm điện như bình thường.",""));
        stepList.add(new Step(1, "Gạo tấm mua về vo sạch qua 2 - 3 lần và nấu trong nồi cơm điện như bình thường.",""));
        recipe.setSteps(stepList);

        recyclerViewIngredient(recipe);
        recyclerViewStep(recipe);

        titleTextView = findViewById(R.id.recipe_title);
        titleTextView.setText(recipe.getTitle());
        servingTextView = findViewById(R.id.recipe_serving);
        servingTextView.setText(recipe.getServings());
        timeTextView = findViewById(R.id.recipe_time);
        timeTextView.setText(String.valueOf(recipe.getTime()));


    }

    private void recyclerViewStep(Recipe recipe) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewSteps = findViewById(R.id.step);
        recyclerViewSteps.setLayoutManager(linearLayoutManager);
        adapter = new StepAdapter(recipe);
        recyclerViewSteps.setAdapter(adapter);

    }

    private void recyclerViewIngredient(Recipe recipe) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewIngredients = findViewById(R.id.ingredient);
        recyclerViewIngredients.setLayoutManager(linearLayoutManager);

        adapter = new IngredientAdapter(recipe);
        recyclerViewIngredients.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed(); // Sử dụng hệ thống back stack để trở về trang trước đó
    }
}