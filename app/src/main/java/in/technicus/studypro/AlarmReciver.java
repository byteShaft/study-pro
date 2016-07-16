package in.technicus.studypro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class AlarmReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Helpers.showNotification();
        String msg = intent.getStringExtra("MyMessage");
        MediaPlayer mPlayer2;
        mPlayer2 = MediaPlayer.create(context, R.raw.alerttone);
        mPlayer2.start();
        Toast.makeText(context, msg.toString(), Toast.LENGTH_LONG).show();
    }
}