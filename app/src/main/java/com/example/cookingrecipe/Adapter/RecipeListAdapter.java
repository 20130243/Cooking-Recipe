package com.example.cookingrecipe.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cookingrecipe.Domain.Model.Recipe;
import com.example.cookingrecipe.OnFavoriteIconClickListener;
import com.example.cookingrecipe.OnItemClickListener;
import com.example.cookingrecipe.R;
import com.example.cookingrecipe.Room.AppDatabase;
import com.example.cookingrecipe.Room.DAO.RecipeDao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {
    List<Recipe> recipeList;
    AppDatabase db;
    private OnItemClickListener itemClickListener;
    private OnFavoriteIconClickListener favoriteIconClickListener;


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public void setOnFavoriteIconClickListener(OnFavoriteIconClickListener listener) {
        this.favoriteIconClickListener = listener;
    }

    public RecipeListAdapter(List<Recipe> recipeList, AppDatabase db) {
        this.recipeList = recipeList;
        this.db = db;
    }

    @NonNull
    @Override
    public RecipeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_favorite, parent, false);
        return new ViewHolder(inflater);
    }


    @Override
    public void onBindViewHolder(@NonNull RecipeListAdapter.ViewHolder holder, int position) {
        Recipe recipe = recipeList.get(holder.getAdapterPosition());
        RecipeDao recipeDao = db.recipeDao();
        AtomicReference<Boolean> isFavorite = new AtomicReference<>(recipeDao.getById(recipe.getId()) != null);
        if (isFavorite.get()) {
            holder.icon_favorite.setImageResource(R.drawable.ic_favorite_fill);
        } else {
            holder.icon_favorite.setImageResource(R.drawable.ic_favorite);
        }

        holder.titleFavorite.setText(recipe.getTitle());
        holder.descriptionFavorite.setText(recipe.getDescription());

        String imageURL = recipe.getImage();
        if (!imageURL.isBlank()) {
            Glide.with(holder.imageFavorite.getContext())
                    .load(imageURL)
                    .into(holder.imageFavorite);
        }

        holder.click_area.setOnClickListener(view -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(recipe.getId());
            }
        });

        holder.icon_favorite.setOnClickListener(view -> {
            if (favoriteIconClickListener != null) {
                if (isFavorite.get()) {
                    isFavorite.set(false);
                    recipeDao.delete(recipe.toRecipeEntity());
                    holder.icon_favorite.setImageResource(R.drawable.ic_favorite);
                } else {
                    isFavorite.set(true);
                    recipeDao.insert(recipe.toRecipeEntity());
                    holder.icon_favorite.setImageResource(R.drawable.ic_favorite_fill);
                }
                favoriteIconClickListener.onIconClick(recipe.getId(), true);
            }
        });
    }


    @Override
    public int getItemCount() {

        return  recipeList == null ? 0 : recipeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleFavorite;
        TextView descriptionFavorite;
        ImageView imageFavorite;
        ConstraintLayout main_layout;
        ConstraintLayout click_area;
        ImageView icon_favorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageFavorite = itemView.findViewById(R.id.image_favorite);
            titleFavorite = itemView.findViewById(R.id.title_favorite);
            descriptionFavorite = itemView.findViewById(R.id.description_favorite);
            main_layout = itemView.findViewById(R.id.main_layout);
            click_area = itemView.findViewById(R.id.click_area);
            icon_favorite = itemView.findViewById(R.id.icon_favorite);
        }
    }
}
