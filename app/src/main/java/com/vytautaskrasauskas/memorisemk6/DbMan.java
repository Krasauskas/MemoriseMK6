package com.vytautaskrasauskas.memorisemk6;

/**
 * Created by Krasauskas on 13-Nov-17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

//Database Manager
public class DbMan extends SQLiteOpenHelper {

    static final String dbName = "MemoriseDB";
    static final String categoriesTable = "Categories";
    static final String categoryID = "CategoryID";
    static final String categoryName = "CategoryName";
    static final String categoryColour = "CategoryColour";
    static final String viewCategories = "ViewCategories";

    static final String casesTable = "Cases";
    static final String caseID = "CaseID";
    static final String caseCategory = "CaseCategory";
    static final String caseName = "CaseName";
    static final String caseYear = "CaseYear";
    static final String caseFacts = "CaseFacts";
    static final String caseIssues = "CaseIssues";
    static final String caseRuling = "CaseRuling";
    static final String viewCases = "ViewCases";

    //customization stuff below
    static final String caseColour = "CaseColour";
    static final String casePicture = "CasePicture";
    static final String caseAudio = "CaseAudio";
    static final String caseURL = "CaseURL";
    static final String caseNotes = "CaseNotes";
    static final String caseAudioRecord = "CaseAudioRecord";

    public DbMan(Context context) {
        super(context, dbName, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE1 = "CREATE TABLE IF NOT EXISTS "+categoriesTable+"("+categoryID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                categoryName+ " TEXT, "+categoryColour+ " TEXT)";
        db.execSQL(CREATE_TABLE1);

        String CREATE_TABLE2 = "CREATE TABLE IF NOT EXISTS "+casesTable+"("+caseID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+caseName+" TEXT, "+caseCategory+ " TEXT, "+caseYear+" Integer, "+caseFacts+ " TEXT, "+caseIssues+" TEXT, "+caseRuling+" TEXT, "+caseColour+" TEXT)";
        db.execSQL(CREATE_TABLE2);

        String CREATE_CASES_VIEW = "CREATE VIEW "+viewCases+" AS SELECT "+casesTable+"."+caseID+" AS _id,"+ " "+casesTable+"."+caseName+","+ " "+casesTable+"."+caseYear+","+ " "+casesTable+"."+caseColour+""+ " FROM "+casesTable;
        db.execSQL(CREATE_CASES_VIEW);

        String CREATE_CATEGORIES_VIEW = "CREATE VIEW " +viewCategories+ " AS SELECT " +categoriesTable+"."+categoryID+ " AS _id ," +categoriesTable+"."+categoryName+", " +categoriesTable+"."+categoryColour+ " FROM " +categoriesTable;
        db.execSQL(CREATE_CATEGORIES_VIEW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+categoriesTable);
        db.execSQL("DROP TABLE IF EXISTS "+casesTable);
        db.execSQL("DROP VIEW IF EXISTS "+viewCategories);
        db.execSQL("DROP VIEW IF EXISTS "+viewCases);
        onCreate(db);
    }

//    public String[] getCategories() {
//        ArrayList<String> cats = new ArrayList<String>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cur = db.rawQuery("SELECT CategoryID as _id, CategoryName from Categories", new String[]{});
//        cur.moveToFirst();
//        while (!cur.isAfterLast()) {
//            cats.add(cur.getString(cur.getColumnIndex("CategoryName")));
//            cur.moveToNext();
//        }
//        cur.close();
//        db.close();
//        return cats.toArray(new String[cats.size()]);
//    }
//
//    public void addCategory(String name, String colour){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put("CategoryName", name);
//        cv.put("CategoryColour", colour);
//        db.insert("Categories", null, cv);
//        db.close();
//    }
}