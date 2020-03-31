package org.meerkatdev.bakingapp;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import org.meerkatdev.bakingapp.ui.StepContentFragment;

public class RecipeContentActivity extends FragmentActivity {

//    public FragmentManager fragmentManager = getSupportFragmentManager();
//    private StepContentFragment contentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_content);
        //contentFragment = (StepContentFragment) fragmentManager.findFragmentById(R.id.step_content_fragment);
    }

}
