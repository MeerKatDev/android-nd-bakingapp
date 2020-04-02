package org.meerkatdev.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.meerkatdev.bakingapp.adapters.RecipeAdapter;
import org.meerkatdev.bakingapp.data.Recipe;
import org.meerkatdev.bakingapp.utils.IntentTags;
import org.meerkatdev.bakingapp.utils.JSONUtils;
import org.meerkatdev.bakingapp.utils.ListItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements ListItemClickListener {

    GridLayoutManager glm;
    Parcelable mListState;
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;

    private final static String TAG = MainActivity.class.getSimpleName();
    private final static String LIST_STATE_KEY = "list-state-key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(getResources().getString(R.string.recipes_list));
        Recipe[] recipes = getRecipesFromJson(this);
        setupRecyclerView();
        recipeAdapter.setData(recipes);
    }

    public Recipe[] getRecipesFromJson(Context ctx) {
        String json = JSONUtils.getJsonFromRaw(ctx, R.raw.baking);
        return JSONUtils.convertJsonToRecipeList(json);
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {
        Recipe recipe = recipeAdapter.elements[clickedItemIndex];
        Intent intent = new Intent(this, RecipeStepsListActivity.class);
        intent.putExtra(IntentTags.RECIPE, recipe);
        startActivity(intent);
    }

    private void setupRecyclerView() {
        recyclerView = this.findViewById(R.id.rv_recipes);
        glm = new GridLayoutManager(this, numberOfColumns());
        recyclerView.setLayoutManager(glm);
        recyclerView.setHasFixedSize(true);
        recipeAdapter = new RecipeAdapter(0, this);
        recyclerView.setAdapter(recipeAdapter);
    }

    private int numberOfColumns() {
        return getResources().getBoolean(R.bool.is_tablet) ? 3 : 1;
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (null != mListState) {
            glm.onRestoreInstanceState(mListState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        mListState = glm.onSaveInstanceState();
        state.putParcelable(LIST_STATE_KEY, mListState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        mListState = state.getParcelable(LIST_STATE_KEY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings_item) {
            Intent intent = new Intent(this, SettingsActivity.class);
            ArrayList<Recipe> recipes = new ArrayList<>(Arrays.asList(recipeAdapter.elements));
            intent.putParcelableArrayListExtra(IntentTags.RECIPES, recipes);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
