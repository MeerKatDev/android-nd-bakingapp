package org.meerkatdev.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import org.meerkatdev.bakingapp.data.Recipe;
import org.meerkatdev.bakingapp.data.RecipeStep;
import org.meerkatdev.bakingapp.utils.IntentTags;
import org.meerkatdev.bakingapp.utils.RecipeStepHandler;

public class RecipeContentActivity extends RecipeStepHandler {

    private Recipe recipe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_content);
        Bundle b = getIntent().getExtras();
        if(b != null && b.containsKey(IntentTags.RECIPE))
            recipe = b.getParcelable(IntentTags.RECIPE);

        if(b != null && b.containsKey(IntentTags.RECIPE_STEP)) {
            RecipeStep recipeStep = (RecipeStep) b.get(IntentTags.RECIPE_STEP);
            setupNavButtons(b, recipeStep);
        }
    }



    private void setupNavButtons(Bundle b, RecipeStep recipeStep) {
        if(recipeStep.id > 0) {
            findViewById(R.id.nav_previous).setOnClickListener(click -> {
                Intent intent = new Intent(this, RecipeContentActivity.class);
                intent.putExtras(b);
                intent.putExtra(IntentTags.RECIPE_STEP, recipe.steps[recipeStep.id-1]);
                startActivity(intent);
            });
        } else {
            findViewById(R.id.nav_previous).setVisibility(View.INVISIBLE);
        }

        if(recipeStep.id < (recipe.steps.length-2)) {
            findViewById(R.id.nav_next).setOnClickListener(click -> {
                Intent intent = new Intent(this, RecipeContentActivity.class);
                intent.putExtras(b);
                intent.putExtra(IntentTags.RECIPE_STEP, recipe.steps[recipeStep.id+1]);
                startActivity(intent);
            });
        } else {
            findViewById(R.id.nav_next).setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public RecipeStep getFirstRecipeStep() {
        return null;
    }
}
