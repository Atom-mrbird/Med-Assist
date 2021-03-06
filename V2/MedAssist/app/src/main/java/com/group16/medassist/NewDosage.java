package com.group16.medassist;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class NewDosage extends AppCompatActivity {

    DatabaseHelper db;
    int prescription_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        prescription_id = getIntent().getIntExtra("prescription_id", 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dosage);
    }

    public void saveDosageToDB(View v) {
        String amountString = ((EditText) findViewById(R.id.dosage_amount)).getText().toString();
        String instructions = ((EditText)findViewById(R.id.dosage_instructions)).getText().toString().trim();
        if(amountString.isEmpty() || instructions.isEmpty()) return;
        db = new DatabaseHelper(this);
        db.createDosage(prescription_id, Integer.parseInt(amountString), instructions);
        db.close();
        Intent intent = new Intent(this, DosagesActivity.class);
        intent.putExtra("prescription_id", prescription_id);
        startActivity(intent);
        //finish();
    }

    public void cancelSaveDosage(View v) {
       /*  Intent intent = new Intent(this, DosagesActivity.class);
        intent.putExtra("prescription_id", prescription_id);
        startActivity(intent); */
        finish();
    }

}
