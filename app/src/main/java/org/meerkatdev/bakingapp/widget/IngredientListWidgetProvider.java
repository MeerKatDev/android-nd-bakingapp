package org.meerkatdev.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.preference.PreferenceManager;

import org.meerkatdev.bakingapp.R;
import org.meerkatdev.bakingapp.data.Ingredient;
import org.meerkatdev.bakingapp.utils.IntentTags;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientListWidgetProvider extends AppWidgetProvider {
    private static final String TAG = IngredientListWidgetProvider.class.getSimpleName();



    public static void updateRecipeWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId,
                                          String recipeName, Ingredient[] ingredients) {
        Log.d(TAG, "updateRecipeWidget");
        Intent intent = new Intent(context, IngredientsListActivity.class);
        intent.putExtra(IntentTags.RECIPE_NAME, recipeName);
        intent.putExtra(IntentTags.INGREDIENTS, ingredients);
        RemoteViews views = setupWidget(context, intent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private static RemoteViews setupWidget(Context context, Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_ingredient_list);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setTextViewText(R.id.tv_widget_recipe_name, sharedPreferences.getString(IntentTags.RECIPE_NAME, ""));
        views.setOnClickPendingIntent(R.id.iv_widget_ingredient_list, pendingIntent);
        views.setOnClickPendingIntent(R.id.tv_widget_recipe_name, pendingIntent);
        return views;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int id : appWidgetIds) {
            Intent intent = new Intent(context, IngredientsListActivity.class);
            RemoteViews views = setupWidget(context, intent);
            appWidgetManager.updateAppWidget(id, views);
        }
    }

}


