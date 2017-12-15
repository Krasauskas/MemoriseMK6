package com.vytautaskrasauskas.memorisemk6;

/**
 * Created by Krasauskas on 13-Nov-17.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CasesYearFragment extends Fragment {

    public static final String TITLE = "Cases By Year";

    public static CasesYearFragment newInstance() {

        return new CasesYearFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cases_year, container, false);
    }
}