package org.meerkatdev.bakingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import org.meerkatdev.bakingapp.R;
import org.meerkatdev.bakingapp.RecipeStepsListActivity;
import org.meerkatdev.bakingapp.data.RecipeStep;

import java.util.prefs.PreferenceChangeEvent;

public class StepContentFragment extends Fragment {

    private final String TAG = this.getClass().getSimpleName();
    private final String PARCEL_TAG = "recipe-step";
    private SimpleExoPlayer sep;
    private PlayerView playerView;
    private RecipeStep recipeStep;
    private TextView descriptionView;
    private DataSource.Factory dataSourceFactory;
    DisplayMetrics displayMetrics = new DisplayMetrics();

    @Nullable // TODO should it be nullable?
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("LIFECYCLE", "Creating step content");
        final View rootView = inflater.inflate(R.layout.fragment_step_content, container, false);
        // TODO Adapt this to use with RecipeContentActivity
        RecipeStepsListActivity fa = (RecipeStepsListActivity) rootView.getContext();
        Bundle bundle = fa.getIntent().getExtras();
        initView(rootView);
        if (bundle != null)
            recoverFromBundle(fa, bundle);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecipeStepsListActivity fa = (RecipeStepsListActivity) view.getContext();
        if(savedInstanceState != null)
            recoverFromBundle(fa, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("LIFECYCLE", "fragment onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putParcelable(PARCEL_TAG, this.recipeStep);
    }

    private void recoverFromBundle(RecipeStepsListActivity fa, Bundle b) {
        Log.d("LIFECYCLE", "recoverFromBundle");
        this.recipeStep = (b.containsKey(PARCEL_TAG)) ? (RecipeStep) b.get(PARCEL_TAG) : fa.getFirstRecipeStep();
        setView(fa, this.recipeStep);
    }

    @Override
    public void onResume() {
        super.onResume();
        changeVideoHeight(getResources().getConfiguration().orientation);
    }

    private void changeVideoHeight(int ori) {
        Log.d(TAG, "changeVideoHeight");
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // TODO maybe y?
        int heightDpi = Math.round(displayMetrics.heightPixels / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        int newHeight = (!getResources().getBoolean(R.bool.is_tablet) && ori == Configuration.ORIENTATION_LANDSCAPE) ? heightDpi : heightDpi / 2;
        playerView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, newHeight));
    }

    public void initView(View rootView) {
        sep = new SimpleExoPlayer.Builder(rootView.getContext()).build();
        sep.setPlayWhenReady(true);
        dataSourceFactory = new DefaultDataSourceFactory(rootView.getContext(), Util.getUserAgent(rootView.getContext(), "BakingApp"));
        playerView = rootView.findViewById(R.id.pv_instruction_video);
        playerView.setPlayer(sep);
        descriptionView = rootView.findViewById(R.id.tv_instruction_text);
    }

    public void setView(FragmentActivity fa, RecipeStep recipeStep) {
        Log.d(TAG, "setupView");
        this.recipeStep = recipeStep;
        if(!recipeStep.videoURL.equals("")) {
            playerView.setVisibility(View.VISIBLE);
            setVideoSource(dataSourceFactory, Uri.parse(recipeStep.videoURL));
            setArtwork(fa, recipeStep.thumbnailURL);
        } else {
            playerView.setVisibility(View.GONE);
        }
        descriptionView.setText(recipeStep.description);

    }

    private void setVideoSource(DataSource.Factory dataSourceFactory, Uri videoUrl) {
        Log.d(TAG, "setVideoSource");
        // This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(videoUrl);
        // Prepare the player with the source.
        Log.d(TAG, "DL from: " + videoUrl.toString());
        sep.prepare(videoSource);
        playerView.setShowBuffering(PlayerView.SHOW_BUFFERING_WHEN_PLAYING);
    }


    private void setArtwork(Context ctx, String thumbnailUrl) {
        if(!thumbnailUrl.equals("")) {
            ImageView thumbnailImageView = new ImageView(ctx);
            Picasso.get().load(thumbnailUrl).into(thumbnailImageView);
            playerView.setDefaultArtwork(thumbnailImageView.getDrawable());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("LIFECYCLE", "Destroying step content");
        sep.release();
    }
}
