package com.example.noahcrieslol;

import android.app.RemoteInput;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EmotionDisplay extends AppCompatActivity {
    DBHelper mydb;
    ListView DistinctEmotionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emotion_display);

        mydb = new DBHelper(this);
        DistinctEmotionList = (ListView) findViewById(R.id.DistinctEmotionList);
        final ArrayList array_list = mydb.getAllDistinctEmotions();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array_list);
        DistinctEmotionList.setAdapter(arrayAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmotionDisplay.this, AddEmotion.class));
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
