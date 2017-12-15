package com.vytautaskrasauskas.memorisemk6;

/**
 * Created by Krasauskas on 13-Nov-17.
 */

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CategoriesFragment extends Fragment {

    public static final String TITLE = "Categories";
    private MyAdapter adapter = null;

    public static CategoriesFragment newInstance() {

        return new CategoriesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.categoryList);
        rv.setHasFixedSize(true);
        adapter = new MyAdapter(getCategories());
        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        return view;
    }


    public String[] getCategories() {
        ArrayList<String> cats = new ArrayList<String>();
        SQLiteDatabase db = new DbMan(getActivity().getApplicationContext()).getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT CategoryID as _id, CategoryName from Categories", new String[]{});
        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            String lalala = cur.getString(cur.getColumnIndex("CategoryName"));
            Log.d("DB entry", lalala);
            cats.add(lalala);
            cur.moveToNext();
        }
        cur.close();
        db.close();
        return cats.toArray(new String[cats.size()]);
    }

    //The following is to let the adapter know to update the visible categories. This is not nice and doesnt work properly
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && !(this.adapter == null)) {
            this.adapter.notifyDataSetChanged();
        }
    }
//
//    public void updateData() {
//        this.adapter = new MyAdapter(getCategories());
//
//    }
}