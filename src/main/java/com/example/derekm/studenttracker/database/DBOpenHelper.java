package com.example.derekm.studenttracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBOpenHelper extends SQLiteOpenHelper{

    // contstants for db name and version
    private static final String DATABASE_NAME = "scheduler.db";
    private static final int DATABASE_VERSION = 1;

    //Terms Table
    public static final String TABLE_TERMS = "terms";
    public static final String TERMS_TABLE_ID = "id";
    public static final String TERM_NAME = "name";
    public static final String TERM_START = "start";
    public static final String TERM_END = "termEnd";
    //public static final String TERM_ACTIVE = "active";
    public static final String[] TERMS_COLUMNS = {TERMS_TABLE_ID, TERM_NAME, TERM_START, TERM_END};

    //Courses Table
    public static final String TABLE_COURSES = "courses";
    public static final String COURSE_TABLE_ID = "_id";
    public static final String COURSE_TERM_ID = "termId";
    public static final String COURSE_NAME = "name";
    //public static final String COURSE_DESCRIPTION = "description";
    public static final String COURSE_START = "start";
    public static final String COURSE_END = "courseEnd";
    public static final String COURSE_STATUS = "status";
    public static final String COURSE_MENTOR = "mentor";
    public static final String COURSE_MENTOR_PHONE = "mentorPhone";
    public static final String COURSE_MENTOR_EMAIL = "mentorEmail";
    //public static final String COURSE_ALERTS = "alerts";
    public static final String[] COURSES_COLUMNS = {COURSE_TABLE_ID, COURSE_TERM_ID, COURSE_NAME,
            COURSE_START, COURSE_END, COURSE_STATUS, COURSE_MENTOR, COURSE_MENTOR_PHONE, COURSE_MENTOR_EMAIL};

    // Course Notes table
    public static final String TABLE_COURSE_NOTES = "courseNotes";
    public static final String COURSE_NOTES_TABLE_ID = "_id";
    public static final String COURSE_NOTE_COURSE_ID = "courseId";
    public static final String COURSE_NOTE_TEXT = "noteText";
    public static final String COURSE_NOTE_CREATED = "noteCreated";
    public static final String[] COURSE_NOTES_COLUMNS = {COURSE_NOTES_TABLE_ID, COURSE_NOTE_COURSE_ID, COURSE_NOTE_TEXT, COURSE_NOTE_CREATED};

    // Assessments table
    public static final String TABLE_ASSESSMENTS = "assessments";
    public static final String ASSESSMENTS_TABLE_ID = "_id";
    public static final String ASSESSMENT_COURSE_ID = "assessmentCourseId";
    public static final String ASSESSMENT_TYPE = "type";
    public static final String ASSESSMENT_NAME = "name";
    public static final String ASSESSMENT_DESCRIPTION = "description";
    public static final String ASSESSMENT_DATETIME = "datetime";
    public static final String ASSESSMENT_NOTIFICATIONS = "notifications";
    public static final String[] ASSESSMENTS_COLUMNS = {ASSESSMENTS_TABLE_ID, ASSESSMENT_COURSE_ID, ASSESSMENT_TYPE,
            ASSESSMENT_NAME, ASSESSMENT_DESCRIPTION, ASSESSMENT_DATETIME, ASSESSMENT_NOTIFICATIONS};

    // Goals table
    private static final String TABLE_GOAL = "goalDates";
    private static final String GOAL_DATE_ID = "goalDateDd";
    private static final String GOAL_DESCRIPTION = "description";
    private static final String GOAL_DATE = "date";
    private static final String GOAL_ASSESSMENT_ID = "assessmentId";

    // Assessment Notes table
    //public static final String TABLE_ASSESSMENT_NOTES = "assessmentNotes";
    //public static final String ASSESSMENT_NOTES_TABLE_ID = "_id";
    //public static final String ASSESSMENT_NOTE_ASSESSMENT_ID = "noteAssessmentId";
    //public static final String ASSESSMENT_NOTE_TEXT = "noteText";
    //public static final String ASSESSMENT_NOTE_CREATED = "noteCreated";
    //public static final String[] ASSESSMENT_NOTES_COLUMNS = {ASSESSMENT_NOTES_TABLE_ID, ASSESSMENT_NOTE_ASSESSMENT_ID,
    //        ASSESSMENT_NOTE_TEXT};


    //Create tables
    //Terms SQL
    private static final String TERMS_TABLE_CREATE =
            "CREATE TABLE " + TABLE_TERMS + " (" +
                    TERMS_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TERM_NAME + " TEXT, " +
                    TERM_START + " DATE, " +
                    TERM_END + " DATE " +
                    //TERM_ACTIVE + " INTEGER " +
                    ")";

    //Courses SQL
    private static final String TABLE_COURSES_CREATE =
            "CREATE TABLE " + TABLE_COURSES + " (" +
                    COURSE_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COURSE_TERM_ID + " INTEGER, " +
                    COURSE_NAME + " TEXT, " +
                    //COURSE_DESCRIPTION + " TEXT, " +
                    COURSE_START + " DATE, " +
                    COURSE_END + " DATE, " +
                    COURSE_STATUS + " TEXT, " +
                    COURSE_MENTOR + " TEXT, " +
                    COURSE_MENTOR_PHONE + " TEXT, " +
                    COURSE_MENTOR_EMAIL + " TEXT, " +
                    //COURSE_ALERTS + " TEXT, " +
                    "FOREIGN KEY(" + COURSE_TERM_ID + ") REFERENCES " + TABLE_TERMS + "(" + TERMS_TABLE_ID + ")" +
                    ")";

    //Course notes SQL
    private static final String TABLE_COURSE_NOTES_CREATE =
            "CREATE TABLE " + TABLE_COURSE_NOTES + " (" +
                    COURSE_NOTES_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COURSE_NOTE_COURSE_ID + " INTEGER, " +
                    COURSE_NOTE_TEXT + " TEXT, " +
                    COURSE_NOTE_CREATED + " TEXT default CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY(" + COURSE_NOTE_COURSE_ID + ") REFERENCES " + TABLE_COURSES + "(" + COURSE_TABLE_ID + ")" +
                    ")";

    //Assessments SQL
    private static final String TABLE_ASSESSMENTS_CREATE =
            "CREATE TABLE " + TABLE_ASSESSMENTS + " (" +
                    ASSESSMENTS_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ASSESSMENT_COURSE_ID + " INTEGER, " +
                    ASSESSMENT_TYPE + " TEXT, " +
                    ASSESSMENT_NAME + " TEXT, " +
                    ASSESSMENT_DESCRIPTION + " TEXT, " +
                    ASSESSMENT_DATETIME + " DATE, " +
                    ASSESSMENT_NOTIFICATIONS + " INTEGER, " +
                    "FOREIGN KEY(" + ASSESSMENT_COURSE_ID + ") REFERENCES " + TABLE_COURSES + "(" + COURSE_TABLE_ID + ")" +
                    ")";
    // goal dates sql
    private static final String TABLE_GOALS_CREATE =
            "CREATE TABLE " + TABLE_GOAL + " (" +
                    GOAL_DATE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    GOAL_DESCRIPTION + " TEXT, " +
                    GOAL_DATE + " TEXT " +
                    GOAL_ASSESSMENT_ID + " INTEGER, " +
                    "FOREIGN KEY(" + GOAL_ASSESSMENT_ID + ") REFERENCES " + TABLE_ASSESSMENTS_CREATE + "(" + ASSESSMENTS_TABLE_ID + ")" +
                    ")";

    //Assessments notes SQL
    //private static final String TABLE_ASSESSMENT_NOTES_CREATE =
     //       "CREATE TABLE " + TABLE_ASSESSMENT_NOTES + " (" +
     //               ASSESSMENT_NOTES_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
     //               ASSESSMENT_NOTE_ASSESSMENT_ID + " INTEGER, " +
     //               ASSESSMENT_NOTE_TEXT + " TEXT, " +
     //               ASSESSMENT_NOTE_CREATED + " TEXT default CURRENT_TIMESTAMP, " +
     //               "FOREIGN KEY(" + ASSESSMENT_NOTE_ASSESSMENT_ID + ") REFERENCES " + TABLE_ASSESSMENTS + "(" + ASSESSMENTS_TABLE_ID + ")" +
     //               ")";




    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TERMS_TABLE_CREATE);
        db.execSQL(TABLE_COURSES_CREATE);
        db.execSQL(TABLE_COURSE_NOTES_CREATE);
        db.execSQL(TABLE_ASSESSMENTS_CREATE);
        db.execSQL(TABLE_GOALS_CREATE);
       // db.execSQL(TABLE_ASSESSMENT_NOTES_CREATE);


    //}

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TERMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE_NOTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSESSMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOALS_CREATE);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSESSMENT_NOTES);


    }


























    public boolean insertTerm(String name, String start, String termEnd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TERM_NAME, name);
        cv.put(TERM_START, start);
        cv.put(TERM_END, termEnd);
        db.insert(TABLE_TERMS, null, cv);
        return true;
    }

    public boolean insertCourse(
            String name,
            String start,
            String courseEnd,
            String status,
            int termId,
            ArrayList<Mentor> mentors
    ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COURSE_NAME, name);
        cv.put(COURSE_START, start);
        cv.put(COURSE_END, courseEnd);
        cv.put(COURSE_STATUS, status);
        cv.put(COURSE_TERM_ID, termId);
        long mCourseId = db.insert(TABLE_COURSES, null, cv);
        for (Mentor mentor : mentors) {
            ContentValues cv1 = new ContentValues();
            cv1.put(COURSE_MENTOR, mentor.getmentorName());
            cv1.put(COURSE_MENTOR_PHONE, mentor.getmentorPhone());
            cv1.put(COURSE_MENTOR_EMAIL, mentor.getmentorEmail());

        }
        return true;
    }

    public void insertNote(int courseId, String noteText) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COURSE_NOTE_COURSE_ID, courseId);
        cv.put(COURSE_NOTE_TEXT, noteText);
        db.insert(TABLE_COURSE_NOTES, null, cv);
    }

    public boolean insertAssessment(
            String name,
            String type,
            String due,
            int courseId,
            ArrayList<GoalDate> goalDates
    ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ASSESSMENT_NAME, name);
        cv.put(ASSESSMENT_TYPE, type);
        cv.put(ASSESSMENT_DATETIME, datetime);
        cv.put(ASSESSMENT_COURSE_ID, courseId);
        long mAssessmentId = db.insert(TABLE_ASSESSMENTS, null, cv);
        for (GoalDate goalDate : goalDates) {
            ContentValues cv1 = new ContentValues();
            cv1.put(GOAL_DESCRIPTION, goalDate.description());
            cv1.put(GOAL_DATE, goalDate.date());
            cv1.put(GOAL_ASSESSMENT_ID, assessmentId);
            db.insert(TABLE_GOAL, null, cv1);
        }
        return true;
    }













    






    public boolean updateTerm(int id, String title, String start, String end) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TERM_COLUMN_TITLE, title);
        cv.put(TERM_COLUMN_START, start);
        cv.put(TERM_COLUMN_END, end);
        db.update(
                TERM_TABLE_NAME,
                cv,
                TERM_COLUMN_ID + " = ? ",
                new String[] { Integer.toString(id) }
        );
        return true;
    }

    public boolean updateCourse(
            int id,
            String title,
            String start,
            String end,
            String status,
            ArrayList<Mentor> mentors
    ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COURSE_COLUMN_TITLE, title);
        cv.put(COURSE_COLUMN_START, start);
        cv.put(COURSE_COLUMN_END, end);
        cv.put(COURSE_COLUMN_STATUS, status);
        db.update(
                COURSE_TABLE_NAME,
                cv,
                COURSE_COLUMN_ID + " = ? ",
                new String[] { Integer.toString(id) }
        );
        db.delete(MENTOR_TABLE_NAME, MENTOR_COLUMN_COURSE_ID + " = " + id, null);
        for (Mentor mentor : mentors) {
            ContentValues cv1 = new ContentValues();
            cv1.put(MENTOR_COLUMN_NAME, mentor.getmName());
            cv1.put(MENTOR_COLUMN_PHONE, mentor.getmPhone());
            cv1.put(MENTOR_COLUMN_EMAIL, mentor.getmEmail());
            cv1.put(MENTOR_COLUMN_COURSE_ID, id);
            db.insert(MENTOR_TABLE_NAME, null, cv1);
        }
        return true;
    }

    public boolean updateAssessment(
            int id,
            String title,
            String type,
            String due,
            ArrayList<GoalDate> goalDates
    ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ASSESSMENT_COLUMN_TITLE, title);
        cv.put(ASSESSMENT_COLUMN_TYPE, type);
        cv.put(ASSESSMENT_COLUMN_DUE, due);
        db.update(
                ASSESSMENT_TABLE_NAME,
                cv,
                ASSESSMENT_COLUMN_ID + " = ?",
                new String[] { Integer.toString(id) }
        );
        db.delete(GOAL_DATE_TABLE_NAME, GOAL_DATE_COLUMN_ASSESSMENT_ID + " = " + id, null);
        for (GoalDate goalDate : goalDates) {
            ContentValues cv1 = new ContentValues();
            cv1.put(GOAL_DATE_COLUMN_DESCRIPTION, goalDate.getmDescription());
            cv1.put(GOAL_DATE_COLUMN_DATE, goalDate.getmDate());
            cv1.put(GOAL_DATE_COLUMN_ASSESSMENT_ID, id);
            db.insert(GOAL_DATE_TABLE_NAME, null, cv1);
        }
        return true;
    }

    public void updateNote(int id, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NOTE_COLUMN_NOTE, note);
        db.update(
                NOTE_TABLE_NAME,
                cv,
                NOTE_COLUMN_ID + " = ?",
                new String[] { Integer.toString(id)}
        );
    }

























    public boolean deleteTerm(int termId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TERM_TABLE_NAME, TERM_COLUMN_ID + " = " + termId, null);
        return true;
    }

    public boolean deleteCourse(int courseId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(COURSE_TABLE_NAME, COURSE_COLUMN_ID + " = " + courseId, null);
        db.delete(ASSESSMENT_TABLE_NAME, ASSESSMENT_COLUMN_COURSE_ID + " = " + courseId, null);
        db.delete(NOTE_TABLE_NAME, NOTE_COLUMN_COURSE_ID + " = " + courseId, null);
        return true;
    }

    public boolean deleteAssessment(int assessmentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ASSESSMENT_TABLE_NAME, ASSESSMENT_COLUMN_ID + " = " + assessmentId, null);
        db.delete(GOAL_DATE_TABLE_NAME, GOAL_DATE_COLUMN_ASSESSMENT_ID + " = " + assessmentId, null);
        return true;
    }

    public void deleteNote(int noteId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NOTE_TABLE_NAME, NOTE_COLUMN_ID + " = " + noteId, null);
    }





















    public Term getTerm(int termId) {
        Term term;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT * FROM " + TERM_TABLE_NAME + " WHERE " + TERM_COLUMN_ID + " = " + termId,
                null
        );
        res.moveToFirst();
        term = new Term(
                res.getInt(0),
                res.getString(1),
                res.getString(2),
                res.getString(3)
        );
        res.close();
        return term;
    }

    public Course getCourse(int courseId) {
        Course course;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT * FROM " + COURSE_TABLE_NAME + " WHERE " + COURSE_COLUMN_ID + " = " + courseId,
                null
        );
        res.moveToFirst();
        course = new Course(
                res.getInt(0),
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getInt(5)
        );
        res.close();
        return course;
    }

    public Assessment getAssessment(int assessmentId) {
        Assessment assessment;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT * FROM " + ASSESSMENT_TABLE_NAME + " WHERE " + ASSESSMENT_COLUMN_ID + " = " + assessmentId,
                null
        );
        res.moveToFirst();
        assessment = new Assessment(
                res.getInt(0),
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getInt(4)
        );
        res.close();
        return assessment;
    }

    public ArrayList<Term> getTerms() {
        ArrayList<Term> a = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TERM_TABLE_NAME, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            int mId = res.getInt(0);
            String mTitle = res.getString(1);
            String mStart = res.getString(2);
            String mEnd = res.getString(3);
            a.add(new Term(mId, mTitle, mStart, mEnd));
            res.moveToNext();
        }
        res.close();
        return a;
    }

    public ArrayList<Course> getCourses(int termId) {
        ArrayList<Course> a = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT * FROM " + COURSE_TABLE_NAME + " WHERE " + COURSE_COLUMN_TERM_ID + " = " + termId,
                null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            int mId = res.getInt(0);
            String mTitle = res.getString(1);
            String mStatus = res.getString(2);
            String mStart = res.getString(3);
            String mEnd = res.getString(4);
            int mTermId = res.getInt(5);
            a.add(new Course(mId, mTitle, mStatus, mStart, mEnd, mTermId));
            res.moveToNext();
        }
        res.close();
        return a;
    }

    public ArrayList<Mentor> getMentors(int courseId) {
        ArrayList<Mentor> a = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT * FROM " + MENTOR_TABLE_NAME + " WHERE " + MENTOR_COLUMN_COURSE_ID + " = " + courseId,
                null
        );
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String mName = res.getString(1);
            String mPhone = res.getString(2);
            String mEmail = res.getString(3);
            a.add(new Mentor(mName, mPhone, mEmail));
            res.moveToNext();
        }
        res.close();
        return a;
    }

    public ArrayList<Assessment> getAssessments(int courseId) {
        ArrayList<Assessment> a = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT * FROM " + ASSESSMENT_TABLE_NAME + " WHERE " + ASSESSMENT_COLUMN_COURSE_ID + " = " + courseId,
                null
        );
        res.moveToFirst();
        while (!res.isAfterLast()) {
            int mId = res.getInt(0);
            String mTitle = res.getString(1);
            String mType = res.getString(2);
            String mDue = res.getString(3);
            int mCourseId = res.getInt(4);
            a.add(new Assessment(mId, mTitle, mType, mDue, mCourseId));
            res.moveToNext();
        }
        res.close();
        return a;
    }

    public ArrayList<Note> getNotes(int courseId) {
        ArrayList<Note> a = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT * FROM " + NOTE_TABLE_NAME + " WHERE " + NOTE_COLUMN_COURSE_ID + " = " + courseId,
                null
        );
        res.moveToFirst();
        while (!res.isAfterLast()) {
            int mId = res.getInt(0);
            String mNote = res.getString(1);
            int mCourseId = res.getInt(2);
            a.add(new Note(mId, mNote, mCourseId));
            res.moveToNext();
        }
        res.close();
        return a;
    }

    public ArrayList<GoalDate> getGoalDates(int assessmentId) {
        ArrayList<GoalDate> a = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT * FROM " + GOAL_DATE_TABLE_NAME + " WHERE " + GOAL_DATE_COLUMN_ASSESSMENT_ID + " = " + assessmentId,
                null
        );
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String mDescription = res.getString(1);
            String mDate = res.getString(2);
            a.add(new GoalDate(mDescription, mDate));
            res.moveToNext();
        }
        res.close();
        return a;
    }
}
}
