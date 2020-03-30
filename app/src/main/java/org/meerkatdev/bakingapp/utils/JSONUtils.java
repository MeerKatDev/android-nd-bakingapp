package org.meerkatdev.bakingapp.utils;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.meerkatdev.bakingapp.data.Recipe;

import java.io.IOException;
import java.io.InputStream;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class JSONUtils {

    public static String getJsonFromRaw(Context ctx, int resId) {
        try {
            InputStream is = ctx.getResources().openRawResource(resId);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Recipe[] convertJsonToRecipeList(String json) {
        Type listType = new TypeToken<Recipe[]>() {}.getType();
        return new Gson().fromJson(json, listType);
    }
}
