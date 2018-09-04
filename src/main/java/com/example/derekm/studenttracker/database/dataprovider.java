/*
package com.example.derekm.studenttracker.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class dataprovider extends ContentProvider{

    //Path strings
    private static final String AUTHORITY = "com.example.derekm.studenttracker.database.dataprovider";
    private static final String TERMS_PATH = "terms";
    private static final String COURSES_PATH = "courses";
    private static final String COURSE_NOTES_PATH = "courseNotes";
    private static final String ASSESSMENTS_PATH = "assessments";
    private static final String ASSESSMENT_NOTES_PATH = "assessmentNotes";

    //URIs
    public static final Uri TERMS_URI = Uri.parse("content://" + AUTHORITY + "/" + TERMS_PATH);
    public static final Uri COURSES_URI = Uri.parse("content://" + AUTHORITY + "/" + COURSES_PATH);
    public static final Uri COURSE_NOTES_URI = Uri.parse("content://" + AUTHORITY + "/" + COURSE_NOTES_PATH);
    public static final Uri ASSESSMENTS_URI = Uri.parse("content://" + AUTHORITY + "/" + ASSESSMENTS_PATH);
    public static final Uri ASSESSMENT_NOTES_URI = Uri.parse("content://" + AUTHORITY + "/" + ASSESSMENT_NOTES_PATH);


    //Constants
    private static final int TERMS = 1;
    private static final int TERMS_ID = 2;
    private static final int COURSES = 3;
    private static final int COURSES_ID = 4;
    private static final int COURSE_NOTES = 5;
    private static final int COURSE_NOTES_ID = 6;
    private static final int ASSESSMENTS = 7;
    private static final int ASSESSMENTS_ID = 8;
    private static final int ASSESSMENT_NOTES = 9;
    private static final int ASSESSMENT_NOTES_ID = 10;


    private static final UriMatcher URI_MATCHER = new UriMatcher(android.content.UriMatcher.NO_MATCH);
    static {
        URI_MATCHER.addURI(AUTHORITY, TERMS_PATH, TERMS);
        URI_MATCHER.addURI(AUTHORITY, TERMS_PATH + "/#", TERMS_ID);
        URI_MATCHER.addURI(AUTHORITY, COURSES_PATH, COURSES);
        URI_MATCHER.addURI(AUTHORITY, COURSES_PATH + "/#", COURSES_ID);
        URI_MATCHER.addURI(AUTHORITY, COURSE_NOTES_PATH, COURSE_NOTES);
        URI_MATCHER.addURI(AUTHORITY, COURSE_NOTES_PATH + "/#", COURSE_NOTES_ID);
        URI_MATCHER.addURI(AUTHORITY, ASSESSMENTS_PATH, ASSESSMENTS);
        URI_MATCHER.addURI(AUTHORITY, ASSESSMENTS_PATH + "/#", ASSESSMENTS_ID);
        URI_MATCHER.addURI(AUTHORITY, ASSESSMENT_NOTES_PATH, ASSESSMENT_NOTES);
        URI_MATCHER.addURI(AUTHORITY, ASSESSMENT_NOTES_PATH + "/#", ASSESSMENT_NOTES_ID);
    }

    public static final String TERM_CONTENT_TYPE = "term";
    public static final String COURSE_CONTENT_TYPE = "course";
    public static final String COURSE_NOTE_CONTENT_TYPE = "courseNote";
    public static final String ASSESSMENT_CONTENT_TYPE = "assessment";
    public static final String ASSESSMENT_NOTE_CONTENT_TYPE = "assessmentNote";

    private SQLiteDatabase database;


    @Override
    public boolean onCreate() {
        DBOpenHelper helper = new DBOpenHelper(getContext());
        database = helper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (URI_MATCHER.match(uri)) {
            case TERMS:
                return database.query(DBOpenHelper.TABLE_TERMS, DBOpenHelper.TERMS_COLUMNS,
                        selection, null, null, null, DBOpenHelper.TERMS_TABLE_ID + " ASC");
            case COURSES:
                return database.query(DBOpenHelper.TABLE_COURSES, DBOpenHelper.COURSES_COLUMNS,
                        selection, null, null, null, DBOpenHelper.COURSE_TABLE_ID + " ASC");
            case COURSE_NOTES:
                return database.query(DBOpenHelper.TABLE_COURSE_NOTES, DBOpenHelper.COURSE_NOTES_COLUMNS,
                        selection, null, null, null, DBOpenHelper.COURSE_NOTES_TABLE_ID + " ASC");
            case ASSESSMENTS:
                return database.query(DBOpenHelper.TABLE_ASSESSMENTS, DBOpenHelper.ASSESSMENTS_COLUMNS,
                        selection, null, null, null, DBOpenHelper.ASSESSMENTS_TABLE_ID + " ASC");
            case ASSESSMENT_NOTES:
                return database.query(DBOpenHelper.TABLE_ASSESSMENT_NOTES, DBOpenHelper.ASSESSMENT_NOTES_COLUMNS,
                        selection, null, null, null, DBOpenHelper.ASSESSMENT_NOTES_TABLE_ID + " ASC");
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long id;
        switch (URI_MATCHER.match(uri)) {
            case TERMS:
                id = database.insert(DBOpenHelper.TABLE_TERMS, null, values);
                return uri.parse(TERMS_PATH + "/" + id);
            case COURSES:
                id = database.insert(DBOpenHelper.TABLE_COURSES, null, values);
                return uri.parse(COURSES_PATH + "/" + id);
            case COURSE_NOTES:
                id = database.insert(DBOpenHelper.TABLE_COURSE_NOTES, null, values);
                return uri.parse(COURSE_NOTES_PATH + "/" + id);
            case ASSESSMENTS:
                id = database.insert(DBOpenHelper.TABLE_ASSESSMENTS, null, values);
                return uri.parse(ASSESSMENTS_PATH + "/" + id);
            case ASSESSMENT_NOTES:
                id = database.insert(DBOpenHelper.TABLE_ASSESSMENT_NOTES, null, values);
                return uri.parse(ASSESSMENT_NOTES_PATH + "/" + id);
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {

        switch (URI_MATCHER.match(uri)) {
            case TERMS:
            return database.delete(DBOpenHelper.TABLE_TERMS, s, strings);
            case COURSES:
                return database.delete(DBOpenHelper.TABLE_COURSES, s, strings);
            case COURSE_NOTES:
                return database.delete(DBOpenHelper.TABLE_COURSE_NOTES, s, strings);
            case ASSESSMENTS:
                return database.delete(DBOpenHelper.TABLE_ASSESSMENTS, s, strings);
            case ASSESSMENT_NOTES:
                return database.delete(DBOpenHelper.TABLE_ASSESSMENT_NOTES, s, strings);
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {

        switch (URI_MATCHER.match(uri)) {
            case TERMS:
                return database.update(DBOpenHelper.TABLE_TERMS, contentValues, s, strings);
            case COURSES:
                return database.update(DBOpenHelper.TABLE_COURSES, contentValues, s, strings);
            case COURSE_NOTES:
                return database.update(DBOpenHelper.TABLE_COURSE_NOTES, contentValues, s, strings);
            case ASSESSMENTS:
                return database.update(DBOpenHelper.TABLE_ASSESSMENTS, contentValues, s, strings);
            case ASSESSMENT_NOTES:
                return database.update(DBOpenHelper.TABLE_ASSESSMENT_NOTES, contentValues, s, strings);
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }


}
*/
