package org.meerkatdev.bakingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import org.meerkatdev.bakingapp.data.Recipe;
import org.meerkatdev.bakingapp.utils.IntentTags;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    private ArrayList<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipes = getIntent().getExtras().getParcelableArrayList(IntentTags.RECIPES);
        setContentView(R.layout.settings_activity);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public static class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            final ListPreference listPreference = findPreference("recipe");
            SettingsActivity sa = (SettingsActivity) getActivity();
            listPreference.setEntries(sa.getRecipes().stream().map(r -> r.name).toArray(String[]::new));
            listPreference.setEntryValues(sa.getRecipes().stream().map(r -> String.valueOf(r.id)).toArray(String[]::new));
            listPreference.setDefaultValue(sa.getRecipes().get(0).name);
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
            Preference preference = findPreference(key);
            if (null != preference) {
                if (!(preference instanceof CheckBoxPreference)) {
                }
            }
        }
    }

}