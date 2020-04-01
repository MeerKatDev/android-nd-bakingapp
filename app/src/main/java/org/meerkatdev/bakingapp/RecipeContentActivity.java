package org.meerkatdev.bakingapp;

import android.os.Bundle;
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
        if(b != null && b.containsKey(IntentTags.RECIPE)) {
            recipe = b.getParcelable(IntentTags.RECIPE);
        }
    }

    @Override
    public RecipeStep getFirstRecipeStep() {
        return recipe.steps[0];
    }
}
