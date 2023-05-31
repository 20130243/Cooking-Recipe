package com.example.cookingrecipe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingrecipe.Adapter.CategoryAdapter;
import com.example.cookingrecipe.Adapter.RecipeFavoriteHomeListAdapter;
import com.example.cookingrecipe.Adapter.RecipeListAdapter;
import com.example.cookingrecipe.Adapter.RecipeTodayAdapter;
import com.example.cookingrecipe.Domain.Model.Recipe;
import com.example.cookingrecipe.Domain.Model.Type;
import com.example.cookingrecipe.Domain.Network.FirebaseRecipe;
import com.example.cookingrecipe.Domain.Network.NetworkHelper;
import com.example.cookingrecipe.R;
import com.example.cookingrecipe.Room.AppDatabase;
import com.example.cookingrecipe.Room.DAO.RecipeDao;
import com.example.cookingrecipe.Room.Entity.RecipeEntity;
import com.example.cookingrecipe.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView recyclerViewRecipeTodayList;
    private RecyclerView getRecyclerViewRecipeFavoriteList;

    private ConstraintLayout searchButton;
    FragmentHomeBinding binding;

    private NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        recyclerViewCategory();
        if(new NetworkHelper().isNetworkConnected(getActivity())){
            recyclerViewRecipeToday();
        }else{
            ConstraintLayout today_layout = binding.recipeTodayLayout;
            today_layout.setVisibility(View.GONE);
        }

        recyclerViewRecipeFavorite();

        searchButton = binding.searchBtn;
        searchButton.setOnClickListener(v -> openSearchFragment());
        return view;
    }

    public void openSearchFragment() {
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.search);
    }

    private void recyclerViewRecipeToday() {
        //danh sach cac cong thuc
        new FirebaseRecipe().getRandomRecipes(12, recipeList -> {

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerViewRecipeTodayList = binding.recyclerViewToday;
            recyclerViewRecipeTodayList.setLayoutManager(linearLayoutManager);

            RecipeTodayAdapter recipeTodayAdapter = new RecipeTodayAdapter(recipeList);
            recipeTodayAdapter.setOnItemClickListener(recipeId -> {
                Intent intent = new Intent(getActivity(), DetailRecipeActivity.class);
                intent.putExtra("recipeId", recipeId);
                startActivity(intent);
            });
            adapter = recipeTodayAdapter;
            recyclerViewRecipeTodayList.setAdapter(adapter);
        });
    }

    private void recyclerViewRecipeFavorite() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        getRecyclerViewRecipeFavoriteList = binding.recyclerViewRecipeFavorite;
        getRecyclerViewRecipeFavoriteList.setLayoutManager(linearLayoutManager);

        AppDatabase db = AppDatabase.getInstance(this.getActivity());
        RecipeDao recipeDao = db.recipeDao();
        List<Recipe> recipeList = new RecipeEntity().toRecipeList(recipeDao.getAll());

        RecipeFavoriteHomeListAdapter recipeFavoriteHomeListAdapter = new RecipeFavoriteHomeListAdapter(recipeList);
        recipeFavoriteHomeListAdapter.setOnItemClickListener(recipeId -> {
            Intent intent = new Intent(getActivity(), DetailRecipeActivity.class);
            intent.putExtra("recipeId", recipeId);
            startActivity(intent);
        });

        adapter = recipeFavoriteHomeListAdapter;
        getRecyclerViewRecipeFavoriteList.setAdapter(adapter);


    }

    private void recyclerViewCategory() {
        //danh sach cac muc
        ArrayList<Type> typeList = new ArrayList<>();
        typeList.add(new Type(0, "Món chính", "burger"));
        typeList.add(new Type(1, "Ăn vặt", "cake"));
        typeList.add(new Type(2, "Sức khỏe", "carrot"));
        typeList.add(new Type(3, "Cơm nhà", "sushi"));
        typeList.add(new Type(4, "Món nước", "carrot"));
        typeList.add(new Type(5, "Món chay", "sushi"));
        typeList.add(new Type(6, "Món canh", "carrot"));
        typeList.add(new Type(7, "Thức uống", "carrot"));
        typeList.add(new Type(8, "Mẹo vặt", "carrot"));
        typeList.add(new Type(9, "Món nước", "carrot"));
        int totalItems = typeList.size();
        int spanCount = (totalItems % 2 == 0) ? totalItems / 2 : (totalItems / 2) + 1;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), spanCount);

        recyclerViewCategoryList = binding.recyclerViewCategory;
        recyclerViewCategoryList.setLayoutManager(gridLayoutManager);

        adapter = new CategoryAdapter(typeList);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}