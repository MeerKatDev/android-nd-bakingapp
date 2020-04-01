package org.meerkatdev.bakingapp.utils;

import androidx.appcompat.app.AppCompatActivity;

import org.meerkatdev.bakingapp.data.RecipeStep;

public abstract class RecipeStepHandler extends AppCompatActivity {

    public abstract RecipeStep getFirstRecipeStep();
}
