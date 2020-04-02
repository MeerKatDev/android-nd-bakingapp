package org.meerkatdev.bakingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.meerkatdev.bakingapp.R;
import org.meerkatdev.bakingapp.data.RecipeStep;
import org.meerkatdev.bakingapp.utils.ItemClickListener;

public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepAdapter.RecipeStepViewHolder> {

    final private ItemClickListener mOnClickListener;
    public RecipeStep[] elements;
    private int noSteps;

    public RecipeStepAdapter(int numberOfItems, ItemClickListener listener) {
        noSteps = numberOfItems;
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public RecipeStepAdapter.RecipeStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recipe_step_item, parent, false);
        return new RecipeStepAdapter.RecipeStepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeStepAdapter.RecipeStepViewHolder holder, int position) {
        holder.indexTextView.setText(String.valueOf(elements[position].id));
        holder.descriptionTextView.setText(elements[position].shortDescription);
    }

    @Override
    public int getItemCount() {
        return noSteps;
    }

    public void setData(RecipeStep[] _elements) {
        elements = _elements;
        noSteps = _elements.length;
        notifyDataSetChanged();
    }

    protected class RecipeStepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView indexTextView;
        private TextView descriptionTextView;

        RecipeStepViewHolder(View itemView) {
            super(itemView);
            indexTextView = itemView.findViewById(R.id.tv_recipe_step_index);
            descriptionTextView = itemView.findViewById(R.id.tv_recipe_step_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onItemClick(elements[getAdapterPosition()]);
        }
    }
}
