package org.meerkatdev.bakingapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.meerkatdev.bakingapp.utils.ListItemClickListener;

public class RecipeStepsListActivity extends AppCompatActivity implements ListItemClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_detail_activity);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }
}
