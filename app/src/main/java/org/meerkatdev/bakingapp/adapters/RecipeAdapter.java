package org.meerkatdev.bakingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.meerkatdev.bakingapp.R;
import org.meerkatdev.bakingapp.data.Recipe;
import org.meerkatdev.bakingapp.utils.ListItemClickListener;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> implements ListItemClickListener {

    final private ListItemClickListener mOnClickListener;
    public Recipe[] elements;
    private int noRecipes;


    public RecipeAdapter(int numberOfItems, ListItemClickListener listener) {
        noRecipes = numberOfItems;
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recipe_card, parent, false);
        return new RecipeAdapter.RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.RecipeViewHolder holder, int position) {
        Recipe recipe = elements[position];
        holder.recipeNameView.setText(recipe.name);
        if(!recipe.image.equals("")) {
            Picasso.get().load(recipe.image).into(holder.recipeThumb);
        } else {
            holder.recipeThumb.setImageResource(R.mipmap.image_not_found_foreground);
        }
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

        final ImageView recipeThumb;
        final TextView recipeNameView;

        RecipeViewHolder(View itemView) {
            super(itemView);
            recipeThumb = itemView.findViewById(R.id.iv_recipe_thumb);
            recipeNameView = itemView.findViewById(R.id.tv_recipe_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
