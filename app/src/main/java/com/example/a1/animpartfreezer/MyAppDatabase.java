package com.example.a1.animpartfreezer;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.a1.animpartfreezer.Model.Recipe;

@Database(entities = {Recipe.class}, version = 1)
public abstract class MyAppDatabase extends RoomDatabase
{

    public abstract MyDao myDao();
}

