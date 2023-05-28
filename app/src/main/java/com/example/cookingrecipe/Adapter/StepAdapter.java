package com.example.cookingrecipe.Adapter;

import static androidx.core.content.ContextCompat.getSystemService;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
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
            Step step = recipe.getSteps().get(position);
            holder.step_title.setText("Bước " + step.getStep_order());
            holder.step_text.setText(step.getDescription());

            String imageUrl = recipe.getSteps().get(position).getImage();
            if (!imageUrl.isBlank()) {
                Glide.with(holder.step_image.getContext())
                        .load(imageUrl)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                System.out.println("Lỗi khi tải ảnh: " + e.getMessage());
                                Log.e("Glide", "Lỗi khi tải ảnh: " + e.getMessage());
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                System.out.println("Tải ảnh thành công");
                                Log.d("Glide", "Tải ảnh thành công");
                                return false;
                            }
                        })
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
            step_text = itemView.findViewById(R.id.step_text);
            step_image = itemView.findViewById(R.id.step_image);
            main_layout = itemView.findViewById(R.id.main_layout);

        }
    }


}
