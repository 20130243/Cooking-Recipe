package com.example.cookingrecipe.Room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cookingrecipe.Room.Entity.RecipeEntity;

import java.util.List;

@Dao
public interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RecipeEntity recipe);

    @Query("SELECT * FROM recipes WHERE id = :id")
    RecipeEntity getById(String id);

    @Query("SELECT * FROM recipes")
    List<RecipeEntity> getAll();

    @Update
    void update(RecipeEntity recipe);

    @Delete
    void delete(RecipeEntity recipe);
}
