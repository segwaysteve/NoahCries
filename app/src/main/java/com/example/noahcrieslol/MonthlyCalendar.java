package com.example.noahcrieslol;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MonthlyCalendar extends AppCompatActivity {
    CalendarView calendarView;
    TextView ModeEmotion;
    TextView ModeEmotionText;
    TextView ModeReason;
    TextView ModeReasonText;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monthly_calendar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        mydb = new DBHelper(this);

        ModeEmotion = (TextView) findViewById(R.id.ModeEmotion);
        ModeEmotionText = (TextView) findViewById(R.id.ModeEmotionText);
        String modeEmotion = mydb.getModeEmotion();
        if (modeEmotion == null) {
            ModeEmotionText.setText("You did not have a most common emotion this month");
        }
        else {
            ModeEmotion.setText(modeEmotion);
        }

        ModeReason = (TextView) findViewById(R.id.ModeReason);
        ModeReasonText = (TextView) findViewById(R.id.ModeReasonText);
        String modeReason = mydb.getModeReason();
        if (modeReason == null) {
            ModeReasonText.setText("You did not have a most common reason this month");
        }
        else {
            ModeReason.setText(modeReason);
        }

        calendarView = (CalendarView) findViewById(R.id.MonthlyCalendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange( CalendarView view, int year, int month, int dayOfMonth) {
                String DayOfMonth = dayOfMonth + "";
                if (String.valueOf(dayOfMonth).length() == 1) {
                    DayOfMonth = "0" + dayOfMonth;
                }
                String Month = (month + 1) + "";
                if (String.valueOf(month).length() == 1) {
                    Month = "0" + (month + 1);
                }
                String date = Month + "/" + DayOfMonth + "/" + year;
                Intent intent = new Intent(MonthlyCalendar.this, DailyCalendar.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MonthlyCalendar.this, AddEmotion.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.Daily:
                startActivity(new Intent(this, DailyCalendar.class));
                return true;
            case R.id.Weekly:
                startActivity(new Intent(this, WeeklyCalendar.class));
                return true;
            case R.id.Emotions:
                startActivity(new Intent(this, EmotionDisplay.class));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
