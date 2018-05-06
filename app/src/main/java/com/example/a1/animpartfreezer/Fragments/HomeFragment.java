package com.example.a1.animpartfreezer.Fragments;



import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a1.animpartfreezer.Adapter.MyAdapter;
import com.example.a1.animpartfreezer.AddRecipeFragment;
import com.example.a1.animpartfreezer.MainActivity;
import com.example.a1.animpartfreezer.Model.Recipe;
import com.example.a1.animpartfreezer.MyAppDatabase;
import com.example.a1.animpartfreezer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener
{
    private Button BnAddRecipe,BnReadRecipes, BnDeleteRecipe, BnUpdateRecipe;
    private RecyclerView list;
    private RecyclerView.LayoutManager layoutManager;
    private List<Recipe> recipess = new ArrayList<>();
    public static MyAppDatabase myAppDatabase;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        BnAddRecipe = view.findViewById(R.id.Add);
        BnAddRecipe.setOnClickListener(this);

        list = (RecyclerView) view.findViewById(R.id.recycler);
        list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(layoutManager);

        myAppDatabase = Room.databaseBuilder(getContext(), MyAppDatabase.class, "RecipeDB")
                .allowMainThreadQueries().build();

        List<Recipe> recipes = MainActivity.myAppDatabase.myDao().getRecipes();

        for (Recipe rcp : recipes) {
            String recipename = rcp.getRecipeName();
            String products = rcp.getProductName();
            Recipe recipe = new Recipe("" + recipename, "" + products, true);

            recipess.add(recipe);
        }

        MyAdapter adapter = new MyAdapter(recipess);
        list.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Add:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.Container, new AddRecipeFragment())
                        .addToBackStack(null).commit();
                break;

        }
    }
}
