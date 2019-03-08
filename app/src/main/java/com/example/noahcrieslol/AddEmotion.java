package com.example.noahcrieslol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class AddEmotion extends AppCompatActivity {
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

        /*String[] countries = getResources().getStringArray(R.array.countries_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, countries);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.AutoComplete);
        textView.setAdapter(adapter);*/
    }
}
