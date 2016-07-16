package in.technicus.studypro;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class Helpers {

    public static SharedPreferences getPreferenceManager() {
        return PreferenceManager.getDefaultSharedPreferences(AppGlobals.getContext());
    }

    public static void saveServiceStateEnabled(boolean enable) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putBoolean("enabled", enable).apply();
    }

    public static boolean isServiceSettingEnabled() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                AppGlobals.getContext());
        return sharedPreferences.getBoolean("enabled", true);
    }
}
