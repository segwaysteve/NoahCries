package com.example.noahcrieslol;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DailyCalendar extends AppCompatActivity {
    private static final String TAG = "DailyCalendar";
    DBHelper mydb;
    ListView DailyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_calendar);
        mydb = new DBHelper(this);

        DailyListView = (ListView) findViewById(R.id.DailyListView);
        final ArrayList array_list = mydb.getAllEmotionsAsId();
        ArrayList array_listtwo = mydb.getAllEmotions();
        Cursor cursor = mydb.getEverything();
        ArrayAdapter arrayAdapter= new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2, cursor, new String[] {cursor.getString(cursor.getColumnIndex(DBHelper.col_1))}, new int[] {android.R.id.text1});
        DailyListView.setAdapter(arrayAdapter);

        DailyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = (String) parent.getAdapter().getItem(position);

                // TODO Auto-generated method stub
                int id_To_Search = Integer.parseInt(value);

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                Intent intent = new Intent(getApplicationContext(), EditEmotion.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DailyCalendar.this, AddEmotion.class));
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
