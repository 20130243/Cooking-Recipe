package com.example.cookingrecipe.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cookingrecipe.Adapter.RecipeTodayAdapter;
import com.example.cookingrecipe.Domain.Network.FirebaseRecipe;
import com.example.cookingrecipe.OnItemClickListener;
import com.example.cookingrecipe.R;
import com.example.cookingrecipe.databinding.FragmentFavoriteBinding;
import com.example.cookingrecipe.databinding.FragmentHomeBinding;

public class FavouriteFragment extends Fragment {
    public FavouriteFragment() {
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
        //danh sach cac cong thuc
        new FirebaseRecipe().getAllRecipe(recipeList -> {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            recyclerViewRecipeFavorite = binding.recylerFavorite;
            recyclerViewRecipeFavorite.setLayoutManager(linearLayoutManager);

//            RecipeFavoriteAdapter recipeFavoriteAdapter = new RecipeFavoriteAdapter(recipeList);
//            recipeTodayAdapter.setOnItemClickListener(new OnItemClickListener() {
//                @Override
//                public void onItemClick(String recipeId) {
//                    Intent intent = new Intent(getActivity(), DetailRecipeActivity.class);
//                    intent.putExtra("recipeId", recipeId);
//                    startActivity(intent);
//                }
//            });
//            adapter = recipeTodayAdapter;
//            recyclerViewRecipeTodayList.setAdapter(adapter);
        });

    }
}