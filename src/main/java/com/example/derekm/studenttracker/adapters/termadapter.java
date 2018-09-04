package com.example.derekm.studenttracker.adapters;

import android.widget.ArrayAdapter;
import android.content.Context;

import com.example.derekm.studenttracker.R;
import com.example.derekm.studenttracker.models.Term;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class termadapter extends ArrayAdapter<Term> {

    public termadapter(Context context, ArrayList<Term> terms) {
        super(context, R.layout.activity_terms, terms);

    }

}
