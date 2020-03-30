package org.meerkatdev.bakingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.meerkatdev.bakingapp.R;
import org.meerkatdev.bakingapp.data.Recipe;
import org.meerkatdev.bakingapp.utils.ListItemClickListener;

public class RecipeAdapter extends RecyclerView.Adapter implements ListItemClickListener {

    final private ListItemClickListener mOnClickListener;
    public Recipe[] elements;
    private int noRecipes;


    public RecipeAdapter(int numberOfItems, ListItemClickListener listener) {
        noRecipes = numberOfItems;
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recipe_card, parent, false);
        return new RecipeAdapter.RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Recipe recipe = elements[position];
        ((TextView)holder.itemView.findViewById(R.id.tv_recipe_name)).setText(recipe.name);
    }

    @Override
    public int getItemCount() {
        return noRecipes;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }

    public void setData(Recipe[] _elements) {
        elements = _elements;
        noRecipes = _elements.length;
        notifyDataSetChanged();
    }


    protected class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RecipeViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
