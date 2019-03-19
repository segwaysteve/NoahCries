package com.example.noahcrieslol;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditEmotion extends AppCompatActivity {
    AutoCompleteTextView EditEmotion;
    EditText EditDate;
    Button EditOpenColorPicker;
    EditText EditTime;
    EditText EditReason;
    DBHelper mydb;
    Integer DefaultColor;
    Button EditUpdate;
    Button EditDelete;
    Button EditCancel;
    int id_To_Update = 0;
    private int selectedID;
    private String selectedEmotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_emotion);
        EditEmotion = (AutoCompleteTextView) findViewById(R.id.EditEmotion);
        EditOpenColorPicker = (Button) findViewById(R.id.EditOpenColorPicker);
        EditDate = (EditText) findViewById(R.id.EditDate);
        EditTime = (EditText) findViewById(R.id.EditTime);
        EditReason = (EditText) findViewById(R.id.EditReason);

        mydb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int id = extras.getInt("id");

            if(id > 0){
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(id);
                id_To_Update = id;
                rs.moveToFirst();

                String emotion = rs.getString(rs.getColumnIndex(DBHelper.col_2));
                Integer color = rs.getInt(rs.getColumnIndex(DBHelper.col_3));
                String date = rs.getString(rs.getColumnIndex(DBHelper.col_4));
                String time = rs.getString(rs.getColumnIndex(DBHelper.col_5));
                String reason = rs.getString(rs.getColumnIndex(DBHelper.col_6));

                if (!rs.isClosed()) {
                    rs.close();
                }

                //Button b = (Button)findViewById(R.id.button1);
                //b.setVisibility(View.INVISIBLE);

                EditEmotion.setText((CharSequence) emotion);

                EditOpenColorPicker.setBackgroundColor(color);

                EditDate.setText((CharSequence) date);

                EditTime.setText((CharSequence) time);

                EditReason.setText((CharSequence) reason);
            }
        }

        EditUpdate = (Button) findViewById(R.id.EditUpdate);
        EditUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emotion = EditEmotion.getText().toString();
                ColorDrawable buttonColor = (ColorDrawable) EditOpenColorPicker.getBackground();
                int color = buttonColor.getColor();
                String date = EditDate.getText().toString();
                String time = EditTime.getText().toString();
                String reason = EditReason.getText().toString();
                mydb.updateEmotion(id_To_Update, emotion, color, date, time, reason);
                startActivity(new Intent(EditEmotion.this, DailyCalendar.class));
            }
        });

        EditDelete = (Button) findViewById(R.id.EditDelete);
        EditDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditEmotion.this);
                builder.setMessage(R.string.deleteEmotion)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteEmotion(id_To_Update);
                                Toast.makeText(EditEmotion.this, "Deleted Successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(EditEmotion.this,DailyCalendar.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });

                AlertDialog d = builder.create();
                d.show();
            }
        });

        EditCancel = (Button) findViewById(R.id.EditCancel);
        EditCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditEmotion.this, DailyCalendar.class));
            }
        });
    }
}
