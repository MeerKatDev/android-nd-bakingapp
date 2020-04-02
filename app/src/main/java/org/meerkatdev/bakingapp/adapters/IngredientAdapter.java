package org.meerkatdev.bakingapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.meerkatdev.bakingapp.R;
import org.meerkatdev.bakingapp.data.Ingredient;

public class IngredientAdapter extends ArrayAdapter<Ingredient> {

    public IngredientAdapter(@NonNull Context context, @NonNull Ingredient[] ingredients) {
        super(context, 0, ingredients);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Ingredient ingredient = getItem(position);
        Log.d("TAG", ingredient.ingredient);
        View row;
        row = (null == convertView) ? LayoutInflater.from(getContext()).inflate(R.layout.ingredient_adapter, parent, false) : convertView;
        ((TextView) row.findViewById(R.id.tv_ingredient_ingredient)).setText(ingredient.ingredient);
        ((TextView) row.findViewById(R.id.tv_ingredient_measure_and_quantity)).setText(ingredient.quantity + " " + ingredient.measure);
        return row;
    }
}
