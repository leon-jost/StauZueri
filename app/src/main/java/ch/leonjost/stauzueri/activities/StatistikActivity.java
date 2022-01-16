package ch.leonjost.stauzueri.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import ch.leonjost.stauzueri.R;

public class StatistikActivity extends AppCompatActivity {
    private final String longestRecordedDelayKey = "longestRecordedDelay";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik);

        // gui elements
        TextView longestRecordedDelayTextView = findViewById(R.id.longestRecordedDelay);

        // get data from sharedpreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        // format time
        int time = sharedPreferences.getInt(longestRecordedDelayKey, 0);
        Date date = new Date((long) (time * 1000L));
        String formattedDate = new SimpleDateFormat("HH:mm:ss").format(date);

        // set text on gui
        longestRecordedDelayTextView.setText(formattedDate);
    }

    public void switchToStauActivity(View view) {
        Intent intent = new Intent(this, StauActivity.class);
        startActivity(intent);
    }
}