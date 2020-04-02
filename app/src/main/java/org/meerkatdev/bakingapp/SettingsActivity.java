package org.meerkatdev.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

import org.meerkatdev.bakingapp.data.Recipe;
import org.meerkatdev.bakingapp.utils.IntentTags;
import org.meerkatdev.bakingapp.widget.IngredientListWidgetProvider;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    protected ArrayList<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipes = getIntent().getExtras().getParcelableArrayList(IntentTags.RECIPES);
        setContentView(R.layout.activity_settings);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
        SettingsActivity sa;
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            final ListPreference listPreference = findPreference("recipe_name");
            sa = (SettingsActivity) getActivity();
            String[] names = sa.recipes.stream().map(r -> r.name).toArray(String[]::new);
            listPreference.setEntries(names);
            listPreference.setEntryValues(names);
            listPreference.setDefaultValue(sa.recipes.get(0).name);
        }

        @Override
        public void onStop() {
            super.onStop();
            /* Unregister the preference change listener */
            getPreferenceScreen().getSharedPreferences()
                    .unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onStart() {
            super.onStart();
            /* Register the preference change listener */
            getPreferenceScreen().getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
             if(key.equals("recipe_name")) {
                AppWidgetManager mgr = AppWidgetManager.getInstance(getContext());
                Recipe recipe = sa.recipes.get(0);
                for(Recipe oneRecipe: sa.recipes) {
                    if(oneRecipe.name.equals(sharedPreferences.getString(key,""))) {
                        recipe = oneRecipe;
                        break;
                    }
                }
                int[] ids = mgr.getAppWidgetIds(new ComponentName(getContext().getPackageName(), IngredientListWidgetProvider.class.getName()));
                for(int id: ids) {
                    IngredientListWidgetProvider.updateRecipeWidget(getContext(), mgr, id, recipe.name, recipe.ingredients);
                }
             }
        }
    }

}