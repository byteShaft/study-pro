package in.technicus.studypro;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {


    private Switch mToggleSwitch;
    private Button mAboutButton;
    private Button mFeedbackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");
        mAboutButton = (Button) findViewById(R.id.button_about);
        mFeedbackButton = (Button) findViewById(R.id.button_feedback);
        mAboutButton.setOnClickListener(this);
        mFeedbackButton.setOnClickListener(this);
        mToggleSwitch = (Switch) findViewById(R.id.aSwitch);
        mToggleSwitch.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            enableBroadcastReceiver();
            mToggleSwitch.setTextColor(Color.BLACK);
            mToggleSwitch.setText("Alarm Enabled");
        } else {
            disableBroadcastReceiver();
            mToggleSwitch.setTextColor(Color.RED);
            mToggleSwitch.setText("Alarm Disabled");
        }

        Helpers.saveServiceStateEnabled(isChecked);
    }

    public void enableBroadcastReceiver() {
        ComponentName receiver = new ComponentName(this, AlarmReceiver.class);
        PackageManager pm = this.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        Toast.makeText(this, "Enabled ", Toast.LENGTH_SHORT).show();
    }

    public void disableBroadcastReceiver(){
        ComponentName receiver = new ComponentName(this, AlarmReceiver.class);
        PackageManager pm = this.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
        Toast.makeText(this, "Disabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mToggleSwitch.setChecked(Helpers.isServiceSettingEnabled());
        if (Helpers.isServiceSettingEnabled()) {
            mToggleSwitch.setText("Alarm Enabled");
            mToggleSwitch.setTextColor(Color.BLACK);
        } else {

            mToggleSwitch.setText("Alarm Disabled");
            mToggleSwitch.setTextColor(Color.RED);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_feedback:
                System.out.println("feedback button");
                startActivity(new Intent(this, FeedbackActivity.class));
                break;
            case R.id.button_about:
                System.out.println("About button");
                startActivity(new Intent(this, AboutActivity.class));
                break;
        }
    }
}
