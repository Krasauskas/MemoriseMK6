package com.vytautaskrasauskas.memorisemk6;

/**
 * Created by Krasauskas on 13-Nov-17.
 */

import android.content.Context;
import android.content.Intent;
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

public class CasesNameFragment extends Fragment {

    public static final String TITLE = "Cases By Name";
    private MyAdapterForCases adapter = null;
    private Context context = getContext();

    public static CasesNameFragment newInstance() {

        return new CasesNameFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cases_name, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.caseListName);
        rv.setHasFixedSize(true);
        adapter = new MyAdapterForCases(getCases(), getYears());
        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        return view;
    }

    public String[] getCases() {
        ArrayList<String> cases = new ArrayList<String>();
        SQLiteDatabase db = new DbMan(getActivity().getApplicationContext()).getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT CaseID as _id, CaseName from Cases", new String[]{});
        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            String name = cur.getString(cur.getColumnIndex("CaseName"));
            Log.d("DB entry", name);
            cases.add(name);
            cur.moveToNext();
        }
        cur.close();
        db.close();
        return cases.toArray(new String[cases.size()]);
    }

    public int[] getYears() {
        ArrayList<Integer> years = new ArrayList<Integer>();
        SQLiteDatabase db = new DbMan(getActivity().getApplicationContext()).getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT CaseID as _id, CaseYear from Cases", new String[]{});
        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            int year = Integer.parseInt(cur.getString(cur.getColumnIndex("CaseYear")));
            Log.d("DB entry", year + "");
            years.add(year);
            cur.moveToNext();
        }
        cur.close();
        db.close();

        //convert to int[]
        int [] x = new int[years.size()];
        int i = 0;
        for (Integer n : years) {
            x[i++] = n.intValue();
        }
        return x;
    }

    //The following is to let the adapter know to update the visible cases. This is not nice and doesnt work properly
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && !(adapter == null)) {
            adapter.notifyDataSetChanged();
        }
    }

    private void goToCaseActivity() {

        Intent intent = new Intent(context, CaseActivity.class);

        startActivity(intent);
    }
}