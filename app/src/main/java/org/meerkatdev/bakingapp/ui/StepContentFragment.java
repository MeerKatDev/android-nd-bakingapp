package org.meerkatdev.bakingapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.meerkatdev.bakingapp.R;

public class StepContentFragment extends Fragment {

    @Nullable // TODO should it be nullable?
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_step_content, container, false);

        // Return the root view
        return rootView;
    }
}
