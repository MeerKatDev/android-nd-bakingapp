package org.meerkatdev.bakingapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.meerkatdev.bakingapp.R;
import org.meerkatdev.bakingapp.adapters.IngredientAdapter;
import org.meerkatdev.bakingapp.adapters.RecipeStepAdapter;
import org.meerkatdev.bakingapp.data.Ingredient;
import org.meerkatdev.bakingapp.data.Recipe;
import org.meerkatdev.bakingapp.utils.IntentTags;
import org.meerkatdev.bakingapp.utils.ItemClickListener;
import org.meerkatdev.bakingapp.utils.ListItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;

public class StepsListFragment extends Fragment {

    private ItemClickListener mCallback;
    private RecipeStepAdapter viewAdapter;
    public Recipe recipe;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mCallback = (ItemClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ListItemClickListener!");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_steps_list, container, false);
        final FragmentActivity fa = getActivity();
        setupRecyclerView(fa, rootView);
        populateAdapterFromIntent(fa, rootView);
        return rootView;
    }

    private void populateAdapterFromIntent(FragmentActivity fa, View rootView) {
        Bundle b = fa.getIntent().getExtras();
        if (b != null && b.containsKey(IntentTags.RECIPE)) {
            this.recipe = b.getParcelable(IntentTags.RECIPE);
            if (this.recipe != null) {
                viewAdapter.setData(this.recipe.steps);
                ListView lv = rootView.findViewById(R.id.ingredients_list);
                lv.setAdapter(new IngredientAdapter(getContext(), this.recipe.ingredients));
            }
        }
    }

    private void setupRecyclerView(FragmentActivity c, View rootView) {
        final RecyclerView recyclerView = rootView.findViewById(R.id.rv_recipe_steps);
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        viewAdapter = new RecipeStepAdapter(0, mCallback);
        recyclerView.setAdapter(viewAdapter);
    }

}
