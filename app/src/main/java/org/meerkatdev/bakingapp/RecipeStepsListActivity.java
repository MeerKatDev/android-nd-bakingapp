package org.meerkatdev.bakingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import org.meerkatdev.bakingapp.data.RecipeStep;
import org.meerkatdev.bakingapp.utils.ItemClickListener;

public class RecipeStepsListActivity extends AppCompatActivity implements ItemClickListener {

    private boolean mTwoPane = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_detail_activity);

        if(findViewById(R.id.pv_instruction_video) != null) {
            // tablet view
            mTwoPane = true;

        }
    }

    @Override
    public void onItemClick(RecipeStep recipeStep) {
        Intent intent = new Intent(this, RecipeContentActivity.class);
        intent.putExtra("recipe_step", recipeStep);
        startActivity(intent);
    }
}
