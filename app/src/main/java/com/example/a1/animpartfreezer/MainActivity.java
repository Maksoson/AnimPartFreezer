package com.example.a1.animpartfreezer;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a1.animpartfreezer.Adapter.MyAdapter;

import com.example.a1.animpartfreezer.Model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button AddBn;
    private TextView mTextMessage;
    public static MyAppDatabase myAppDatabase;
    public static android.support.v4.app.FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    RecyclerView list;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        fragmentManager = getSupportFragmentManager();
//
//        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class,"RecopeDB")
//                .allowMainThreadQueries().build();
//
//        if(findViewById(R.id.Container)!=null)
//        {
//            if(savedInstanceState!=null){
//                return;
//            }
//
//            fragmentManager.beginTransaction().add(R.id.Container, new HomeFragment()).commit();
//
//        }

        AddBn = findViewById(R.id.Add);

        list = (RecyclerView) findViewById(R.id.recycler);
        list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "RecipeDB")
                .allowMainThreadQueries().build();

        List<Recipe> recipes = MainActivity.myAppDatabase.myDao().getRecipes();

//        for (Recipe rcp : recipes) {
//            String recipename = rcp.getRecipeName();
//            String products = rcp.getProductName();
//            Recipe recipe = new Recipe("" + recipename, "" + products, true);
//
//            recipes.add(recipe);
//        }

        MyAdapter adapter = new MyAdapter(recipes);
        list.setAdapter(adapter);

    }

    public void onClickAdd(View view){
        MainActivity.fragmentManager.beginTransaction().replace(R.id.Container, new AddRecipeFragment())
                .addToBackStack(null).commit();
    }

//            case R.id.bn_delete_recipe:
//                MainActivity.fragmentManager.beginTransaction().replace(R.id.Container, new DeleteRecipeFragment())
//                        .addToBackStack(null).commit();
//                break;
//
//            case R.id.bn_update_recipe:
//                MainActivity.fragmentManager.beginTransaction().replace(R.id.Container, new UpdateFragment())
//                        .addToBackStack(null).commit();
//                break;
        }



//    private void setData() {
////        for (int i=0;i<3;i++){
////            if (i%1 ==0){
////                Recipe recipe = new Recipe("Recipe "+(i+1), "Special Products List... " ,true);
////                recipes.add(recipe);
////            }
////            else{
////                Recipe recipe = new Recipe("Recipe "+(i+1), "", false);
////                recipes.add(recipe);
////            }
////        }
//
//
//
//        MyAdapter adapter = new MyAdapter(recipes);
//        list.setAdapter(adapter);
//    }
//
//}
