package org.meerkatdev.bakingapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.meerkatdev.bakingapp.R;

public class StepsListFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager llm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_steps_list, container, false);
        recyclerView = rootView.findViewById(R.id.rv_recipe_steps);
        llm = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
//        movieAdapter = new MovieAdapter(0, this);
//        recyclerView.setAdapter(movieAdapter);

        return rootView;
    }


    private void setupGridView() {
    }

}
