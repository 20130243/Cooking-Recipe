package com.example.cookingrecipe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingrecipe.Adapter.CategoryAdapter;
import com.example.cookingrecipe.Adapter.RecipeTodayAdapter;
import com.example.cookingrecipe.Domain.Model.Recipe;
import com.example.cookingrecipe.Domain.Model.Type;
import com.example.cookingrecipe.Domain.Network.FirebaseRecipe;
import com.example.cookingrecipe.OnItemClickListener;
import com.example.cookingrecipe.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView recyclerViewRecipeTodayList;
    FragmentHomeBinding binding;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        recyclerViewCategory();
        recyclerViewRecipeToday();

        return view;
    }


    private void recyclerViewRecipeToday() {
        //danh sach cac cong thuc
        new FirebaseRecipe().getAllRecipe(new FirebaseRecipe.RecipeListCallback() {

            @Override
            public void onRecipeListReady(List<Recipe> recipeList) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                recyclerViewRecipeTodayList = binding.recyclerViewToday;
                recyclerViewRecipeTodayList.setLayoutManager(linearLayoutManager);

                RecipeTodayAdapter recipeTodayAdapter = new RecipeTodayAdapter(recipeList);
                recipeTodayAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(String recipeId) {
                        Intent intent = new Intent(getActivity(), DetailRecipeActivity.class);
                        intent.putExtra("recipeId", recipeId);
                        startActivity(intent);
                    }
                });
                adapter = recipeTodayAdapter;

                recyclerViewRecipeTodayList.setAdapter(adapter);
            }
        });

    }

    private void recyclerViewCategory() {
        //danh sach cac muc
        ArrayList<Type> typeList = new ArrayList();
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