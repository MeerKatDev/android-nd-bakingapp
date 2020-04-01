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

import org.meerkatdev.bakingapp.BuildConfig;
import org.meerkatdev.bakingapp.MainActivity;
import org.meerkatdev.bakingapp.R;
import org.meerkatdev.bakingapp.utils.IntentTags;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientListWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context,
                                AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_ingredient_list);
        Intent intent = new Intent(context, IngredientsListActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        intent.putExtra(IntentTags.INGREDIENTS, IntentTags.INGREDIENTS);
        views.setTextViewText(R.id.tv_widget_recipe_name, sharedPreferences.getString(IntentTags.RECIPE, "404"));
        // onClick of the Cupcake image, launch the Activity/Dialog
        views.setOnClickPendingIntent(R.id.iv_widget_ingredient_list, pendingIntent);
        views.setOnClickPendingIntent(R.id.tv_widget_recipe_name, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

