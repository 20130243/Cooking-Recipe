package com.example.cookingrecipe.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cookingrecipe.Adapter.IngredientAdapter;
import com.example.cookingrecipe.Adapter.StepAdapter;
import com.example.cookingrecipe.Domain.Model.Recipe;
import com.example.cookingrecipe.Domain.Network.FirebaseRecipe;
import com.example.cookingrecipe.R;
import com.google.android.material.appbar.MaterialToolbar;

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
        setContentView(R.layout.activity_detail_recipe);

        // toolbar
        toolbar = findViewById(R.id.materialToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        toolbar.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            return itemId == R.id.favorite;
        });

        recipeId = getIntent().getStringExtra("recipeId");
        new FirebaseRecipe().getRecipeById(recipeId, recipe -> {


            ImageView recipe_image = findViewById(R.id.recipe_image);
            String imageURL = recipe.getImage();
            if (!imageURL.isBlank()) {
                Glide.with(recipe_image.getContext())
                        .load(imageURL)
                        .into(recipe_image);
            }
            titleTextView = findViewById(R.id.recipe_title);
            titleTextView.setText(recipe.getTitle());
            servingTextView = findViewById(R.id.recipe_serving);
            servingTextView.setText(recipe.getServings());
            timeTextView = findViewById(R.id.recipe_time);
            timeTextView.setText(String.valueOf(recipe.getTime()));
            recyclerViewIngredient(recipe);
            recyclerViewStep(recipe);
        });


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_material_toolbar, menu);
        return true;
    }
}