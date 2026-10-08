package com.example.smartpantrymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "smart_pantry.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PANTRY = "pantry";
    public static final String COLUMN_PANTRY_ID = "id";
    public static final String COLUMN_PANTRY_NAME = "name";
    public static final String COLUMN_PANTRY_QTY = "quantity";
    public static final String COLUMN_PANTRY_UNIT = "unit";

    public static final String TABLE_RECIPES = "recipes";
    public static final String COLUMN_RECIPE_ID = "id";
    public static final String COLUMN_RECIPE_NAME = "name";
    public static final String COLUMN_RECIPE_STEPS = "steps";

    public static final String TABLE_RECIPE_INGREDIENTS = "recipe_ingredients";
    public static final String COLUMN_RI_RECIPE_ID = "recipe_id";
    public static final String COLUMN_RI_NAME = "ingredient_name";
    public static final String COLUMN_RI_QTY = "required_qty";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createPantry = "CREATE TABLE " + TABLE_PANTRY + " (" +
                COLUMN_PANTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PANTRY_NAME + " TEXT, " +
                COLUMN_PANTRY_QTY + " REAL, " +
                COLUMN_PANTRY_UNIT + " TEXT);";

        String createRecipes = "CREATE TABLE " + TABLE_RECIPES + " (" +
                COLUMN_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_RECIPE_NAME + " TEXT, " +
                COLUMN_RECIPE_STEPS + " TEXT);";

        String createRecipeIngredients = "CREATE TABLE " + TABLE_RECIPE_INGREDIENTS + " (" +
                COLUMN_RI_RECIPE_ID + " INTEGER, " +
                COLUMN_RI_NAME + " TEXT, " +
                COLUMN_RI_QTY + " REAL);";

        db.execSQL(createPantry);
        db.execSQL(createRecipes);
        db.execSQL(createRecipeIngredients);

        seedRecipes(db);
    }

    private void seedRecipes(SQLiteDatabase db) {
        insertRecipeWithIngredients(db, "Scrambled Eggs", "Beat eggs with salt. Melt butter in pan over low heat. Cook until soft curds form.",
                new String[]{"egg", "butter", "salt"}, new double[]{2, 1, 1});

        insertRecipeWithIngredients(db, "Omelette", "Whisk eggs. Pour into hot oiled pan. Add cheese, fold over, and serve.",
                new String[]{"egg", "cheese", "oil"}, new double[]{3, 1, 1});

        insertRecipeWithIngredients(db, "Grilled Cheese Sandwich", "Butter bread. Place cheese between slices. Grill in pan until golden.",
                new String[]{"bread", "cheese", "butter"}, new double[]{2, 2, 1});

        insertRecipeWithIngredients(db, "French Toast", "Whisk egg and milk. Dip bread. Fry in butter until brown on both sides.",
                new String[]{"bread", "egg", "milk", "butter"}, new double[]{2, 1, 1, 1});

        insertRecipeWithIngredients(db, "Basic Pasta", "Boil pasta in salted water. Drain, toss with olive oil and garlic.",
                new String[]{"pasta", "oil", "garlic", "salt"}, new double[]{1, 1, 1, 1});

        insertRecipeWithIngredients(db, "Tomato Pasta", "Boil pasta. Sauté garlic and canned tomato in oil. Mix together.",
                new String[]{"pasta", "tomato", "garlic", "oil"}, new double[]{1, 2, 1, 1});

        insertRecipeWithIngredients(db, "Fried Rice", "Heat oil. Sauté rice with egg, garlic, and salt until fluffy.",
                new String[]{"rice", "egg", "garlic", "oil", "salt"}, new double[]{1, 2, 1, 1, 1});

        insertRecipeWithIngredients(db, "Rice and Beans", "Cook rice. Heat canned beans with garlic and oil. Serve together.",
                new String[]{"rice", "beans", "garlic", "oil"}, new double[]{1, 1, 1, 1});

        insertRecipeWithIngredients(db, "Garlic Bread", "Slice bread. Spread mixed butter and garlic. Bake at 180C for 10 mins.",
                new String[]{"bread", "butter", "garlic"}, new double[]{1, 1, 1});

        insertRecipeWithIngredients(db, "Pancakes", "Mix flour, egg, and milk into batter. Cook spoonfuls on oiled hot pan.",
                new String[]{"flour", "egg", "milk", "oil"}, new double[]{1, 1, 1, 1});

        insertRecipeWithIngredients(db, "Egg Salad", "Boil eggs. Mash with salt and oil or mayonnaise. Serve on bread.",
                new String[]{"egg", "bread", "salt", "oil"}, new double[]{2, 2, 1, 1});

        insertRecipeWithIngredients(db, "Cheese Toastie", "Place cheese on bread. Toast in oven or toaster until melted.",
                new String[]{"bread", "cheese"}, new double[]{2, 1});

        insertRecipeWithIngredients(db, "Boiled Eggs with Salt", "Boil eggs in water for 8 minutes. Peel and season with salt.",
                new String[]{"egg", "salt"}, new double[]{2, 1});

        insertRecipeWithIngredients(db, "Simple Tomato Salad", "Slice tomatoes. Drizzle with oil and season with salt.",
                new String[]{"tomato", "oil", "salt"}, new double[]{2, 1, 1});

        insertRecipeWithIngredients(db, "Garlic Rice", "Fry garlic in oil until golden. Add cooked rice and stir fry.",
                new String[]{"rice", "garlic", "oil"}, new double[]{1, 2, 1});
    }

    private void insertRecipeWithIngredients(SQLiteDatabase db, String name, String steps, String[] ings, double[] qtys) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_RECIPE_NAME, name);
        cv.put(COLUMN_RECIPE_STEPS, steps);
        long recId = db.insert(TABLE_RECIPES, null, cv);

        for (int i = 0; i < ings.length; i++) {
            ContentValues ingCv = new ContentValues();
            ingCv.put(COLUMN_RI_RECIPE_ID, recId);
            ingCv.put(COLUMN_RI_NAME, ings[i]);
            ingCv.put(COLUMN_RI_QTY, qtys[i]);
            db.insert(TABLE_RECIPE_INGREDIENTS, null, ingCv);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_INGREDIENTS);
        onCreate(db);
    }
// --- Pantry CRUD Operations ---

    public boolean addPantryItem(String name, double quantity, String unit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("quantity", quantity);
        cv.put("unit", unit);
        long result = db.insert("pantry", null, cv);
        return result != -1;
    }

    public boolean updatePantryItem(int id, String name, double quantity, String unit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("quantity", quantity);
        cv.put("unit", unit);
        int result = db.update("pantry", cv, "id=?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean deletePantryItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("pantry", "id=?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public java.util.List<PantryItem> getAllPantryItems() {
        java.util.List<PantryItem> itemList = new java.util.ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM pantry", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                double quantity = cursor.getDouble(cursor.getColumnIndexOrThrow("quantity"));
                String unit = cursor.getString(cursor.getColumnIndexOrThrow("unit"));
                itemList.add(new PantryItem(id, name, quantity, unit));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return itemList;
    }

    // --- Recipe Matching Logic (Strict Matching) ---

    public java.util.List<String> getSuggestedRecipes() {
        java.util.List<String> suggestedRecipes = new java.util.ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT r.name FROM recipes r " +
                "WHERE NOT EXISTS (" +
                "    SELECT ri.ingredient_name FROM recipe_ingredients ri " +
                "    WHERE ri.recipe_id = r.id " +
                "    EXCEPT " +
                "    SELECT p.name FROM pantry p" +
                ")";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                suggestedRecipes.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return suggestedRecipes;
    }
    // Method using normalized string comparisons
    public java.util.List<String> getSuggestedRecipesNormalized() {
        java.util.List<String> matchingRecipes = new java.util.ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Get all pantry item names normalized
        java.util.Set<String> pantryItems = new java.util.HashSet<>();
        Cursor pantryCursor = db.rawQuery("SELECT name FROM pantry", null);
        if (pantryCursor.moveToFirst()) {
            do {
                pantryItems.add(normalize(pantryCursor.getString(0)));
            } while (pantryCursor.moveToNext());
        }
        pantryCursor.close();

        // Check recipes against normalized pantry items
        Cursor recipeCursor = db.rawQuery("SELECT id, name FROM recipes", null);
        if (recipeCursor.moveToFirst()) {
            do {
                int recipeId = recipeCursor.getInt(0);
                String recipeName = recipeCursor.getString(1);

                Cursor ingCursor = db.rawQuery("SELECT ingredient_name FROM recipe_ingredients WHERE recipe_id = ?",
                        new String[]{String.valueOf(recipeId)});

                boolean hasAllIngredients = true;
                if (ingCursor.moveToFirst()) {
                    do {
                        String ingredient = normalize(ingCursor.getString(0));
                        if (!pantryItems.contains(ingredient)) {
                            hasAllIngredients = false;
                            break;
                        }
                    } while (ingCursor.moveToNext());
                }
                ingCursor.close();

                if (hasAllIngredients) {
                    matchingRecipes.add(recipeName);
                }
            } while (recipeCursor.moveToNext());
        }
        recipeCursor.close();

        return matchingRecipes;
    }

    // Helper method to normalize ingredient names (trimming, lowercase, singularization)
    private String normalize(String input) {
        if (input == null) return "";
        String trimmed = input.trim().toLowerCase();
        if (trimmed.endsWith("es") && trimmed.length() > 3) {
            return trimmed.substring(0, trimmed.length() - 2);
        } else if (trimmed.endsWith("s") && trimmed.length() > 2) {
            return trimmed.substring(0, trimmed.length() - 1);
        }
        return trimmed;
    }
}
