package in.technicus.studypro;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    finish();
                    Intent i = new Intent(getApplicationContext(), dashboard.class);
                    startActivity(i);
                }
            }
        };
        splashThread.start();
    }
}
