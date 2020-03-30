package org.meerkatdev.bakingapp.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.meerkatdev.bakingapp.R;

public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepAdapter.RecipeStepViewHolder> {

//    final private ListItemClickListener mOnClickListener;
//    public List<Movie> availableMovies;
    private int noSteps;

    @NonNull
    @Override
    public RecipeStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeStepViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return noSteps;
    }


    protected class RecipeStepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView movieView;

        RecipeStepViewHolder(View itemView) {
            super(itemView);
            movieView = itemView.findViewById(R.id.tv_recipe_step);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
//            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
