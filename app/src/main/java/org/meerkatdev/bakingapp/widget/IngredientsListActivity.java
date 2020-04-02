package org.meerkatdev.bakingapp.widget;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.meerkatdev.bakingapp.R;
import org.meerkatdev.bakingapp.adapters.IngredientAdapter;
import org.meerkatdev.bakingapp.data.Ingredient;
import org.meerkatdev.bakingapp.utils.IntentTags;

import java.util.Objects;

public class IngredientsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_list_view);
        Bundle extras = getIntent().getExtras();
        if(extras != null && extras.containsKey(IntentTags.INGREDIENTS)) {
            String recipeName = extras.getString(IntentTags.RECIPE_NAME);
            Ingredient[] ingredients = getIngredientsFromParcel(Objects.requireNonNull(extras.getParcelableArray(IntentTags.INGREDIENTS)));
            new MaterialAlertDialogBuilder(this)
                    .setTitle(getResources().getString(R.string.ingredients_recipe) + recipeName)
                    .setView(getIngredientsListView(ingredients))
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> finish())
                    .show();
        } else {
            new MaterialAlertDialogBuilder(this)
                    .setTitle(getResources().getString(R.string.no_recipe_chosen))
                    .setMessage(getResources().getString(R.string.choose_recipe))
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> finish())
                    .show();
        }

    }

    private Ingredient[] getIngredientsFromParcel(Parcelable[] parceledIngredients) {
        Ingredient[] ingredients = new Ingredient[parceledIngredients.length];
        System.arraycopy(parceledIngredients, 0, ingredients, 0, parceledIngredients.length);
        return ingredients;
    }

    private ListView getIngredientsListView(Ingredient[] ingredients) {
        ListView lv = new ListView(this);
        lv.setAdapter(new IngredientAdapter(this, ingredients));
        return lv;
    }

}
