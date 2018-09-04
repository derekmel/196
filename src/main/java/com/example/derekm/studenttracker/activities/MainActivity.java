package com.example.derekm.studenttracker.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.derekm.studenttracker.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DBOpenHelper helper = new DBOpenHelper(this);
        //SQLiteDatabase database = helper.getWritableDatabase();

    }

    public void termsButtonClickHandler(View view) {
        Intent termsIntent = new Intent(this, termsActivity.class);
        startActivity(termsIntent);
    }

    public void coursesButtonClickHandler(View view) {
        Intent coursesIntent = new Intent(this, coursesActivity.class);
        startActivity(coursesIntent);
    }

    public void assessmentsButtonClickHandler(View view) {
        Intent assessmentsIntent = new Intent(this, assesmentsActivity.class);
        startActivity(assessmentsIntent);
    }

}
