package com.example.cookingrecipe.activity;


import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cookingrecipe.Adapter.IngredientAdapter;
import com.example.cookingrecipe.Adapter.StepAdapter;
import com.example.cookingrecipe.Domain.Model.Recipe;
import com.example.cookingrecipe.Domain.Network.FirebaseRecipe;
import com.example.cookingrecipe.R;
import com.example.cookingrecipe.Room.AppDatabase;
import com.example.cookingrecipe.Room.DAO.RecipeDao;
import com.example.cookingrecipe.Room.Entity.RecipeEntity;
import com.google.android.material.appbar.MaterialToolbar;

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
    private boolean isFavorite;
    private RecipeEntity recipeEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);
        recipeId = getIntent().getStringExtra("recipeId");

        AppDatabase db = AppDatabase.getInstance(this);
        RecipeDao recipeDao = db.recipeDao();
        isFavorite = recipeDao.getById(recipeId) != null;

        toolbar = findViewById(R.id.materialToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        toolbar.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.favorite) {
                toggleFavorite();
                return true;
            }
            return false;
        });


        if (isNetworkConnected()) {
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
                recipeEntity = recipe.toRecipeEntity();
            });
        } else {
            Recipe recipe = recipeDao.getById(recipeId).toRecipe();
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
            recipeEntity = recipe.toRecipeEntity();
        }

    }

    public boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    private void toggleFavorite() {
        if (isFavorite) {
            // Nếu công thức đã được yêu thích, xóa công thức khỏi cơ sở dữ liệu và thay đổi biểu tượng yêu thích thành không có màu
            AppDatabase db = AppDatabase.getInstance(this);
            RecipeDao recipeDao = db.recipeDao();
            recipeDao.delete(recipeDao.getById(recipeId));
            toolbar.getMenu().findItem(R.id.favorite).setIcon(R.drawable.ic_favorite); // thay đổi biểu tượng thành không có màu
            isFavorite = false;
        } else {
            // Nếu công thức chưa được yêu thích, thêm công thức vào cơ sở dữ liệu và thay đổi biểu tượng yêu thích thành có màu
            AppDatabase db = AppDatabase.getInstance(this);
            RecipeDao recipeDao = db.recipeDao();
            recipeDao.insert(recipeEntity);
            toolbar.getMenu().findItem(R.id.favorite).setIcon(R.drawable.ic_favorite_fill); // thay đổi biểu tượng thành có màu
            isFavorite = true;
        }
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
        if (isFavorite) {
            toolbar.getMenu().findItem(R.id.favorite).setIcon(R.drawable.ic_favorite_fill);
        }
        return true;
    }
}