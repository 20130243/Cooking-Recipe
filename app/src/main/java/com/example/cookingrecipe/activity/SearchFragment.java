package com.example.cookingrecipe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingrecipe.Adapter.RecipeListAdapter;
import com.example.cookingrecipe.Domain.Model.Recipe;
import com.example.cookingrecipe.Domain.Network.FirebaseRecipe;
import com.example.cookingrecipe.Domain.Network.NetworkHelper;
import com.example.cookingrecipe.Room.AppDatabase;
import com.example.cookingrecipe.Room.DAO.RecipeDao;
import com.example.cookingrecipe.Room.Entity.RecipeEntity;
import com.example.cookingrecipe.databinding.FragmentSearchBinding;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class SearchFragment extends Fragment {
    FragmentSearchBinding binding;

    private RecyclerView.Adapter adapter;
    SearchView searchView;
    List<Recipe> recipeList;
    RecyclerView recyclerSearch;
    Boolean isLocalDataLoaded = false;
    Boolean isFirebaseDataLoaded = false;
    private CountDownLatch countDownLatch;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        searchView = binding.search;
        recyclerSearch = binding.recylerSearch;

        showRecipe();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchRecipe(newText);
                return true;
            }
        });


        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
         searchView.requestFocus();
    }


    public void searchRecipe(String text) {
        List<Recipe> searchList = new ArrayList<>();
        if (recipeList != null) {
            if (text != null) {
                for (Recipe recipe : recipeList) {
                    if (removeAccent(recipe.getTitle().toLowerCase())
                            .contains(removeAccent(text.toLowerCase()))) {
                        searchList.add(recipe);
                    }
                }
            } else {
                searchList = recipeList;
            }


            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            recyclerSearch = binding.recylerSearch;
            recyclerSearch.setLayoutManager(linearLayoutManager);

            RecipeListAdapter recipeListAdapter = new RecipeListAdapter(searchList, AppDatabase.getInstance(this.getActivity()));
            recipeListAdapter.setOnItemClickListener(recipeId -> {
                Intent intent = new Intent(getActivity(), DetailRecipeActivity.class);
                intent.putExtra("recipeId", recipeId);
                startActivity(intent);
            });
            recipeListAdapter.setOnFavoriteIconClickListener((position, recipeId) -> {
            });

            adapter = recipeListAdapter;
            recyclerSearch.setAdapter(adapter);
        }
    }

    public void showRecipe() {
        List<Recipe> showList1 = new ArrayList<>();
        AtomicInteger i = new AtomicInteger();
        if (NetworkHelper.isNetworkConnected(this.getActivity())) {
            new FirebaseRecipe().getAllRecipe(list -> {
                recipeList = list;

                isFirebaseDataLoaded = true;

                if (recipeList != null) {
                    Set<Recipe> showSet = new HashSet<>();
                    for (Recipe recipe : recipeList) {
                        if (showSet.size() < 12) {
                            showSet.add(recipe);
                        } else {
                            break;
                        }
                    }

                    List<Recipe> showList = new ArrayList<>(showSet);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    recyclerSearch = binding.recylerSearch;
                    recyclerSearch.setLayoutManager(linearLayoutManager);

                    RecipeListAdapter recipeListAdapter = new RecipeListAdapter(showList, AppDatabase.getInstance(this.getActivity()));
                    recipeListAdapter.setOnItemClickListener(recipeId -> {
                        Intent intent = new Intent(getActivity(), DetailRecipeActivity.class);
                        intent.putExtra("recipeId", recipeId);
                        startActivity(intent);
                    });
                    recipeListAdapter.setOnFavoriteIconClickListener((position, recipeId) -> {
                    });

                    adapter = recipeListAdapter;
                    recyclerSearch.setAdapter(adapter);
                }
            });
        } else {
            AppDatabase db = AppDatabase.getInstance(this.getActivity());
            RecipeDao recipeDao = db.recipeDao();
            recipeList = new RecipeEntity().toRecipeList(recipeDao.getAll());
            isLocalDataLoaded = true;

            if (recipeList != null) {
                Set<Recipe> showSet = new HashSet<>();
                for (Recipe recipe : recipeList) {
                    if (showSet.size() < 12) {
                        showSet.add(recipe);
                    } else {
                        break;
                    }
                }

                List<Recipe> showList = new ArrayList<>(showSet);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                recyclerSearch = binding.recylerSearch;
                recyclerSearch.setLayoutManager(linearLayoutManager);

                RecipeListAdapter recipeListAdapter = new RecipeListAdapter(showList, AppDatabase.getInstance(this.getActivity()));
                recipeListAdapter.setOnItemClickListener(recipeId -> {
                    Intent intent = new Intent(getActivity(), DetailRecipeActivity.class);
                    intent.putExtra("recipeId", recipeId);
                    startActivity(intent);
                });
                recipeListAdapter.setOnFavoriteIconClickListener((position, recipeId) -> {
                });

                adapter = recipeListAdapter;
                recyclerSearch.setAdapter(adapter);
            }
        }

    }

    private String removeAccent(String s) {
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String strFormD = Normalizer.normalize(s, Normalizer.Form.NFD);
        return pattern.matcher(strFormD).replaceAll("").replace('đ', 'd').replace('Đ', 'D');
    }
}