package com.example.smartpantrymanager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;

public class SuggestedRecipesActivity extends Activity {

    private ListView lvSuggestedRecipes;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DatabaseHelper(this);

        lvSuggestedRecipes = new ListView(this);
        setContentView(lvSuggestedRecipes);

        List<String> suggestedRecipes = dbHelper.getSuggestedRecipes();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                suggestedRecipes
        );
        lvSuggestedRecipes.setAdapter(adapter);
    }
}