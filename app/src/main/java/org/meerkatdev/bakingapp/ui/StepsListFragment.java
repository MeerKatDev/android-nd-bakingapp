package org.meerkatdev.bakingapp.ui;

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
import org.meerkatdev.bakingapp.utils.ListItemClickListener;

public class StepsListFragment extends Fragment {

    private ListItemClickListener mCallback;
    //private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    private RecipeStepAdapter viewAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mCallback = (ListItemClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ListItemClickListener!");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("NONO", getActivity().getClass().getSimpleName() );
        final View rootView = inflater.inflate(R.layout.fragment_steps_list, container, false);
        final FragmentActivity c = getActivity();
        //recyclerView = rootView.findViewById(R.id.rv_recipe_steps);
        //llm = new LinearLayoutManager(ctx);
        final RecyclerView recyclerView = rootView.findViewById(R.id.rv_recipe_steps);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setAdapter(viewAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);
        Recipe recipe = (Recipe) c.getIntent().getExtras().get("recipe");

        new Thread(() -> {
            viewAdapter = new RecipeStepAdapter(0, mCallback);
            c.runOnUiThread(() -> {
                recyclerView.setAdapter(viewAdapter);
                viewAdapter.setData(recipe.steps);
            });
        }).start();
        //setupRecyclerView(rootView.getContext(), rootView);
        //useIntent(getActivity().getIntent());
        return rootView;
    }

//    private void useIntent(Intent intent) {
//        if(intent != null) {
//            Log.d("howmany", String.valueOf(recipe.steps.length));
//            viewAdapter.setData(recipe.steps);
//        }
//    }
//    private void setupRecyclerView(Context ctx, View rootView) {
//        recyclerView = rootView.findViewById(R.id.rv_recipe_steps);
//        llm = new LinearLayoutManager(ctx);
//        recyclerView.setLayoutManager(llm);
//        recyclerView.setHasFixedSize(true);
//        viewAdapter = new RecipeStepAdapter(0, mCallback);
//        recyclerView.setAdapter(viewAdapter);
//    }

}
