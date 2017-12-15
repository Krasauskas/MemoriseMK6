package com.vytautaskrasauskas.memorisemk6;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCaseActivity extends AppCompatActivity {

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.vytautaskrasauskas.memorisemk6.R.layout.activity_add_case);

        Button button = (Button) findViewById(R.id.buttonAddCase);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                EditText temp = (EditText) findViewById(R.id.caseName);
                String name = temp.getText().toString();
                temp = (EditText) findViewById(R.id.caseRuling);
                String ruling = temp.getText().toString();
                temp = (EditText) findViewById(R.id.caseFacts);
                String facts = temp.getText().toString();
                temp = (EditText) findViewById(R.id.caseCategory);
                String category = temp.getText().toString();
                temp = (EditText) findViewById(R.id.caseYear);
                int year = Integer.parseInt(temp.getText().toString());
                temp = (EditText) findViewById(R.id.caseIssues);
                String issues = temp.getText().toString();
                SQLiteDatabase db= new DbMan(context).getWritableDatabase();
                ContentValues cv=new ContentValues();
                cv.put("CaseCategory", category);
                cv.put("CaseName", name);
                cv.put("CaseRuling", ruling);
                cv.put("CaseYear", year);
                cv.put("CaseIssues", issues);
                cv.put("CaseFacts", facts);
                db.insert("Cases", null, cv);
                db.close();
                finish();
            }
        });

    }
}
