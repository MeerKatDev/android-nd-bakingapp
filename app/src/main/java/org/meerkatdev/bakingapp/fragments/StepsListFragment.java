package org.meerkatdev.bakingapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.meerkatdev.bakingapp.R;
import org.meerkatdev.bakingapp.adapters.RecipeStepAdapter;
import org.meerkatdev.bakingapp.data.Recipe;
import org.meerkatdev.bakingapp.utils.ItemClickListener;

import java.util.Objects;

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
        populateAdapterFromIntent(fa);

        return rootView;
    }

    private void populateAdapterFromIntent(FragmentActivity c) {
        Intent intent = c.getIntent();
        Bundle b = intent.getExtras();
        if (b != null && b.containsKey("recipe")) {
            try {
                this.recipe = b.getParcelable("recipe");
                if (this.recipe != null) {
                    viewAdapter.setData(this.recipe.steps);
                }
            } catch (NullPointerException e) {
                Log.d("StepsListFragment", Objects.requireNonNull(e.getMessage()));
                e.printStackTrace();

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
