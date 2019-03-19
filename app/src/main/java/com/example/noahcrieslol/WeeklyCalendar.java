package com.example.noahcrieslol;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class WeeklyCalendar extends AppCompatActivity {
    ListView WeeklyListView;
    TextView ModeEmotion;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly_calendar);
        mydb = new DBHelper(this);

        WeeklyListView = (ListView) findViewById(R.id.WeeklyListView);
        ArrayList<String> array_list = new ArrayList<String>();
        array_list.add("Monday");
        array_list.add("Tuesday");
        array_list.add("Wednesday");
        array_list.add("Thursday");
        array_list.add("Friday");
        array_list.add("Saturday");
        array_list.add("Sunday");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, array_list);
        WeeklyListView.setAdapter(arrayAdapter);

        ModeEmotion = (TextView) findViewById(R.id.ModeEmotion);
        String modeEmotion = mydb.getModeEmotion();
        ModeEmotion.setText(modeEmotion);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WeeklyCalendar.this, AddEmotion.class));
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
            case R.id.Emotions:
                startActivity(new Intent(this, EmotionDisplay.class));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
