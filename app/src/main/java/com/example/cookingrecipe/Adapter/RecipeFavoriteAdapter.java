package com.example.cookingrecipe.Adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.cookingrecipe.Domain.Model.Recipe;
import com.example.cookingrecipe.OnItemClickListener;
import com.example.cookingrecipe.R;

import java.util.List;

public class RecipeFavoriteAdapter extends RecyclerView.Adapter<RecipeFavoriteAdapter.ViewHolder> {
    List<Recipe> recipeList;
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public RecipeFavoriteAdapter(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }


    @NonNull
    @Override
    public RecipeFavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_favorite, parent, false);
        return new ViewHolder(inflater);
    }


    @Override
    public void onBindViewHolder(@NonNull RecipeFavoriteAdapter.ViewHolder holder, int position) {
        Recipe recipe = recipeList.get(holder.getAdapterPosition());

        holder.titleFavorite.setText(recipe.getTitle());
        holder.descriptionFavorite.setText(recipe.getDescription());

        String imageURL = recipe.getImage();
        if (!imageURL.isBlank()) {
            Glide.with(holder.imageFavorite.getContext())
                    .load(imageURL)
                    .into(holder.imageFavorite);
        }

        holder.click_area.setOnClickListener(view -> {
            if (listener != null) {
                listener.onItemClick(recipe.getId());
            }
        });
    }


    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleFavorite;
        TextView descriptionFavorite;
        ImageView imageFavorite;
        ConstraintLayout main_layout;
        ConstraintLayout click_area;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageFavorite = itemView.findViewById(R.id.image_favorite);
            titleFavorite = itemView.findViewById(R.id.title_favorite);
            descriptionFavorite = itemView.findViewById(R.id.description_favorite);
            main_layout = itemView.findViewById(R.id.main_layout);
            click_area = itemView.findViewById(R.id.click_area);
        }
    }
}
