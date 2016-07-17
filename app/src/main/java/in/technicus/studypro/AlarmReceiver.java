package in.technicus.studypro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("TAG", intent.getAction());
        if (intent.getAction().equals("com.byteshaft.setnotification")) {
            Helpers.showNotification();

        } else if (intent.getAction().equals("com.byteshaft.setagenda")) {
            String msg = intent.getStringExtra("MyMessage");
            MediaPlayer mPlayer2;
            mPlayer2 = MediaPlayer.create(context, R.raw.alerttone);
            mPlayer2.start();
            Toast.makeText(context, msg.toString(), Toast.LENGTH_LONG).show();
        }
    }
}