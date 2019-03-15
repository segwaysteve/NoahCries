package com.example.noahcrieslol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

public class AddEmotion extends AppCompatActivity {

    Button submit;
    EditText Color;
    EditText Emotion;
    EditText Date;
    EditText Time;
    EditText Reason;

    private static final String[] COUNTRIES = new String[] {
            "nice", "pie", "bad", "lie"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emotion);
        AutoCompleteTextView editText = findViewById(R.id.Emotion);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        editText.setAdapter(adapter);

        submit = (Button) findViewById(R.id.Submit);
    }
}
