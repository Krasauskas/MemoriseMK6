package com.vytautaskrasauskas.memorisemk6;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCategoryActivity extends AppCompatActivity {

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        Button button = (Button) findViewById(R.id.buttonAddCategory);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                EditText temp = (EditText) findViewById(R.id.categoryName);
                String name = temp.getText().toString();
//                temp = (EditText) findViewById(R.id.categoryColour);
//                String colour = temp.getText().toString();
                SQLiteDatabase db= new DbMan(context).getWritableDatabase();
                ContentValues cv=new ContentValues();
                cv.put("CategoryName", name);
//                cv.put("CategoryColour", colour);
                db.insert("Categories", null, cv);
                db.close();
                finish();
            }
        });

        Button button2 = (Button) findViewById(R.id.buttonNukeDB);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                SQLiteDatabase db= new DbMan(context).getWritableDatabase();
                db.execSQL("DROP TABLE IF EXISTS Categories;");
                db.close();
            }
        });


    }
}

