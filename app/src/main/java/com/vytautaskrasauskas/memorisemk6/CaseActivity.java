package com.vytautaskrasauskas.memorisemk6;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class CaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case);
        String caseName = getIntent().getStringExtra("Case Name");


        TextView name = (TextView) findViewById(R.id.tv_caseName);
        TextView year = (TextView) findViewById(R.id.tv_caseYear);
        TextView ruling = (TextView) findViewById(R.id.tv_caseRuling);
        TextView facts = (TextView) findViewById(R.id.tv_caseFacts);
        TextView issues = (TextView) findViewById(R.id.tv_caseIssues);
        String[] textFields = setTextForCases(caseName);
        name.setText(textFields[0]);
        year.setText(textFields[1]);
        ruling.setText(textFields[2]);
        facts.setText(textFields[3]);
        issues.setText(textFields[4]);
    }

    public String[] setTextForCases(String caseName) {
        SQLiteDatabase db = new DbMan(this).getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM Cases WHERE CaseName = \"" +caseName + "\"", new String[]{});
        cur.moveToFirst();
        String[] returnValues = new String[5];
        returnValues[0] = "Case Name:\n" + cur.getString(cur.getColumnIndex("CaseName"));
        returnValues[1] = "Case Year:\n" + cur.getString(cur.getColumnIndex("CaseYear"));
        returnValues[2] = "Ruling:\n" + cur.getString(cur.getColumnIndex("CaseRuling"));
        returnValues[3] = "Facts:\n" + cur.getString(cur.getColumnIndex("CaseFacts"));
        returnValues[4] = "Issues:\n" + cur.getString(cur.getColumnIndex("CaseIssues"));
//        String Case = cur.getString(cur.getColumnIndex(caseName));
        Log.d("Case name", returnValues[0]);
        Log.d("Case year", returnValues[1]);
        Log.d("Case ruling", returnValues[2]);
        Log.d("Case facts", returnValues[3]);
        Log.d("Case issues", returnValues[4]);
        cur.close();
        db.close();
        return returnValues;
    }

}
