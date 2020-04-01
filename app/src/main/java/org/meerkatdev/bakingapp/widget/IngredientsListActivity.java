package org.meerkatdev.bakingapp.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.meerkatdev.bakingapp.R;
import org.meerkatdev.bakingapp.data.Ingredient;
import org.meerkatdev.bakingapp.utils.IntentTags;

import java.util.ArrayList;

public class IngredientsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources ress = getResources();
        Bundle extras = getIntent().getExtras();
        if(extras != null && extras.containsKey(IntentTags.INGREDIENTS)) {
            ArrayList<Ingredient> ingredients = extras.getParcelableArrayList(IntentTags.INGREDIENTS);
            String recipeName = extras.getString(IntentTags.RECIPE);

            if (ingredients != null) {
                new MaterialAlertDialogBuilder(this)
                        .setTitle(ress.getString(R.string.ingredients_recipe) + recipeName)
                        .setView(getIngredientsListView(ingredients))
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> finish())
                        .show();
            }
        } else {
            new MaterialAlertDialogBuilder(this)
                    .setTitle(ress.getString(R.string.no_recipe_chosen))
                    .setMessage(ress.getString(R.string.choose_recipe))
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> finish())
                    .show();
        }
    }

    private ListView getIngredientsListView(ArrayList<Ingredient> ingredients) {
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        View view = getLayoutInflater().inflate(R.layout.ingredients_list_view, null, false);
        ListView lv = view.findViewById(R.id.ingredients_list);
        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ingredients));
        return lv;
    }
}
