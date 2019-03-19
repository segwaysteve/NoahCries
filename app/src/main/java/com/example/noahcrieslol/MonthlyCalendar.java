package com.example.noahcrieslol;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;

public class MonthlyCalendar extends AppCompatActivity {
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monthly_calendar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        calendarView = (CalendarView) findViewById(R.id.MonthlyCalendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange( CalendarView view, int year, int month, int dayOfMonth) {
                startActivity(new Intent(MonthlyCalendar.this, DailyCalendar.class));
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
            case R.id.Monthly:
                startActivity(new Intent(this, MonthlyCalendar.class));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
