package in.technicus.studypro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class dashboard extends AppCompatActivity {
Button myagenda,mycourse,mysetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        myagenda=(Button) findViewById(R.id.agendaa);
        mycourse=(Button) findViewById(R.id.course);
        mysetting=(Button) findViewById(R.id.setting_tab);

        myagenda.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent myIntent = new Intent(getBaseContext(), ViewAgenda.class);
                startActivity(myIntent);
            }
        });

        mycourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        mysetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
