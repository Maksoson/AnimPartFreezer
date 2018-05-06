package com.example.a1.animpartfreezer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.example.a1.animpartfreezer.Model.Recipe;

import java.util.List;

/**
 * Created by 1 on 05.05.2018.
 */
@Dao
public interface MyDao {

    @Insert
    public void addRecipe(Recipe recipe);

    @Query("select * from Recipes")
    public List<Recipe> getRecipes();

    @Delete
    public void deleteRecipe(Recipe recipe);

    @Update
    public void updateRecipe(Recipe recipe);
}

