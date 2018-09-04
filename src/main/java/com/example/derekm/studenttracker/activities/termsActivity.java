package com.example.derekm.studenttracker.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.example.derekm.studenttracker.R;
import com.example.derekm.studenttracker.database.dataprovider;
import com.example.derekm.studenttracker.models.Term;

public class termsActivity extends AppCompatActivity {
    private dataprovider dp;
    ListView tl;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        //dp = new dataprovider(this);

        //dp.open();

        List<Term> terms = new ArrayList<>();
        Term Term1 = new Term(0l,"Term 1", "2018-08-29", "2018-09-29");
        terms.add(Term1);

        Term Term2 = new Term(1l,"Term 2", "2018-09-29", "2018-10-29");
        terms.add(Term2);

        tl = (ListView) findViewById(R.id.termslist);
        ArrayAdapter<Term> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, terms);
        tl.setAdapter(adapter);

    }

    public void newTermAddButtonHandler(View view) {
        Intent newTermIntent = new Intent(this, newTermActivity.class);
        startActivity(newTermIntent);
    }



}
