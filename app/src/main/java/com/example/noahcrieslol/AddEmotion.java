package com.example.noahcrieslol;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;

import yuku.ambilwarna.AmbilWarnaDialog;

import static android.os.Build.ID;

public class AddEmotion extends AppCompatActivity {
    Button submit;
    EditText Color;
    EditText Emotion;
    EditText Date;
    EditText Time;
    EditText Reason;
    DBHelper mydb;
    int DefaultColor;
    Button OpenColorPicker;

    /*private static final String[] COUNTRIES = new String[] {
            "nice", "pie", "bad", "lie"
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emotion);

        //make array list of emotions in database
        mydb = new DBHelper(this);
        ArrayList array_list = mydb.getAllEmotions();

        //autocomplete when filling in emotion
        AutoCompleteTextView editText = findViewById(R.id.Emotion);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, array_list);
        editText.setAdapter(adapter);

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
        EditText autoD8 = (EditText)findViewById(R.id.Date);
        EditText autoTime = (EditText)findViewById(R.id.Time);

        SimpleDateFormat dateF = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault());
        SimpleDateFormat timeF = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String date = dateF.format(Calendar.getInstance().getTime());
        String time = timeF.format(Calendar.getInstance().getTime());

        autoD8.setText(date);
        autoTime.setText(time);

        //add emotion to database and dailyview
        submit = (Button) findViewById(R.id.Submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
