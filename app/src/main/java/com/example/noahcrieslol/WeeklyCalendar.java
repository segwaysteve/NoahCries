package com.example.noahcrieslol;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WeeklyCalendar extends AppCompatActivity {
    ListView WeeklyListView;
    TextView ModeEmotion;
    TextView ModeReason;
    TextView ModeEmotionText;
    TextView ModeReasonText;
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

        WeeklyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Calendar c = Calendar.getInstance();
                int dayOfWeekToday = c.get(Calendar.DAY_OF_WEEK) - 2;
                int daysOfWeekBefore = position - dayOfWeekToday;
                if (daysOfWeekBefore < 0) {
                    String date = getCalculatedDate(daysOfWeekBefore);
                    Intent intent = new Intent(WeeklyCalendar.this, DailyCalendar.class);
                    intent.putExtra("date", date);
                    startActivity(intent);
                }
                else if (daysOfWeekBefore == 0) {
                    startActivity(new Intent(WeeklyCalendar.this, DailyCalendar.class));
                }
                else {
                    Toast.makeText(getApplicationContext(), "That day hasn't happened yet", Toast.LENGTH_LONG);
                }
            }
        });

        ModeEmotion = (TextView) findViewById(R.id.ModeEmotion);
        ModeEmotionText = (TextView) findViewById(R.id.ModeEmotionText);
        String modeEmotion = mydb.getModeEmotion();
        if (modeEmotion == null) {
            ModeEmotionText.setText("You did not have a most common emotion this week");
        }
        else {
            ModeEmotion.setText(modeEmotion);
        }

        ModeReason = (TextView) findViewById(R.id.ModeReason);
        ModeReasonText = (TextView) findViewById(R.id.ModeReasonText);
        String modeReason = mydb.getModeReason();
        if (modeReason == null) {
            ModeReasonText.setText("You did not have a most common reason this week");
        }
        else {
            ModeReason.setText(modeReason);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WeeklyCalendar.this, AddEmotion.class));
            }
        });
    }

    public static String getCalculatedDate(int days) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy");
        cal.add(Calendar.DAY_OF_YEAR, days);
        return s.format(new Date(cal.getTimeInMillis()));
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
