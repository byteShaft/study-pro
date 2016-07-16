package in.technicus.studypro;

import android.app.Application;
import android.content.Context;

/**
 * Created by shahid on 16/07/2016.
 */

public class AppGlobals extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }
}
