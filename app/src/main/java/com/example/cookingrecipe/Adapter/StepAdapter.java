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
import com.example.cookingrecipe.Domain.Model.Step;
import com.example.cookingrecipe.R;


public class StepAdapter extends RecyclerView.Adapter<StepAdapter.ViewHolder> {
    Recipe recipe;

    public StepAdapter(Recipe recipe) {
        this.recipe = recipe;
    }

    @NonNull
    @Override
    public StepAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_step, parent, false);
        return new StepAdapter.ViewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull StepAdapter.ViewHolder holder, int position) {

        if (recipe.getSteps() != null) {
            Step step = recipe.getSteps().get(holder.getAdapterPosition());
            holder.step_title.setText("Bước " + step.getStep_order());
            holder.step_text.setText(step.getDescription());

            String imageUrl = recipe.getSteps().get(position).getImage();
            if (imageUrl != null && !imageUrl.isEmpty()) {
                Glide.with(holder.step_image.getContext())
                        .load(imageUrl)
                        .into(holder.step_image);
            } else {
                holder.step_image.setVisibility(View.GONE); // Ẩn ImageView nếu không có ảnh

            }
        }
    }

    @Override
    public int getItemCount() {
        return recipe.getSteps().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView step_title;
        TextView step_text;
        ImageView step_image;
        ConstraintLayout main_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            step_title = itemView.findViewById(R.id.step_title);
            step_text = itemView.findViewById(R.id.ingredient_text);
            step_image = itemView.findViewById(R.id.step_image);
            main_layout = itemView.findViewById(R.id.main_layout);

        }
    }


}
