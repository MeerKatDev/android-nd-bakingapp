package org.meerkatdev.bakingapp.utils;

import android.os.Bundle;
import android.util.Log;

import org.meerkatdev.bakingapp.data.Ingredient;

import java.util.Iterator;
import java.util.Set;

public class Utils {
    public static void logBundle(Bundle b) {
        if (b != null) {
            StringBuilder str = new StringBuilder();
            Set<String> keys = b.keySet();
            Iterator<String> it = keys.iterator();
            while (it.hasNext()) {
                String key = it.next();
                str.append(key);
                str.append(":");
                if(b.get(key) instanceof Ingredient[])
                    Log.d("UTILS", ((Ingredient[])b.get(key))[0].ingredient);
                str.append(b.get(key));
                str.append("\n\r");
            }
            Log.d("BUNDLE_LOGGING", str.toString());
        }
    }
}
