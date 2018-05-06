package com.example.a1.animpartfreezer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.a1.animpartfreezer.Model.Recipe;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddRecipeFragment extends Fragment {
    private EditText RecipeId, RecipeName, Products;
    private Button BnSave;

    public AddRecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add,container,false);

        RecipeId = view.findViewById(R.id.txt_recipe_id);
        RecipeName = view.findViewById(R.id.txt_name);
        Products = view.findViewById(R.id.txt_products);
        BnSave = view.findViewById(R.id.bn_save_recipe);

        BnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   int recipeId = Integer.parseInt(RecipeId.getText().toString());
                String recipename = RecipeName.getText().toString();
                String products = Products.getText().toString();

                Recipe recipe = new Recipe(recipename,products,true);
              //  recipe.setId(recipeId);
                recipe.setRecipeName(recipename);
                recipe.setProductName(products);
                MainActivity.myAppDatabase.myDao().addRecipe(recipe);
                Toast.makeText(getActivity(), "Recipe added successfully", Toast.LENGTH_SHORT).show();

                RecipeId.setText("");
                RecipeName.setText("");
                Products.setText("");

            }
        });

        return view;
    }

}