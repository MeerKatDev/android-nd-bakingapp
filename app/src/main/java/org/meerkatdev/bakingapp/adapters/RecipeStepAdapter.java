package org.meerkatdev.bakingapp.adapters;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.meerkatdev.bakingapp.R;
import org.meerkatdev.bakingapp.data.RecipeStep;
import org.meerkatdev.bakingapp.utils.ListItemClickListener;
import org.w3c.dom.Text;

public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepAdapter.RecipeStepViewHolder> {

    final private ListItemClickListener mOnClickListener;
    public RecipeStep[] elements;
    private int noSteps;


    public RecipeStepAdapter(int numberOfItems, ListItemClickListener listener) {
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
        ((TextView)view.findViewById(R.id.tv_recipe_step_description)).setText(elements[position].description);
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
            Log.d("TAG", itemView.toString());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
