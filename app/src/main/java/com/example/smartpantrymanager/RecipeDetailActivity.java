package com.example.smartpantrymanager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class RecipeDetailActivity extends Activity {

    private TextView tvRecipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvRecipeName = new TextView(this);

        String recipeName = getIntent().getStringExtra("RECIPE_NAME");
        if (recipeName != null) {
            tvRecipeName.setText(recipeName);
        }

        setContentView(tvRecipeName);
    }
}