package in.technicus.studypro;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;


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

    public static void showNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(AppGlobals.getContext())
                        .setDefaults(Notification.DEFAULT_SOUND)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Study Pro")
                        .setContentText("The Time Has Come");
        NotificationManager mNotificationManager = (NotificationManager)
                AppGlobals.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }
}
