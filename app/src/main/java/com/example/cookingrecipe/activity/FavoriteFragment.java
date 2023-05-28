package com.example.cookingrecipe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cookingrecipe.Adapter.RecipeFavoriteAdapter;
import com.example.cookingrecipe.Domain.Model.Recipe;
import com.example.cookingrecipe.Domain.Network.FirebaseRecipe;
import com.example.cookingrecipe.OnItemClickListener;
import com.example.cookingrecipe.Room.AppDatabase;
import com.example.cookingrecipe.Room.DAO.RecipeDao;
import com.example.cookingrecipe.Room.Entity.RecipeEntity;
import com.example.cookingrecipe.databinding.FragmentFavoriteBinding;

import java.util.List;

public class FavoriteFragment extends Fragment {
    public FavoriteFragment() {
        // Required empty public constructor
    }

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewRecipeFavorite;
    FragmentFavoriteBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setRecyclerViewRecipeFavorite();


        return view;

    }

    private void setRecyclerViewRecipeFavorite() {
        AppDatabase db = AppDatabase.getInstance(this.getActivity());
        RecipeDao recipeDao = db.recipeDao();
        List<Recipe> recipeList = new RecipeEntity().toRecipeList(recipeDao.getAll());
        //danh sach cac cong thuc
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewRecipeFavorite = binding.recylerFavorite;
        recyclerViewRecipeFavorite.setLayoutManager(linearLayoutManager);

        RecipeFavoriteAdapter recipeFavoriteAdapter = new RecipeFavoriteAdapter(recipeList);
        recipeFavoriteAdapter.setOnItemClickListener(recipeId -> {
            Intent intent = new Intent(getActivity(), DetailRecipeActivity.class);
            intent.putExtra("recipeId", recipeId);
            startActivity(intent);
        });
        adapter = recipeFavoriteAdapter;
        recyclerViewRecipeFavorite.setAdapter(adapter);


    }
}