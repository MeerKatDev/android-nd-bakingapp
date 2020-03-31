package org.meerkatdev.bakingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import org.meerkatdev.bakingapp.data.Recipe;
import org.meerkatdev.bakingapp.data.RecipeStep;
import org.meerkatdev.bakingapp.ui.StepContentFragment;
import org.meerkatdev.bakingapp.utils.ItemClickListener;

public class RecipeStepsListActivity extends FragmentActivity implements ItemClickListener {

    private final String TAG = this.getClass().getSimpleName();
    private final String LIFECYCLE_TAG = "LIFECYCLE";
    private final static String CONTENT_FRAGMENT_TAG = "content_fragment";

    private boolean mTwoPane = false;
    private StepContentFragment contentFragment;
    //private StepsListFragment stepsListFragment;
    public Recipe recipe;
    public FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        if(b != null && b.containsKey("recipe")) {
            recipe = b.getParcelable("recipe");
        }
        setContentView(R.layout.activity_recipe_steps);
        Log.d(LIFECYCLE_TAG, "creating " + TAG);

        if(findViewById(R.id.pv_instruction_video) != null) {
            mTwoPane = true;
            Log.d(LIFECYCLE_TAG, "pv_instruction_video not null");
            contentFragment = (StepContentFragment) fragmentManager.findFragmentById(R.id.step_content_fragment);
            //stepsListFragment = (StepsListFragment) fragmentManager.findFragmentById(R.id.steps_list_fragment);
            Log.d(LIFECYCLE_TAG, "fragment casted");
            hideNavButtons(contentFragment);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LIFECYCLE_TAG, TAG + " onSaveInstanceState");

    }

    @Override
    protected void onResume() {
        super.onResume();
        hideNavButtons(contentFragment);
    }

    private void hideNavButtons(Fragment fragment) {
        if((contentFragment != null)
                && (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)) {
            try {
                fragment.getView().findViewById(R.id.mobile_arrows).setVisibility(View.GONE);
            } catch(NullPointerException e) {
                Log.d(TAG, e.getMessage());
            }
        }

    }

    public RecipeStep getFirstRecipeStep() {
        return recipe.steps[0];
    }

    @Override
    public void onItemClick(RecipeStep recipeStep) {
        Log.d(LIFECYCLE_TAG, "clicked on recipe step");
        if(mTwoPane) {
            contentFragment.setView(this, recipeStep);
        } else {
            Intent intent = new Intent(this, RecipeContentActivity.class);
            intent.putExtra("recipe_step", recipeStep);
            startActivity(intent);
        }
    }
}
