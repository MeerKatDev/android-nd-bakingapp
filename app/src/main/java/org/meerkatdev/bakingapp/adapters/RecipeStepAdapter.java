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
    public RecipeStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recipe_step_item, parent, false);
        return new RecipeStepAdapter.RecipeStepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeStepViewHolder holder, int position) {
        View view = holder.itemView.getRootView();
        ((TextView)view.findViewById(R.id.tv_recipe_step_index)).setText(String.valueOf(elements[position].id));
        ((TextView)view.findViewById(R.id.tv_recipe_step_description)).setText(elements[position].shortDescription);
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

        RecipeStepViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onItemClick(elements[getAdapterPosition()]);
        }
    }
}
