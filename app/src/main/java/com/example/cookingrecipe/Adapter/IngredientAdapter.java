package com.example.cookingrecipe.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingrecipe.Domain.Model.Ingredient;
import com.example.cookingrecipe.Domain.Model.Recipe;
import com.example.cookingrecipe.R;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {
    Recipe recipe;

    public IngredientAdapter(Recipe recipe) {
        this.recipe = recipe;
    }

    @NonNull
    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_ingredient, parent, false);
        return new ViewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.ViewHolder holder, int position) {
        Ingredient ingredient = recipe.getIngredients().get(holder.getAdapterPosition());
        holder.ingredient_text.setText(ingredient.getAmount() + " " + ingredient.getUnit() + " " + ingredient.getName());

    }

    @Override
    public int getItemCount() {
        return recipe.getIngredients().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredient_text;
        ConstraintLayout main_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredient_text = itemView.findViewById(R.id.ingredient_text);
            main_layout = itemView.findViewById(R.id.main_layout);
        }
    }
}
