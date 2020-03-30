package org.meerkatdev.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Parcelable;

import org.meerkatdev.bakingapp.utils.JSONUtils;

import org.meerkatdev.bakingapp.R;

/**
 * https://review.udacity.com/#!/rubrics/829/view
 * FRAGMENTS (or BASICS
 * - App should display recipes from provided network resource.
 * - App should allow navigation between individual recipes and recipe steps.
 * - App uses RecyclerView and can handle recipe steps that include videos or images.
 * - Application uses Master Detail Flow to display recipe steps and navigation between them.
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
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
//    private MovieAdapter movieAdapter;
//    private AppDatabase mDb;
//    MovieViewModel movieViewModel;
    GridLayoutManager glm;
    Parcelable mListState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String json = JSONUtils.getJsonFromRaw(this, R.raw.baking);

    }


}
