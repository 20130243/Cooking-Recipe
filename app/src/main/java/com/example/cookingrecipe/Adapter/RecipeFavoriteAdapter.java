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
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_today, parent, false);
        return new ViewHolder(inflater);
    }



    @Override
    public void onBindViewHolder(@NonNull RecipeFavoriteAdapter.ViewHolder holder, int position) {
        Recipe recipe = recipeList.get(holder.getAdapterPosition());

        holder.recipeTitle.setText(recipe.getTitle());

        String imageURL = recipe.getImage();
        holder.main_layout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_bg));

        if (!imageURL.isBlank()) {
            Glide.with(holder.recipeImage.getContext())
                    .load(imageURL)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            System.out.println("Lỗi khi tải ảnh: " + e.getMessage());
                            return false;
                        }
                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            System.out.println("Tải ảnh thành công");
                            return false;
                        }
                    })
                    .into(holder.recipeImage);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(recipe.getId());
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView recipeTitle;
        ImageView recipeImage;
        ConstraintLayout main_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeTitle = itemView.findViewById(R.id.recipe_today_title);
            recipeImage = itemView.findViewById(R.id.recipe_today_image);
            main_layout = itemView.findViewById(R.id.recipe_today_layout);
        }
    }
}