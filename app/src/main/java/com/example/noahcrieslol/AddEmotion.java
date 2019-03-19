package com.example.noahcrieslol;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;

import yuku.ambilwarna.AmbilWarnaDialog;

import static android.os.Build.ID;

public class AddEmotion extends AppCompatActivity {
    AutoCompleteTextView Emotion;
    EditText Date;
    EditText Time;
    EditText Reason;
    Button submit;
    DBHelper mydb;
    Integer DefaultColor;
    Button cancel;
    Button OpenColorPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emotion);

        //make array list of emotions in database
        mydb = new DBHelper(this);
        final ArrayList emotionArray = mydb.getAllDistinctEmotions();

        //autocomplete when filling in emotion
        Emotion = findViewById(R.id.Emotion);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, emotionArray);
        Emotion.setAdapter(adapter);

        //set color button and default color and open color picker
        OpenColorPicker = (Button) findViewById(R.id.OpenColorPicker);
        DefaultColor = ContextCompat.getColor(AddEmotion.this, R.color.colorPrimary);
        OpenColorPicker.setBackgroundColor(DefaultColor);
        OpenColorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });

        //auto date and time setter
        EditText autoDate = (EditText)findViewById(R.id.Date);
        EditText autoTime = (EditText)findViewById(R.id.Time);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String date = dateFormat.format(Calendar.getInstance().getTime());
        String time = timeFormat.format(Calendar.getInstance().getTime());

        autoDate.setText(date);
        autoTime.setText(time);

        //goes back to main activity screen
        cancel = (Button) findViewById(R.id.CancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddEmotion.this, DailyCalendar.class));
            }
        });

        //submits data to database and adds to main activity
        Date = (EditText) findViewById(R.id.Date);
        Time = (EditText) findViewById(R.id.Time);
        Reason = (EditText) findViewById(R.id.Reason);
        submit = (Button) findViewById(R.id.Submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emotion = Emotion.getText().toString();
                //finds if emotion already exists in database and if it does, automatically assigns color
                ColorDrawable buttonColor = (ColorDrawable) OpenColorPicker.getBackground();
                Integer color = buttonColor.getColor();
                for (int i= 1; i <= emotionArray.size(); i++) {
                    String databaseEmotion = mydb.getEmotion(i);
                    Integer databaseColor = mydb.getColor(i);
                    if (databaseEmotion.equals(emotion)) {
                        OpenColorPicker.setBackgroundColor(databaseColor);
                    }
                }
                String date = Date.getText().toString();
                String time = Time.getText().toString();
                String reason = Reason.getText().toString();
                if(Emotion.length() != 0 || Date.length() != 0 || Time.length() != 0 ) {
                    addData(emotion, color, date, time, reason);
                    startActivity(new Intent(AddEmotion.this, DailyCalendar.class));
                }
                else {
                    Toast.makeText(AddEmotion.this, "you must put something", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //method for adding text entered in to the database
    public void addData(String emotion, Integer color, String date, String time, String reason) {
        boolean insertData = mydb.addEmotion(emotion, color, date, time, reason);
        if(insertData == true) {
            Toast.makeText(AddEmotion.this, "entered data", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(AddEmotion.this, "something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    //color picking method
    public void openColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, DefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                DefaultColor = color;
                OpenColorPicker.setBackgroundColor(DefaultColor);
            }
        });
        colorPicker.show();
    }
}
