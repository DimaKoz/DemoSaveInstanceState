package ru.otus.demo.saveactivityinstance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class ActivityExplicitIntent extends AppCompatActivity {

    static Activity activity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (activity == null) {
            activity =this;
        }

        setContentView(R.layout.activity_explicit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = new Intent();
        intent.putExtra("answer", "42");
        setResult(RESULT_OK, intent);
    }

}
