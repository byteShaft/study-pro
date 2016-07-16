package in.technicus.studypro;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import in.technicus.studypro.Services.DbConnection;
import in.technicus.studypro.Services.ListPojo;

public class AddAgenda extends AppCompatActivity implements  View.OnClickListener {
    Button btnDatePicker, btnTimePicker,addAgenda;
    EditText txtDate, txtTime,agendaTitle;
    CheckBox addRem;
    TextView lblRem;
    DbConnection dbcon;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String atitle,aImportance,aRem,aDate,aTime;
    int alarmYear,alarmMonth,alarmDay,alarmHours,alarmMinutes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_agenda);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbcon=new DbConnection(this);

        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnTimePicker = (Button) findViewById(R.id.btn_time);
        txtDate = (EditText) findViewById(R.id.in_date);
        txtTime = (EditText) findViewById(R.id.in_time);
        lblRem=(TextView) findViewById(R.id.remtext);
        agendaTitle=(EditText) findViewById(R.id.agendaTitle);
        aRem="no";
        //setting field of reminder hidden
        btnDatePicker.setVisibility(View.GONE);
        btnTimePicker.setVisibility(View.GONE);
        txtDate.setVisibility(View.GONE);
        txtTime.setVisibility(View.GONE);
        lblRem.setVisibility(View.GONE);

        addRem=(CheckBox) findViewById(R.id.remCheck);
        addRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    aRem = "yes";
                    btnDatePicker.setVisibility(View.VISIBLE);
                    btnTimePicker.setVisibility(View.VISIBLE);
                    txtDate.setVisibility(View.VISIBLE);
                    txtTime.setVisibility(View.VISIBLE);
                    lblRem.setVisibility(View.VISIBLE);
                } else {
                    aRem = "no";
                    btnDatePicker.setVisibility(View.GONE);
                    btnTimePicker.setVisibility(View.GONE);
                    txtDate.setVisibility(View.GONE);
                    txtTime.setVisibility(View.GONE);
                    lblRem.setVisibility(View.GONE);
                }
            }
        });

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        addAgenda=(Button) findViewById(R.id.addAgenda);
        addAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //assign into variables
                String title=agendaTitle.getText().toString();
                final RadioGroup rd=(RadioGroup) findViewById(R.id.impRadio);
                RadioButton rdbtn=null;
                int rid = rd.getCheckedRadioButtonId();
                rdbtn = (RadioButton) findViewById(rid);
                String Imp=rdbtn.getText().toString();
                if(aRem=="yes")
                {
                    //insert into db

                    Calendar myAlarmDate = Calendar.getInstance();
//                    myAlarmDate.setTimeInMillis(System.currentTimeMillis());

//                    myAlarmDate.set(alarmYear, alarmMonth, alarmDay, alarmHours, alarmMinutes);
                    myAlarmDate.set(Calendar.MONTH,alarmMonth);
                    myAlarmDate.set(Calendar.YEAR, alarmYear);
                    myAlarmDate.set(Calendar.DAY_OF_MONTH, alarmDay);
                    myAlarmDate.set(Calendar.HOUR_OF_DAY, alarmHours);
                    myAlarmDate.set(Calendar.MINUTE,alarmMinutes);
                    myAlarmDate.set(Calendar.SECOND,10);
                    Long nextAlarm= myAlarmDate.getTimeInMillis();


                    AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);

                    Intent _myIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
                    _myIntent.putExtra("MyMessage", "You have setted a alarm for "+ title +" for time:"+alarmHours+":"+alarmMinutes);

                    PendingIntent _myPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 123, _myIntent, PendingIntent.FLAG_UPDATE_CURRENT |  Intent.FILL_IN_DATA);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, myAlarmDate.getTimeInMillis(), _myPendingIntent);

                    Log.e("Data", "alarmDay"+alarmMonth +"-" +alarmHours + "-" + alarmMinutes);

                    dbcon.insertAgenda(new ListPojo(title, Imp));
                    Toast.makeText(getApplicationContext(), "You have setted a alarm for "+title+" for time:"+alarmHours+":"+alarmMinutes, Toast.LENGTH_SHORT).show();
                    Intent nq=new Intent(getApplicationContext(),ViewAgenda.class);
                    startActivity(nq);


                }else
                {
                    //insert into db
                    dbcon.insertAgenda(new ListPojo(title, Imp));
                    Toast.makeText(getApplicationContext(), "Your assignment has been added", Toast.LENGTH_SHORT).show();
                    Intent nq=new Intent(getApplicationContext(),ViewAgenda.class);
                    startActivity(nq);
                    Log.e("Data",title+Imp);
                }
                Log.e("Data",title+Imp+aRem);

            }
        });

    }

    public void onClick(View v) {
        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            alarmYear=year;
                            alarmDay=dayOfMonth;
                            alarmMonth=monthOfYear;

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
                      // Launch Time Picker Dialog

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            alarmHours=hourOfDay;
                            alarmMinutes=minute;
                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

    }


}
