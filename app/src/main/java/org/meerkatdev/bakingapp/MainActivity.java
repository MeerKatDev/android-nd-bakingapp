package org.meerkatdev.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.util.Log;

import org.meerkatdev.bakingapp.adapters.RecipeAdapter;
import org.meerkatdev.bakingapp.data.Recipe;
import org.meerkatdev.bakingapp.utils.JSONUtils;
import org.meerkatdev.bakingapp.utils.ListItemClickListener;

/**
 * https://review.udacity.com/#!/rubrics/829/view
 * FRAGMENTS (or BASICS
 * - OK App should display recipes from provided network resource.
 * - OK App should allow navigation between individual recipes and recipe steps.
 * - OK App uses RecyclerView and can handle recipe steps that include videos or images.
 * - OK Application uses Master Detail Flow to display recipe steps and navigation between them.
 *  EXOPLAYER
 * - Application uses Exoplayer to display videos.
 * - Application properly initializes and releases video assets when appropriate.
 * - Application should properly retrieve media assets from the provided network links.
 *   It should properly handle network requests.
 *  TESTING
 * - Application makes use of Espresso to test aspects of the UI.,
 *  THIRD-PARTY LIBRARY
 * - Application sensibly utilizes a third-party library to enhance the app's features.
 *  WIDGETS
 * - Application has a companion homescreen widget.
 * - Widget displays ingredient list for desired recipe.
 * Cards: https://material.io/components/cards/#cards%C2%ADusage
 */
public class MainActivity extends AppCompatActivity implements ListItemClickListener {

    GridLayoutManager glm;
    Parcelable mListState;
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
//    private AppDatabase mDb;
//    MovieViewModel movieViewModel;

    // TODO add json parsing to tests
    // TODO add two intents test
    // orientation rotation test?
    // TODO add preferences autoplay

    private final static String TAG = MainActivity.class.getSimpleName();
    private final static String LIST_STATE_KEY = "list-state-key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String json = JSONUtils.getJsonFromRaw(this, R.raw.baking);
        Recipe[] recipes = JSONUtils.convertJsonToRecipeList(json);
        setupRecyclerView();
        recipeAdapter.setData(recipes);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Recipe recipe = recipeAdapter.elements[clickedItemIndex];
        Intent intent = new Intent(this, RecipeStepsListActivity.class);
        intent.putExtra("recipe", recipe);
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
        Log.d(TAG, "Resuming activity");
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

}
