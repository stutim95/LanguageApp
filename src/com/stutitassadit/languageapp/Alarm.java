package com.stutitassadit.languageapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;
public class Alarm extends Activity {

	AlarmManager alarmManager;
	PendingIntent pendingIntent;
	TimePicker alarmTimePicker;
    private static Alarm inst;
    TextView alarmTextView;
    ToggleButton alarmToggle;
    Button button1;
    public static Alarm instance() {
        return inst;
    }
    
    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);
		alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmTextView = (TextView) findViewById(R.id.alarmText);
       // alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);
        button1 = (Button)findViewById(R.id.button1);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
	    }
	  public void onToggleClicked(View view) {
	       if (((ToggleButton) view).isChecked()) {
	          //  Log.d("Alarm", "Alarm On");
	            Calendar calendar = Calendar.getInstance();
	            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
	            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute()); 
	            Intent myIntent = new Intent(Alarm.this , AlarmReceiver.class);
	            pendingIntent = PendingIntent.getBroadcast(Alarm.this , 0 , myIntent, 0);
	            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
	        } 
	       else {
	            alarmManager.cancel(pendingIntent);
	            setAlarmText("");
	            Log.d("MyActivity", "Alarm Off");
	        }
	    }
       

	    public void setAlarmText(String alarmText) {
	        alarmTextView.setText(alarmText);
	    }
		
	
	
}
