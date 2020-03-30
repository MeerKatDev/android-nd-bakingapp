package org.meerkatdev.bakingapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import org.meerkatdev.bakingapp.data.RecipeStep;

public class RecipeContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_step_content);

        RecipeStep recipeStep = (RecipeStep) getIntent().getExtras().get("recipe_step");

        PlayerView playerView = findViewById(R.id.pv_instruction_video);
        SimpleExoPlayer sep = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(sep);

        // recipeStep.videoURL
        // recipeStep.thumbnailURL
        // init player
        ((TextView)findViewById(R.id.tv_instruction_text)).setText(recipeStep.description);

    }
}
