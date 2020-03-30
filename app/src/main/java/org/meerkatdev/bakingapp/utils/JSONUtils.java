package org.meerkatdev.bakingapp.utils;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JSONUtils {

    public static String getJsonFromRaw(Context ctx, int resid) {

        try {
            InputStream is = ctx.getResources().openRawResource(resid);
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
}
