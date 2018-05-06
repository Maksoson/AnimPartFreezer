package com.example.a1.animpartfreezer.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;


@Entity(tableName = "Recipes")
public class Recipe {

    @ColumnInfo(name = "Recipe_Name")
    private String RecipeName;

    @ColumnInfo(name = "Product_Name")
    private String ProductName;

    @PrimaryKey
    private boolean isExpandable;



    public Recipe(String RecipeName, String ProductName, boolean isExpandable){
        this.RecipeName = RecipeName;
        this.ProductName = ProductName;
        this.isExpandable = isExpandable;
    }

//    @PrimaryKey
//    private int id;

    public String getRecipeName() {
        return RecipeName;
    }
//
//    public int getId() {
//        return Id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public void setRecipeName(String recipeName) {
        this.RecipeName = recipeName;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        this.ProductName = productName;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }
}

